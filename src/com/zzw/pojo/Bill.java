package com.zzw.pojo;

import java.sql.Timestamp;

public class Bill {

	private Integer id;//主键ID
	private String billCode;//账单编码
	private String productName;//商品名称
	private String productDesc;//商品描述
	private String productUnit;//商品单位
	private double productCount;//商品数量
	private double totalPrice;//商品总额
	private Integer isPayment;//是否支付（1：未支付 2：已支付）
	private Integer createdBy;//创建者（userId）
	private Timestamp creationDate;//创建时间
	private Integer modifyBy;//更新者（userId）
	private Timestamp modifyDate;//更新时间
	private Integer providerId;//供应商ID
	private boolean deleteState;//删除状态，true:未删除,false:已删除
	
	private String providerName;
	
	public boolean isDeleteState() {
		return deleteState;
	}
	public void setDeleteState(boolean deleteState) {
		this.deleteState = deleteState;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public double getProductCount() {
		return productCount;
	}
	public void setProductCount(double productCount) {
		this.productCount = productCount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
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
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", billCode=" + billCode + ", productName=" + productName + ", productDesc="
				+ productDesc + ", productUnit=" + productUnit + ", productCount=" + productCount + ", totalPrice="
				+ totalPrice + ", isPayment=" + isPayment + ", createdBy=" + createdBy + ", creationDate="
				+ creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + ", providerId=" + providerId
				+ "]";
	}
}
