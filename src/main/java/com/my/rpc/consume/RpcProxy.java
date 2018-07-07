package com.my.rpc.consume;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.my.rpc.msg.RequestMsg;

public class RpcProxy {
	
	public static  Object createInstance(final Class clazz){
		
		return Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz}, new InvocationHandler(){

			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// TODO Auto-generated method stub
				return getRpcMethod(clazz,method,args);
			}
		});
	}
	public static Object getRpcMethod(Class clazz,Method method,Object[] args){
		RequestMsg request=new RequestMsg();
		request.setClassNamess(clazz.getName());
		request.setMethodName(method.getName());
		request.setParmTypes(method.getParameterTypes());
		request.setValues(args);
		final RpcProxyHandler proxyHandler=new RpcProxyHandler();
		EventLoopGroup work=new NioEventLoopGroup();
		try{
			Bootstrap b=new Bootstrap();
			b.group(work).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {

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
					
					pipeline.addLast(proxyHandler);
				}
			
			}).option(ChannelOption.TCP_NODELAY, true);
			
			ChannelFuture future=b.connect("127.0.0.1", 9090).sync();
			future.channel().writeAndFlush(request);
			future.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			work.shutdownGracefully();
		}
		return proxyHandler.getResult();
	}
	
}
