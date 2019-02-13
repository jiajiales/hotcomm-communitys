package com.hotcomm.framework.utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotcomm.framework.comm.FileUploadResponse;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.web.exception.HKException;

/**
 * 文件上传辅助类
 * @author yuanyuanxing
 * @since 2018年11月2日 上午11:00:00
 */
public class FileUploadHelper {

	private static final String FILE_UPLOAD_REQUEST_URL = "http://39.108.54.252:8185/hot/file/upload";

	/**
	 * 上传文件
	 * @param file 文件
	 * @param moduleName 模块名称(可为null或空)
	 * @return FileUploadResponse
	 * @throws HKException
	 */
	public static FileUploadResponse upload(MultipartFile file, String moduleName) throws HKException {
		HotHttpResponse result = new HotHttpResponse();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(1000).setSocketTimeout(10000).setRedirectsEnabled(true).build();
		HttpPost httpPost = new HttpPost(FILE_UPLOAD_REQUEST_URL);
		httpPost.setConfig(requestConfig);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName("UTF-8"));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		try {
			builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
			throw new HKException("FU0001", "文件上传失败");
		}

		if (moduleName != null && moduleName.length() != 0)
			builder.addPart("moduleName", new StringBody(moduleName.toString(), ContentType.TEXT_PLAIN));

		builder.addPart("source", new StringBody("1".toString(), ContentType.TEXT_PLAIN));

		HttpEntity reqEntity = builder.build();
		httpPost.setEntity(reqEntity);

		try {
			HttpResponse response = httpClient.execute(httpPost);
			boolean flag = HttpStatus.SC_OK == response.getStatusLine().getStatusCode();
			result.setSuccess(flag);

			if (flag) {
				HttpEntity entitys = response.getEntity();

				if (entitys != null)
					result.setReturnJson(EntityUtils.toString(entitys));
			}

			httpClient.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new HKException("FU0001", "文件上传失败");
		} catch (IOException e) {
			e.printStackTrace();
			throw new HKException("FU0001", "文件上传失败");
		}

		FileUploadResponse fileUploadResponse = null;

		if (result.isSuccess()) {
			ObjectMapper mapper = new ObjectMapper();

			try {
				String dataStr = JSONObject.toJSON(result.getReturnJson()).toString();
				fileUploadResponse = mapper.readValue(dataStr, new TypeReference<FileUploadResponse>() {});
			} catch (IOException e) {
				e.printStackTrace();
				throw new HKException("FU0002", "解析上传文件后的响应失败");
			}
		}

		return fileUploadResponse;
	}


	public static MultipartFile base64ToMultipart(String base64) {
	    try {
	        String[] baseStrs = base64.split(",");
	 
	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = new byte[0];
	        b = decoder.decodeBuffer(baseStrs[1]);
	 
	        for(int i = 0; i < b.length; ++i) {
	            if (b[i] < 0) {
	                b[i] += 256;
	            }
	        }
	        return new BASE64DecodedMultipartFile(b, baseStrs[0]);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
}
