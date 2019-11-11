package com.zzw.pojo;

import java.sql.Timestamp;

public class Provider {

	private Integer id;//����ID
	private String proCode;//��Ӧ�̱���
	private String proName;//��Ӧ������
	private String proDesc;//��Ӧ����ϸ����
	private String proContact;//��Ӧ����ϵ��
	private String proPhone;//��ϵ�绰
	private String proAddress;//��ַ
	private String proFax;//����
	private Integer createdBy;//�����ߣ�userId��
	private Timestamp creationDate;//����ʱ��
	private Timestamp modifyDate;//����ʱ��
	private Integer modifyBy;//�����ߣ�userId��
	private boolean deleteState;//ɾ��״̬��true:δɾ��,false:��ɾ��
	private String proPicture;//��Ӧ��ִ��ͼƬ
	
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
