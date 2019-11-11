package com.zzw.pojo;

import java.sql.Date;
import java.sql.Timestamp;
//import java.sql.Timestamp;

public class User {

	private Integer id;//����ID
	private String userCode;//�û�����
	private String userName;//�û�����
	private String userPassword;//�û�����
	private Integer gender;//�Ա�1:�С� 2:Ů��
	private Date birthday;//��������
	private String phone;//�ֻ�
	private String address;//��ַ
	private Integer userRole;//�û���ɫ��ȡ�Խ�ɫ��-��ɫid��
	private Integer createdBy;//�����ߣ�userId��
	private Timestamp  creationDate;//����ʱ��
	private Integer modifyBy;//�����ߣ�userId��
	private Timestamp modifyDate;//����ʱ��
	
	private int age;//����
	private String userRoleName;//�û���ɫ����
	
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getGender() {
		return gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
		
	}
	public Date getBirthday() {
		return birthday;
	}
	@SuppressWarnings("deprecation")
	public void setBirthday(Date birthday) {
		java. util. Date ud = new java. util. Date ();   
		this.setAge(ud.getYear()-birthday.getYear());
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userCode=" + userCode + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", gender=" + gender + ", birthday=" + birthday + ", phone=" + phone + ", address=" + address
				+ ", userRole=" + userRole + ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + ", age=" + age + ", userRoleName="
				+ userRoleName + "]";
	}


}
