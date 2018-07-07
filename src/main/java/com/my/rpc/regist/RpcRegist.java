package com.my.rpc.regist;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.rpc.spring.Service;
import com.my.test.api.User;

public class RpcRegist {
	
	public void start(int port){
	   EventLoopGroup boss=new NioEventLoopGroup();
	   EventLoopGroup work=new NioEventLoopGroup();
	   
	   try{
		   ServerBootstrap b=new ServerBootstrap();
		   b.group(boss, work).channel(NioServerSocketChannel.class)
		   .childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					// TODO Auto-generated method stub
					ChannelPipeline pipeline=ch.pipeline();
					//解决拆包粘包
					pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
					pipeline.addLast(new LengthFieldPrepender(4));
					
					//编码解码
					pipeline.addLast(new ObjectEncoder());
					pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
					
					pipeline.addLast(new RpcHandler());
				}
		   }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
		   
		   ChannelFuture future=b.bind(port).sync();
		   System.out.println("服务启动。");
		   future.channel().closeFuture().sync();
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   boss.shutdownGracefully();
		   work.shutdownGracefully();
	   }
	}
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext contex=new ClassPathXmlApplicationContext("config/application.xml");
		Service p = (Service)contex.getBean("userService");
		
		User u = (User)contex.getBean(p.getRef());
		
		System.out.println("注册服务的接口"+p.getInterfaces()+"||"+u.getName("ffff"));
		RpcRegist regist=new RpcRegist();
		regist.start(9090);
	}

}
