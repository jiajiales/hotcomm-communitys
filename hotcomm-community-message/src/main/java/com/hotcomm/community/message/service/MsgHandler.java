package com.hotcomm.community.message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

public class MsgHandler extends SimpleChannelInboundHandler<Object> {

	public static ConcurrentMap<String, Map<String, List<Channel>>> channels = new ConcurrentHashMap<>();// 小区ID->前后端->通信通道
	private static ConcurrentMap<ChannelId, String> maps = new ConcurrentHashMap<>();// 通道ID-->小区ID
	private static ConcurrentMap<ChannelId, String> source = new ConcurrentHashMap<>();// 通道ID-->0:大数据，1：后台
	private WebSocketServerHandshaker handshaker;
	private final String URI;

	public MsgHandler(String uri) {
		this.URI = uri;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpRequest) {
			handRequest(ctx, (FullHttpRequest) msg);
		}
	}

	/**
	 * 这里是保持服务器与客户端长连接 进行心跳检测 避免连接断开
	 * 
	 * @param ctx
	 * @param evt
	 * @throws Exception
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent stateEvent = (IdleStateEvent) evt;
			PingWebSocketFrame ping = new PingWebSocketFrame();
			switch (stateEvent.state()) {
			case READER_IDLE:
				System.out.println("【" + ctx.channel().remoteAddress() + "】读空闲（服务器端）");
				ctx.writeAndFlush(ping);
				break;
			case WRITER_IDLE:
				System.out.println("【" + ctx.channel().remoteAddress() + "】写空闲（客户端）");
				ctx.writeAndFlush(ping);
				break;
			case ALL_IDLE:
				System.out.println("【" + ctx.channel().remoteAddress() + "】读写空闲");
				break;
			}
		}
	}

	/**
	 * 连接上服务器
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("【handlerAdded】====>" + ctx.channel().id());
		// group.add(ctx.channel());
	}

	/**
	 * 断开连接
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("【handlerRemoved】====>" + ctx.channel().id());
		if (maps.containsKey(ctx.channel().id()) && source.containsKey(ctx.channel().id())) {
			String communityId = maps.get(ctx.channel().id());
			String sources = source.get(ctx.channel().id());
			Map<String, List<Channel>> map = channels.get(communityId);
			List<Channel> list = map.get(sources);
			list.remove(ctx.channel());
			maps.remove(ctx.channel().id());
			source.remove(ctx.channel().id());
		}
	}

	/**
	 * 连接异常 需要关闭相关资源
	 * 
	 * @param ctx
	 * @param cause
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("【系统异常】======>" + cause.toString());
		ctx.close();
		ctx.channel().close();
	}

	/**
	 * websocket第一次连接握手
	 * 
	 * @param ctx
	 * @param request
	 */
	private void handRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
		// http 解码失败
		if (!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
			sendHttpResponse(ctx, request,
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
		}
		String uri = request.uri().substring(0, request.uri().indexOf("?"));
		if (!uri.equals(URI)) {
			ctx.close();
		}
		// ctx.attr(AttributeKey.valueOf("type")).set(uri);
		// 获取websocket连接工厂
		WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(
				"ws://" + request.headers().get("Host") + URI, null, false);
		// 创建握手协议
		handshaker = factory.newHandshaker(request);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		}
		// 进行连接
		handshaker.handshake(ctx.channel(), request);
		// 获取参数
		QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
		Map<String, List<String>> map = decoder.parameters();
		List<String> communityIds = map.get("communityId");
		List<String> sources = map.get("source");
		if (!communityIds.isEmpty() && !sources.isEmpty()) {
			String communityId = communityIds.get(0);
			// channels包含小区ID
			if (!channels.containsKey(communityId)) {

				Map<String, List<Channel>> so = new HashMap<>();
				List<Channel> list = new ArrayList<>();
				list.add(ctx.channel());
				so.put(sources.get(0), list);
				channels.put(communityId, so);
				maps.put(ctx.channel().id(), communityId);
				source.put(ctx.channel().id(), sources.get(0));
			} else {
				// 存在小区ID，但不存在前（或后端）
				if (!channels.get(communityId).containsKey(sources.get(0))) {
					List<Channel> list = new ArrayList<>();
					list.add(ctx.channel());
					channels.get(communityId).put(sources.get(0), list);
					maps.put(ctx.channel().id(), communityId);
					source.put(ctx.channel().id(), sources.get(0));
				}

			}
			// 小区和前后端都存在，判断通道是都存在
			if (!channels.get(communityId).get(sources.get(0)).contains(ctx.channel())) {
				channels.get(communityId).get(sources.get(0)).add(ctx.channel());
				maps.put(ctx.channel().id(), communityId);
				source.put(ctx.channel().id(), sources.get(0));
			}
		}
	}

	private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
		// 返回应答给客户端
		if (res.status().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
		}
		// 如果是非Keep-Alive，关闭连接
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!req.headers().contains("keep-alive") || res.status().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}
}
