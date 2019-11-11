package com.zzw.service;

import java.util.List;

import com.zzw.pojo.Bill;

public interface OrderService {
	public List<Bill> findAllBill(int i, int j);

	public Integer getPageCount(int i, String queryProductName, Integer queryProviderId, 
								Integer queryIsPayment, String startTime, String endTime);

	public List<Bill> findOrderBySearch(String queryProductName, 
											Integer queryProviderId, 
											Integer queryIsPayment, 
											String startTime, 
											String endTime, 
											int currentPage,
											int pageSize);

	public Bill findBillById(int billid);

	public Integer modifyBill(Bill bill);

	public Integer deleteBill(Integer billid);

	public Integer addBill(Bill bill);

	public Integer getLastBillId();

}
