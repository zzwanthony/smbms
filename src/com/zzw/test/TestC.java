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
		if(null != user){//��¼�ɹ�
			System.out.println("����ɹ�");
		}else{//����ʧ��
			System.out.println("����ʧ��");
		}
	}
}
