package com.hotcomm.community.resful.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonDispatcher {

	@RequestMapping(value = { "/json/dispatcher" }, method = {
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public String jsonToDis(HttpServletRequest request, String json, String url) throws IOException {
		url = "http://localhost:8281/wisdom/" + url;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		StringEntity postingString = new StringEntity(json, "utf-8");// json传递
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json;charset=UTF-8");
		HttpResponse response = httpClient.execute(post);
		return EntityUtils.toString(response.getEntity());
	}

}
