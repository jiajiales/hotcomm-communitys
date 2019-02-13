package com.hotcomm.framework.utils;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
 
	 private static final String CHARSET_UTF_8 = "UTF-8";
	 private static final String CONTENT_TYPE_JSON = "application/json";

	    /**
	     * 发送get请求
	     * @param url
	     * @return
	     */
	    public static String get(String url) {
	        String res = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        try {
	            HttpGet httpGet = new HttpGet(url);
	            res = execute(httpClient, httpGet);
	        } finally {
	            doHttpClientClose(httpClient);
	        }
	        return res;
	    }

	    /**
	     * post json数据
	     * @param url
	     * @param jsonStr
	     * @return
	     */
	    public static String postJson(String url, String jsonStr) {
	        String res = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        try {
	            HttpPost httpPost = new HttpPost(url);
	            StringEntity stringEntity;
	            try {
	                stringEntity = new StringEntity(jsonStr);
	            } catch (UnsupportedEncodingException e) {
	                return null;
	            }
	            httpPost.setHeader("Content-Type", CONTENT_TYPE_JSON);
	            httpPost.setEntity(stringEntity);
	            res = execute(httpClient, httpPost);
	        } finally {
	            doHttpClientClose(httpClient);
	        }
	        return res;
	    }
	    
	    /**
	     * 发送post请求
	     * @param url    post url
	     * @param params post参数
	     * @return
	     */
	    public static String post(String url, Map<String, String> params) {
	        String res = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        try {
	            HttpPost httpPost = httpPostHandler(url, params);
	            res = execute(httpClient, httpPost);
	        } finally {
	            doHttpClientClose(httpClient);
	        }
	        return res;
	    }



	  
	    private static HttpPost httpPostHandler(String url, Map<String, String> params) {
	        HttpPost httpPost = new HttpPost(url);
	        List<NameValuePair> nvps = new ArrayList<>();
	        for (Map.Entry<String, String> entry : params.entrySet()) {
	            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
	        }
	        try {
	            httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET_UTF_8));
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return httpPost;
	    }
	    private static String execute(CloseableHttpClient httpClient, HttpUriRequest httpGetOrPost) {
	        String res = null;
	        CloseableHttpResponse response = null;
	        try {
	            response = httpClient.execute(httpGetOrPost);
	            HttpEntity entity = response.getEntity();
	            res = EntityUtils.toString(entity, CHARSET_UTF_8);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            doResponseClose(response);
	        }
	        return res;
	    }

	    private static void doHttpClientClose(CloseableHttpClient httpClient) {
	        if (httpClient != null) {
	            try {
	                httpClient.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    private static void doResponseClose(CloseableHttpResponse response) {
	        if (response != null) {
	            try {
	                response.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
