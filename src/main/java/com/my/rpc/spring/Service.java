package com.my.rpc.spring;

public class Service {
	
	private String id;
	/**
	 * 服务暴露的接口类
	 */
	private String interfaces;
	/**
	 * 服务指向的具体的bean
	 */
	private String ref;
	
	
	public String getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
