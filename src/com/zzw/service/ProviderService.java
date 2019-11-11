package com.zzw.service;

import java.util.List;

import com.zzw.pojo.Bill;
import com.zzw.pojo.Provider;

public interface ProviderService {

	public List<Provider> getAllProvider();
	public List<Provider> findAllProvider(Integer currentPage,Integer pageSize, String queryProCode, String queryProName);
	public Integer getCount(String queryProCode, String queryProName);
	public Provider findProviderById(Integer proid);
	public Integer modifyProvider(Provider provider);
	public Integer deleteProvider(Integer proid);
	public Integer getProviderId(Integer proid);
	public Integer addProvider(Provider provider);
	public String proNameTest(String name);
	public String proCodeTest(String name);
	public List<Bill> proGetBill(Integer proid);
}
