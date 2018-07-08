package com.my.test.consume;

import com.my.rpc.consume.RpcProxy;
import com.my.test.api.User;

/**
 * dfsdfsdf
 */
public class UserInfoConsume {

	public static void main(String[] args) {
		User u=(User) RpcProxy.createInstance(User.class);
		//修改了一bug
		System.out.println(u.getName("zw555"));
	}
}
