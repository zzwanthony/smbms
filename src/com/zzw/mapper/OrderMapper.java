package com.zzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzw.pojo.Bill;

public interface OrderMapper {

	//��ҳ��ѯ�����ض�����Ϣ
	public List<Bill> findAllBill(int currentPage, int pageSize);
	//��ȡ������Ϣ������/ģ����ѯ������
	public Integer getPageCount(@Param("likeCount") int i, 
								@Param("queryProductName") String queryProductName, 
								@Param("queryProviderId") Integer queryProviderId,
								@Param("queryIsPayment") Integer queryIsPayment, 
								@Param("startTime") String startTime, 
								@Param("endTime") String endTime);
	//ģ����ѯ�ͷ�ҳ��ѯ
	public List<Bill> findOrderBySearch(@Param("queryProductName") String queryProductName, 
											@Param("queryProviderId") Integer queryProviderId,
											@Param("queryIsPayment") Integer queryIsPayment, 
											@Param("startTime") String startTime, 
											@Param("endTime") String endTime,
											@Param("currentPage") int currentPage,
											@Param("pageSize") int pageSize);
	//ͨ��ID��ȡBill��Ϣ
	public Bill findBillById(int billid);
	//�޸Ķ�����Ϣ
	public Integer modifyBill(Bill bill);
	//ɾ������
	public Integer deleteBill(Integer billid);
	//��Ӷ���
	public Integer addBill(Bill bill);
	//��ȡ���ж����Ĺ�Ӧ��id
	public Integer getProviderId(Integer proId);
	//ͨ����Ӧ��ID��ȡ��ӵ�ж�����Ϣ
	public List<Bill> proGetBill(Integer proid);
	//��ȡ���һ������id
	public Integer getLastBillId();
}
