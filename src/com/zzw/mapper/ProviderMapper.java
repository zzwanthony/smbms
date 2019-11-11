package com.zzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzw.pojo.Provider;

public interface ProviderMapper {

	//获取所有供应商名称
	public List<Provider> getAllProvider();
	//获取供应商总数据条数
	public Integer getCount(@Param("queryProCode") String queryProCode, 
							@Param("queryProName") String queryProName);
	//获取供应商页面展示信息
	public List<Provider> findAllProvider(@Param("currentPage") Integer currentPage, 
											@Param("pageSize") Integer pageSize, 
											@Param("queryProCode") String queryProCode, 
											@Param("queryProName") String queryProName);
	//根据ID获取供应商信息
	public Provider findProviderById(Integer proid);
	//修改供应商信息
	public Integer modifyProvider(Provider provider);
	//添加供应商的信息
	public Integer addProvider(Provider provider);
	//删除供应商信息
	public Integer deleteProvider(Integer proid);
	//供应商编码验证重复
	public String proCodeTest(String name);
	//供应名称码验证重复
	public String proNameTest(String name);
}
