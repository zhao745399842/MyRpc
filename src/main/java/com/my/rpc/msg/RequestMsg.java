package com.my.rpc.msg;

import java.io.Serializable;

public class RequestMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	//类名
	private String classNamess;
	//方法名
	private String methodName;
	//参数类型列表
	private Class<?>[] parmTypes;
	//参数值列表
	private Object[] values;
	public String getClassNamess() {
		return classNamess;
	}
	public void setClassNamess(String classNamess) {
		this.classNamess = classNamess;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getParmTypes() {
		return parmTypes;
	}
	public void setParmTypes(Class<?>[] parmTypes) {
		this.parmTypes = parmTypes;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	

}
