package com.my.rpc.regist;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import com.my.rpc.msg.RequestMsg;

public class RpcHandler extends ChannelInboundHandlerAdapter{

	private ConcurrentHashMap<String, Object> catchObjMap=new ConcurrentHashMap<String, Object>();
	
	public RpcHandler() {
		// TODO Auto-generated constructor stub
		scanObjs("com.my.test.privader");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		Object result=null;
		RequestMsg request =(RequestMsg) msg;
		Object registObj=catchObjMap.get(request.getClassNamess());
	    Method m=registObj.getClass().getMethod(request.getMethodName(), request.getParmTypes());
	    result=m.invoke(registObj, request.getValues());
	    ctx.writeAndFlush(result);
	    ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
	
	public void scanObjs(String packageName){
		URL url=this.getClass().getClassLoader().getResource(packageName.replace(".", "/"));
		File dirs=new File(url.getPath());
		
		for(File f:dirs.listFiles()){
			if(f.isDirectory()){
				scanObjs(packageName+"."+f.getName());
			}else{
				String className=packageName+"."+f.getName().replace(".class", "");
				if(!catchObjMap.contains(className)){
					try{
						Class clazz=Class.forName(className);
						catchObjMap.put(clazz.getInterfaces()[0].getName(), clazz.newInstance());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
		}
	}

}
