package com.zzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzw.pojo.Bill;

public interface OrderMapper {

	//分页查询，返回定单信息
	public List<Bill> findAllBill(int currentPage, int pageSize);
	//获取定单信息总条数/模糊查询总条数
	public Integer getPageCount(@Param("likeCount") int i, 
								@Param("queryProductName") String queryProductName, 
								@Param("queryProviderId") Integer queryProviderId,
								@Param("queryIsPayment") Integer queryIsPayment, 
								@Param("startTime") String startTime, 
								@Param("endTime") String endTime);
	//模糊查询和分页查询
	public List<Bill> findOrderBySearch(@Param("queryProductName") String queryProductName, 
											@Param("queryProviderId") Integer queryProviderId,
											@Param("queryIsPayment") Integer queryIsPayment, 
											@Param("startTime") String startTime, 
											@Param("endTime") String endTime,
											@Param("currentPage") int currentPage,
											@Param("pageSize") int pageSize);
	//通过ID获取Bill信息
	public Bill findBillById(int billid);
	//修改定单信息
	public Integer modifyBill(Bill bill);
	//删除定单
	public Integer deleteBill(Integer billid);
	//添加定单
	public Integer addBill(Bill bill);
	//获取所有定单的供应商id
	public Integer getProviderId(Integer proId);
	//通过供应商ID获取所拥有定单信息
	public List<Bill> proGetBill(Integer proid);
	//获取最后一条定单id
	public Integer getLastBillId();
}
