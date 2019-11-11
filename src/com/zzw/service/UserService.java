package com.zzw.service;

import java.util.List;

import com.zzw.pojo.User;
public interface UserService {

	User UserLogin(String userName, String userPwd);

	List<User> findAllUser(int currentPage,int pageSize);

	Integer getPageCount(int c,String queryName,Integer queryUserRole);//用户数据总条数
	
	List<User> findUserBySearch(String queryname, Integer queryUserRole, Integer currentPage, Integer pageSize);

	Integer deleteUser(int uid);

	User findUserByid(Integer uid);

	Integer updateUserMassage(User user);

	Integer addUserMassage(User user);

	String userNameTest(String userCode);

	Integer passwordChange(String newPassword, int id);

}
