package com.my.rpc.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport{

	public void init() {
		// TODO Auto-generated method stub
		registerBeanDefinitionParser("myservice", new ServiceBeanDefinitionParser());  
	}

}
