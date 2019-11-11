package com.zzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzw.pojo.User;

public interface SMBMSMapper {

	//登入验证
	public User UserLogin(String userName, String userPwd);
	//获取user表总数据条数
	public int getPageCount(@Param("likeCount") int c,
								@Param("queryName") String queryName,
								@Param("queryUserRole") Integer queryUserRole);
	//获取当前页面用户
	public List<User> findAllUser(int currentPage, int pageSize);
	//通过用户名、用户角色、页码获取用户页面
	public List<User> findUserBySearch(@Param("queryName") String queryName,
										@Param("queryUserRole") Integer queryUserRole,
										@Param("currentPage") Integer currentPage,
										@Param("pageSize") Integer pageSize);
	//删除用户信息
	public Integer deleteUser(int uid);
	//根据用户id查找用户信息
	public User findUserByid(Integer uid);
	//修改用户信息
	public Integer updateUserMassage(User user);
	//增加用户信息
	public Integer addUserMassage(User user);
	//验证用户编码是否存在
	public String userNameTest(String userCode);
	//修改密码
	public Integer passwordChange(String newPassword, int id);
	
}
