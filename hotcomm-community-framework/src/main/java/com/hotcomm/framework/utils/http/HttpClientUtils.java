package com.hotcomm.framework.utils.http;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


public class HttpClientUtils {

    public static final Logger ERROR_LOG = LoggerFactory.getLogger("errorLogger");
    public static final Logger ROOT = LoggerFactory.getLogger(HttpClientUtils.class);

    public static HotHttpResponse post(List<HotHttpEntity> params, String url) {
        HotHttpResponse result = new HotHttpResponse();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(1000).setSocketTimeout(10000).setRedirectsEnabled(true).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        for (Iterator<HotHttpEntity> itrator = params.iterator(); itrator.hasNext(); ) {
            HotHttpEntity hotHttpEntity = itrator.next();
            EntityEnum type = hotHttpEntity.getType();
            if (type == EntityEnum.TEXT) {
                StringBody stringBody = null;
                stringBody = new StringBody(hotHttpEntity.getValue().toString(), ContentType.TEXT_PLAIN);
                builder.addPart(hotHttpEntity.getKey(), stringBody);
            } else {
                File file = (File) hotHttpEntity.getValue();
                FileBody fileBody = new FileBody(file);
                builder.addPart(hotHttpEntity.getKey(), fileBody);
            }
        }
        HttpEntity reqEntity = builder.build();
        httpPost.setEntity(reqEntity);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            boolean flag = HttpStatus.SC_OK == response.getStatusLine().getStatusCode();
            result.setSuccess(flag);
            if (flag) {
                ROOT.info("请求路径：{},结果:成功", url);
                HttpEntity entitys = response.getEntity();
                if (entitys != null) {
                    String resultStr = EntityUtils.toString(entitys);
                    result.setReturnJson(resultStr);
                    ROOT.info("执行结果：{}",resultStr);
                }
            }else{
                HttpEntity entitys = response.getEntity();
                ERROR_LOG.info("请求路径：{},请求结果：成功", url);
                String resultStr = EntityUtils.toString(entitys);
                result.setReturnJson(resultStr);
                ERROR_LOG.info("执行结果：{}",resultStr);
            }
            httpClient.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    class Response{
        Integer code;
        String data;
    }

    @SuppressWarnings("deprecation")
    public static HotHttpResponse doPost(List<HotHttpEntity> params, String url) {
        HotHttpResponse result = new HotHttpResponse();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(1000).setSocketTimeout(10000).setRedirectsEnabled(true).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        for (Iterator<HotHttpEntity> itrator = params.iterator(); itrator.hasNext(); ) {
            HotHttpEntity hotHttpEntity = itrator.next();
            EntityEnum type = hotHttpEntity.getType();
            if (type == EntityEnum.TEXT) {
                StringBody stringBody = null;
                try {
                    stringBody = new StringBody(hotHttpEntity.getValue().toString(), Consts.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                builder.addPart(hotHttpEntity.getKey(), stringBody);
            } else {
                File file = (File) hotHttpEntity.getValue();
                FileBody fileBody = new FileBody(file);
                builder.addPart(hotHttpEntity.getKey(), fileBody);
            }
        }
        HttpEntity reqEntity = builder.build();
        httpPost.setEntity(reqEntity);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            boolean flag = HttpStatus.SC_OK == response.getStatusLine().getStatusCode();
            result.setSuccess(flag);
            ROOT.info("请求路径：{}", url);
            ROOT.info("请求结果：{}", (flag ? "成功" : "失败"));
            if (flag) {
                HttpEntity entitys = response.getEntity();
                if (entitys != null) {
                    String resultStr = EntityUtils.toString(entitys);
                    ROOT.info("执行结果：{}",resultStr);
                    result.setReturnJson(resultStr);
                }
            }
            httpClient.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * get类型的
     *
     * @param url
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String doGet(String url) throws ClientProtocolException, IOException {
        final String[] result = {null};
        // 设置超时时间，单位是秒
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000).build();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(defaultRequestConfig);
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    boolean flag = HttpStatus.SC_OK == response.getStatusLine().getStatusCode();
                    ROOT.info("请求路径：{}", url);
                    ROOT.info("请求结果：{}", (flag ? "成功" : "失败"));
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        result[0] =  entity != null ? EntityUtils.toString(entity) : null;
                        ROOT.info("执行结果：{}",result[0]);
                        return result[0] ;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            String responseBody = httpclient.execute(httpget, responseHandler);
            result[0] = responseBody;
        } finally {
            httpclient.close();
        }
        return result[0];
    }


    public static void main(String[] args) {
        String url = "http://112.74.51.248:8281/wisdom/system/getValidateCode";
        try {
            String responseStr = doGet(url);
            System.out.println(responseStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
