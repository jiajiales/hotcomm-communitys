package com.hotcomm.community.message.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.framework.web.exception.HKException;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Service
public class MsgServiceImpl extends BaseService {

	public void sendMessage(Message msg) throws HKException {
		ConcurrentMap<String, Map<String, List<Channel>>> channels = MsgHandler.channels;
		ObjectMapper mapper = new ObjectMapper();
		if (msg.getCommunityId() == null) {
			throw exceptionManager.configLog(service).serviceRecord("MSG0001");
		}
		if (msg.getSource() == null) {
			throw exceptionManager.configLog(service).serviceRecord("MSG0002");
		}
		Map<String, List<Channel>> map = channels.get(msg.getCommunityId());
		if (map != null && map.containsKey(msg.getSource())) {
			List<Channel> list = map.get(msg.getSource());
			try {
				String json = mapper.writeValueAsString(msg);
				//String json=JSONObject.toJSONString(msg);
				if (list.size() != 0) {
					for (Channel channel : list) {
						TextWebSocketFrame tws = new TextWebSocketFrame(json);
						channel.writeAndFlush(tws);
						tws.retain();
					}
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
}
