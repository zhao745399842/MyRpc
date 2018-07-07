package com.my.test.consume;

import com.my.rpc.consume.RpcProxy;
import com.my.test.api.User;

public class UserInfoConsume {

	public static void main(String[] args) {
		User u=(User) RpcProxy.createInstance(User.class);
		System.out.println(u.getName("zw555"));
	}
}
