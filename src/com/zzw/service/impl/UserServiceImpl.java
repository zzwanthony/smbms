package com.zzw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzw.mapper.SMBMSMapper;
import com.zzw.pojo.User;
import com.zzw.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private SMBMSMapper userMapper;

	@Override
	public User UserLogin(String userName, String userPwd) {
		return userMapper.UserLogin(userName,userPwd);
	}

	@Override
	public List<User> findAllUser(int currentPage,int pageSize) {
		return userMapper.findAllUser(currentPage-1, pageSize);
	}

	@Override
	public Integer getPageCount(int c,String queryName,Integer queryUserRole) {
		return userMapper.getPageCount(c,queryName,queryUserRole);
	}

	@Override
	public List<User> findUserBySearch(String queryname, Integer queryUserRole, Integer currentPage, Integer pageSize) {
		return userMapper.findUserBySearch(queryname,queryUserRole,currentPage,pageSize);
	}

	@Override
	public Integer deleteUser(int uid) {
		return userMapper.deleteUser(uid);
	}

	@Override
	public User findUserByid(Integer uid) {
		return userMapper.findUserByid(uid);
	}

	@Override
	public Integer updateUserMassage(User user) {
		return userMapper.updateUserMassage(user);
	}

	@Override
	public Integer addUserMassage(User user) {
		return userMapper.addUserMassage(user);
	}

	@Override
	public String userNameTest(String userCode) {
		return userMapper.userNameTest(userCode);
	}

	@Override
	public Integer passwordChange(String newPassword, int id) {
		// TODO Auto-generated method stub
		return userMapper.passwordChange(newPassword, id);
	}
}

