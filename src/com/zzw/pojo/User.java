package com.zzw.pojo;

import java.sql.Date;
import java.sql.Timestamp;
//import java.sql.Timestamp;

public class User {

	private Integer id;//主键ID
	private String userCode;//用户编码
	private String userName;//用户名称
	private String userPassword;//用户密码
	private Integer gender;//性别（1:男、 2:女）
	private Date birthday;//出生日期
	private String phone;//手机
	private String address;//地址
	private Integer userRole;//用户角色（取自角色表-角色id）
	private Integer createdBy;//创建者（userId）
	private Timestamp  creationDate;//创建时间
	private Integer modifyBy;//更新者（userId）
	private Timestamp modifyDate;//更新时间
	
	private int age;//年龄
	private String userRoleName;//用户角色名称
	
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
