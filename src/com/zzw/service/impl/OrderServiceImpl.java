package com.zzw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzw.mapper.OrderMapper;
import com.zzw.pojo.Bill;
import com.zzw.service.OrderService;
@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Resource
	private OrderMapper orderMapper;
	
	@Override
	public List<Bill> findAllBill(int currentPage,int pageSize) {
		return orderMapper.findAllBill(currentPage,pageSize);
	}

	@Override
	public Integer getPageCount(int i, String queryProductName, Integer queryProviderId, Integer queryIsPayment, String startTime, String endTime) {
		return orderMapper.getPageCount(i, queryProductName, queryProviderId, queryIsPayment, startTime, endTime);
	}

	@Override
	public List<Bill> findOrderBySearch(String queryProductName, 
										Integer queryProviderId, 
										Integer queryIsPayment, 
										String startTime, 
										String endTime,
										int currentPage, int pageSize) {
		return orderMapper.findOrderBySearch(queryProductName,queryProviderId,queryIsPayment, startTime, endTime,currentPage,pageSize);
	}

	@Override
	public Bill findBillById(int billid) {
		return orderMapper.findBillById(billid) ;
	}

	@Override
	public Integer modifyBill(Bill bill) {
		return orderMapper.modifyBill(bill);
	}

	@Override
	public Integer deleteBill(Integer billid) {
		return orderMapper.deleteBill(billid);
	}

	@Override
	public Integer addBill(Bill bill) {
		// TODO Auto-generated method stub
		return orderMapper.addBill(bill);
	}

	@Override
	public Integer getLastBillId() {
		// TODO Auto-generated method stub
		return orderMapper.getLastBillId();
	}

}
