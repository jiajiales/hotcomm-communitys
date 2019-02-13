package com.hotcomm.community.resful.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotcomm.community.resful.comm.HKRequestWrapper;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.community.resful.comm.ThreadLocalManager;
import com.hotcomm.framework.comm.ContentType;
import com.hotcomm.framework.utils.SpringUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Component
@Order // 过滤器加载的顺序 默认Integer.MAXVALUE
@WebFilter(urlPatterns = "/*", filterName = "wholeFilter") // 拦截所有路径
public class HttpFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest requ = (HttpServletRequest) request;
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers", "Origin,x-requested-with,Authorization,Content-Type, Accept");
		resp.setHeader("Access-Control-Allow-Credentials", "true");

		// 判断日志记录是否需要忽略
		if (RestfulUrlRecord.skip(requ.getRequestURI()) || requ.getRequestURI().contains("/druid/") || requ.getRequestURI().contains("/favicon.ico")) {
			chain.doFilter(request, response);
			return;
		}

		if(request.getContentType().equals(ContentType.JSON.getName())||request.getContentType().startsWith(ContentType.JSON.getName())) {
			ServletRequest requestWrapper = null;
			if (request instanceof HttpServletRequest) {
				requestWrapper = new HKRequestWrapper((HttpServletRequest) request);
			}
			StringBuffer json = new StringBuffer();
			String line = null;
			BufferedReader reader =requestWrapper.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			String requestParamsStr = json.toString();
			if(requestParamsStr.trim().length()>0) {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> params = mapper.readValue(json.toString(), new TypeReference<Map<String, Object>>() {});
				SpringUtil.getBean(ThreadLocalManager.class).setRequestParams(params);
			}
			chain.doFilter(requestWrapper, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
