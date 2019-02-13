package com.hotcomm.community.resful.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotcomm.community.resful.comm.ThreadLocalManager;
import com.hotcomm.framework.comm.Constant;
import com.hotcomm.framework.comm.ContentType;
import com.hotcomm.framework.comm.LoginUser;
import com.hotcomm.framework.utils.RedisHelper;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.web.exception.HKException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description: token 安全拦截器
 * @author 万鹏
 * @date 2018年12月5日 上午9:48:21
 * @version V1.0
 */
public class TokenAuthInterceptor implements HandlerInterceptor {

	Logger log = LoggerFactory.getLogger("infoLogger");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String devModel = SpringUtil.getBean(Environment.class).getProperty("dev_model", String.class);
		ThreadLocalManager manager = SpringUtil.getBean(ThreadLocalManager.class);
		// 判断当前项目运行模式 test:则放行,并且往线程持有者存储用户信息 prod:则根据token的安全验证
		if (Constant.DevModel.DEV.getVal().equals(devModel)) {
			manager.setLoingUser(new LoginUser(1, "test", Constant.DevModel.DEV));
			return true;
		}
		
		// 根据http协议请求头类型获取token		
		String token = null;
		if (request.getContentType().equals(ContentType.JSON.getName())||request.getContentType().startsWith(ContentType.JSON.getName())) {
			Map<String, Object> params = SpringUtil.getBean(ThreadLocalManager.class).getRequestParams();
			token = params.get("token").toString();
		} else {
			token = request.getParameter("token");
		}
		
		//验证token 安全性
		if (token == null || token.isEmpty())	throw new HKException("-2", "缺失令牌验证");
		
		RedisHelper redisHelper = SpringUtil.getBean(RedisHelper.class);
		String redisValStr = redisHelper.get(token);
		if (redisValStr == null) throw new HKException("-2", "该令牌验证不合法");
		
		// 将redis种存储的数据拿到并且反序列化,数据存储值线程持有者对象种,方便后面开发拿到
		ObjectMapper jsonMapper = new ObjectMapper();
		LoginUser user = jsonMapper.readValue(redisValStr, LoginUser.class);
		user.setDevModel(Constant.DevModel.PROD);
		manager.setLoingUser(user);
		
		// 更新缓存时间,所有接口调用时增加请求来源，0：大数据;1:后台
		{
			String source = null;
			if(request.getContentType().equals(ContentType.JSON.getName())||request.getContentType().startsWith(ContentType.JSON.getName())) {
				
				Map<String, Object> params = SpringUtil.getBean(ThreadLocalManager.class).getRequestParams();
				source = params.get("source").toString();
				if (source.equals("0")) {
					redisHelper.set(token, redisValStr);
				} else {
					redisHelper.set(token, redisValStr, 60 * 60 * 24);
				}
			} else {
				source = request.getParameter("source");
				if (source.equals("0")) {
					redisHelper.set(token, redisValStr);
				} else {
					redisHelper.set(token, redisValStr, 60 * 60 * 24);
				}
			}
		}
	
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
