package com.zzw.pojo;

import java.sql.Timestamp;

public class Provider {

	private Integer id;//主键ID
	private String proCode;//供应商编码
	private String proName;//供应商名称
	private String proDesc;//供应商详细描述
	private String proContact;//供应商联系人
	private String proPhone;//联系电话
	private String proAddress;//地址
	private String proFax;//传真
	private Integer createdBy;//创建者（userId）
	private Timestamp creationDate;//创建时间
	private Timestamp modifyDate;//更新时间
	private Integer modifyBy;//更新者（userId）
	private boolean deleteState;//删除状态，true:未删除,false:已删除
	private String proPicture;//供应商执照图片
	
	public String getProPicture() {
		return proPicture;
	}
	public void setProPicture(String proPicture) {
		this.proPicture = proPicture;
	}
	public boolean isDeleteState() {
		return deleteState;
	}
	public void setDeleteState(boolean deleteState) {
		this.deleteState = deleteState;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getProContact() {
		return proContact;
	}
	public void setProContact(String proContact) {
		this.proContact = proContact;
	}
	public String getProPhone() {
		return proPhone;
	}
	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}
	public String getProAddress() {
		return proAddress;
	}
	public void setProAddress(String proAddress) {
		this.proAddress = proAddress;
	}
	public String getProFax() {
		return proFax;
	}
	public void setProFax(String proFax) {
		this.proFax = proFax;
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
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	@Override
	public String toString() {
		return "Provider [id=" + id + ", proCode=" + proCode + ", proName=" + proName + ", proDesc=" + proDesc
				+ ", proContact=" + proContact + ", proPhone=" + proPhone + ", proAddress=" + proAddress + ", proFax="
				+ proFax + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", modifyDate=" + modifyDate
				+ ", modifyBy=" + modifyBy + "]";
	}
	
}
