package com.hotcomm.community.message.service;

import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

@Component
public class MsgServer {

	public void start() {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup work = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, work);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// HttpServerCodec：将请求和应答消息解码为HTTP消息 
					ch.pipeline().addLast(new HttpServerCodec());
					// HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
					ch.pipeline().addLast(new HttpObjectAggregator(65536));
					// ChunkedWriteHandler：向客户端发送HTML5文件
					ch.pipeline().addLast(new ChunkedWriteHandler());
					// 进行设置心跳检测
					ch.pipeline().addLast(new IdleStateHandler(0,0,60*10, TimeUnit.SECONDS));
					//业务逻辑处理
					ch.pipeline().addLast(new MsgHandler("/ws"));
				}
			});
			ChannelFuture future = bootstrap.bind(8282).sync();
			if (future.isSuccess()) {
				System.out.println("netty server start ------------------");
			}
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}
	}
}
