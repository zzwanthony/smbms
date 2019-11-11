package com.zzw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzw.mapper.OrderMapper;
import com.zzw.mapper.ProviderMapper;
import com.zzw.pojo.Bill;
import com.zzw.pojo.Provider;
import com.zzw.service.ProviderService;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService{

	@Resource
	private ProviderMapper providerMapper;
	@Resource
	private OrderMapper orderMapper;
	
	@Override
	public List<Provider> getAllProvider() {
		return providerMapper.getAllProvider();
	}
	@Override
	public List<Provider> findAllProvider(Integer currentPage, Integer pageSize, String queryProCode, String queryProName) {
		return providerMapper.findAllProvider(currentPage, pageSize, queryProCode, queryProName);
	}
	@Override
	public Integer getCount(String queryProCode, String queryProName) {
		return providerMapper.getCount(queryProCode, queryProName);
	}
	@Override
	public Provider findProviderById(Integer proid) {
		return providerMapper.findProviderById(proid);
	}
	@Override
	public Integer modifyProvider(Provider provider) {
		return providerMapper.modifyProvider(provider);
	}
	@Override
	public Integer deleteProvider(Integer proid) {
		return providerMapper.deleteProvider(proid);
	}
	@Override
	public Integer getProviderId(Integer proId) {
		return orderMapper.getProviderId(proId);
	}
	@Override
	public Integer addProvider(Provider provider) {
		return providerMapper.addProvider(provider);
	}
	@Override
	public String proNameTest(String name) {
		return providerMapper.proNameTest(name);
	}
	@Override
	public String proCodeTest(String name) {
		return providerMapper.proCodeTest(name);
	}
	@Override
	public List<Bill> proGetBill(Integer proid) {
		// TODO Auto-generated method stub
		return orderMapper.proGetBill(proid);
	}
}
