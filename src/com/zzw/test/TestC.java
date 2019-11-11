package com.zzw.test;


import javax.annotation.Resource;

import com.zzw.pojo.User;
import com.zzw.service.UserService;

public class TestC {

	@Resource(name="userService")
	static UserService userService;
	public static void main(String[] args) {
		System.out.println("**************login***************" );
		User user = userService.UserLogin("admin","1234567");
		if(null != user){//登录成功
			System.out.println("登入成功");
		}else{//登入失败
			System.out.println("登入失败");
		}
	}
}
