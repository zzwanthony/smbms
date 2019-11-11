package com.zzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzw.pojo.User;

public interface SMBMSMapper {

	//������֤
	public User UserLogin(String userName, String userPwd);
	//��ȡuser������������
	public int getPageCount(@Param("likeCount") int c,
								@Param("queryName") String queryName,
								@Param("queryUserRole") Integer queryUserRole);
	//��ȡ��ǰҳ���û�
	public List<User> findAllUser(int currentPage, int pageSize);
	//ͨ���û������û���ɫ��ҳ���ȡ�û�ҳ��
	public List<User> findUserBySearch(@Param("queryName") String queryName,
										@Param("queryUserRole") Integer queryUserRole,
										@Param("currentPage") Integer currentPage,
										@Param("pageSize") Integer pageSize);
	//ɾ���û���Ϣ
	public Integer deleteUser(int uid);
	//�����û�id�����û���Ϣ
	public User findUserByid(Integer uid);
	//�޸��û���Ϣ
	public Integer updateUserMassage(User user);
	//�����û���Ϣ
	public Integer addUserMassage(User user);
	//��֤�û������Ƿ����
	public String userNameTest(String userCode);
	//�޸�����
	public Integer passwordChange(String newPassword, int id);
	
}
