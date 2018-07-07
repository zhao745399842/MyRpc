package com.psdb.http.HTTP_RPC;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;




public class BasicAuthenticationExample { 

	  
	   public static void main(String[] args) throws Exception {
	      
	      HttpClient client = new HttpClient();
	      PostMethod postMethod=new PostMethod("https://www.om.cn/product/0-0-0-0-1-0-0-0-0-0-0");
	      //PostMethod postMethod=new PostMethod("http://http://www.pinterest.com/");
	      
	    
	      postMethod.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
	      postMethod.addRequestHeader("Accept-Encoding", "gzip, deflate, sdch");
	      postMethod.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
	      postMethod.addRequestHeader("Cache-Control","max-age=0");
	      postMethod.addRequestHeader("Connection","keep-alive");
	      postMethod.addRequestHeader("Content-Length","32");
	      postMethod.addRequestHeader("Cookie","Hm_lvt_932cd41aa09c90857d16a9dd6b041475=1489673423,1489674621; Hm_lpvt_932cd41aa09c90857d16a9dd6b041475=1489674692");
	      postMethod.addRequestHeader("Host","www.om.cn");

	     // postMethod.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
	   
	      
	      NameValuePair[] postData=new NameValuePair[3];
	      postData[0]=new NameValuePair("Language","zh");
	      postData[1]=new NameValuePair("Train","T170");
	      postData[2]=new NameValuePair("SUBMITzh","");
	      
	      //postMethod.addParameters(postData);
	      int statusCode=client.executeMethod(postMethod);
	      String dd=postMethod.getResponseBodyAsString();
	      System.out.println("response="+statusCode+"??"+dd);
	      //InputStream inputStream = postMethod.getResponseBodyAsStream();
	      
	      
	      postMethod.releaseConnection();
		   
	   } 
	  /* public static void main(String[] args) throws Exception{
		   FileOutputStream out=new FileOutputStream(new File("E:/dd.txt"));
		   HttpClient client=new HttpClient();
		   GetMethod getMethod=new GetMethod("http://10.10.10.10");
		   Integer statusCode=client.executeMethod(getMethod);
		   System.out.println(getMethod.getResponseBodyAsString());
		   InputStream input=getMethod.getResponseBodyAsStream();
		   
		   byte[] b=new byte[100];
		   int i=0;
		   while((i=input.read(b, 0, b.length))!=-1){
			   out.write(b, 0, i);
		   }
		   
	   }*/
	}
