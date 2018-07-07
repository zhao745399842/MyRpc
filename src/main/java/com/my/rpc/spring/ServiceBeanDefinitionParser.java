package com.my.rpc.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class ServiceBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	
	protected Class getBeanClass(Element element) {  
        return Service.class;  
    }  
	
    protected void doParse(Element element, BeanDefinitionBuilder bean) {  
        String interfaces = element.getAttribute("interfaces");  
        String ref = element.getAttribute("ref");  
        
        if (StringUtils.hasText(interfaces)) {  
            bean.addPropertyValue("interfaces", interfaces);  
        }  
        if (StringUtils.hasText(ref)) {  
            bean.addPropertyValue("ref", ref);  
        }  
    }  
}
