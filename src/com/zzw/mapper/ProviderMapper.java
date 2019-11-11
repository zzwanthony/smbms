package com.zzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzw.pojo.Provider;

public interface ProviderMapper {

	//��ȡ���й�Ӧ������
	public List<Provider> getAllProvider();
	//��ȡ��Ӧ������������
	public Integer getCount(@Param("queryProCode") String queryProCode, 
							@Param("queryProName") String queryProName);
	//��ȡ��Ӧ��ҳ��չʾ��Ϣ
	public List<Provider> findAllProvider(@Param("currentPage") Integer currentPage, 
											@Param("pageSize") Integer pageSize, 
											@Param("queryProCode") String queryProCode, 
											@Param("queryProName") String queryProName);
	//����ID��ȡ��Ӧ����Ϣ
	public Provider findProviderById(Integer proid);
	//�޸Ĺ�Ӧ����Ϣ
	public Integer modifyProvider(Provider provider);
	//��ӹ�Ӧ�̵���Ϣ
	public Integer addProvider(Provider provider);
	//ɾ����Ӧ����Ϣ
	public Integer deleteProvider(Integer proid);
	//��Ӧ�̱�����֤�ظ�
	public String proCodeTest(String name);
	//��Ӧ��������֤�ظ�
	public String proNameTest(String name);
}
