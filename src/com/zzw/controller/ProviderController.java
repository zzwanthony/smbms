package com.zzw.controller;

import com.alibaba.fastjson.JSON;
import com.zzw.pojo.Bill;
import com.zzw.pojo.Provider;
import com.zzw.pojo.User;
import com.zzw.service.ProviderService;
import com.zzw.util.UtilPage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/providerManager")
public class ProviderController {

	/**
	 * ����log4j��־
	 */
	private static final Logger log=Logger.getLogger(ProviderController.class);
	
	@Resource(name="providerService")
	private ProviderService providerService;
	
	UtilPage page = new UtilPage();
	/**
	 * ��ת����Ӧ��ҳ����ҳ
	 * @return
	 */
	@RequestMapping(value="/providerPage")
	public String providerPage(@RequestParam(value="queryProCode",defaultValue="") String queryProCode,
									@RequestParam(value="queryProName",defaultValue="") String queryProName,
									@RequestParam(value="pageIndex",defaultValue="1") Integer currentPage,
									Map<String,Object> map){
		List<Provider> providerList = null;
		Integer getCount = providerService.getCount(queryProCode,queryProName);
		page.setCurrentPageNo(currentPage);
		page.setTotalCount(getCount);
		providerList = providerService.findAllProvider((currentPage-1)*page.getPageSize(), page.getPageSize(),queryProCode,queryProName);
		map.put("providerList", providerList);
		map.put("currentPageNo", page.getCurrentPageNo());
		map.put("totalCount", page.getTotalCount());
		map.put("totalPageCount", page.getTotalPageCount());
		if(queryProCode!=""){
			map.put("queryProCode", queryProCode);
		}
		if(queryProName!=""){
			map.put("queryProName", queryProName);
		}
		log.info("**********���ʹ�Ӧ��ҳ�棨ģ����ѯ/�����ѯ����queryProCode="+queryProCode+" queryProName="+queryProName+" currentPage="+currentPage);
		return "providerlist";
	}
	
	/**
	 * ��ת���鿴���޸�ҳ��
	 */
	@RequestMapping("/viewOrModify")
	public String viewOrModify(@RequestParam("method")String method,
								@RequestParam("proid") Integer proid,
								Map<String, Object> map){
		Provider provider = providerService.findProviderById(proid);
		map.put("provider", provider);
		if(method.equals("view")){
			log.info("**********���ʹ�Ӧ�̣�"+proid+"����Ϣ�鿴ҳ��");
			return "providerview";
		}
		else{
			log.info("**********���ʹ�Ӧ�̣�"+proid+"����Ϣ�޸�ҳ��");
			return "providermodify";
		}
	}
	
	/**
	 * �޸���Ϣ
	 */
	@RequestMapping("/modifyProvider")
	public String modifyProvider(Provider provider,HttpSession session,
			@RequestParam(value="Picture") CommonsMultipartFile file,
			HttpServletRequest request) throws IOException{
		//�޸�ʱ��
		Timestamp time = new Timestamp(System.currentTimeMillis());
		provider.setModifyDate(time);
		//�޸���id
		User user=(User) session.getAttribute("userSession");
		if(user!=null){
			provider.setModifyBy(user.getId());	
		}else{
			provider.setModifyBy(null);
		}
		if(!file.isEmpty()){
			//�������·����ȡ����·����ͼƬ�ϴ���λ��Ԫ������
			String realUploadPath=request.getServletContext().getRealPath("/")+"picture";
			//��ȡ�ϴ���ԭͼ����Ե�ַ
			String imageUrl = uploadImage(file, realUploadPath);
			if(imageUrl.equals("false")){
				request.setAttribute("uploadFileError", " * �ϴ�ͼƬ��ʽ����ȷ��");
				request.setAttribute("provider", provider);
				return "providermodify";
			}
			provider.setProPicture(imageUrl);			
		}
		Integer modifyResult = providerService.modifyProvider(provider);
		log.info("**********"+user.getUserName()+"�޸Ĺ�Ӧ�̣�"+provider.getProCode()+"�������"+modifyResult);
		return "redirect:providerPage";
	}
	
	/**
	 * ɾ����Ӧ��
	 */
	@RequestMapping("/deletePro")
	@ResponseBody
	public Object deleteProvider(@RequestParam(value="proid",defaultValue="0") Integer proid){
		Map<String,Object> map=new HashMap<String,Object>();
		if(proid == 0){
			map.put("delResult", "notexist");
			return map;
		}
		Integer billResult = providerService.getProviderId(proid);
		if(billResult > 0){
			map.put("delResult", billResult);
			return map;
		}
		Integer delResult = providerService.deleteProvider(proid);
		if(delResult > 0){
			map.put("delResult", "true");
		}
		else
			map.put("delResult", "false");
		log.info("**********ɾ����Ӧ�̣�"+proid+"�������"+delResult);
		return map;
	}
	
	/**
	 * ��ת�����ҳ��
	 */
	@RequestMapping("/jumpToAddPage")
	public String jumpToAddPage(){
		log.info("**********���ʹ�Ӧ�����ҳ��");
		return "provideradd";
	}
	
	/**
	 * ��ӹ�Ӧ��
	 */
	@RequestMapping("/addProvider")
	public String addProvider(Provider provider,HttpSession session,
			@RequestParam(value="Picture") CommonsMultipartFile file,
			HttpServletRequest request) throws IOException{
		//�޸�ʱ��
		Timestamp time = new Timestamp(System.currentTimeMillis());
		provider.setCreationDate(time);
		//�޸���id
		User user=(User) session.getAttribute("userSession");
		if(user!=null){
			provider.setCreatedBy(user.getId());	
		}else{
			provider.setCreatedBy(null);
		}
		if(!file.isEmpty()){
			//�������·����ȡ����·����ͼƬ�ϴ���λ��Ԫ������
			String realUploadPath=request.getServletContext().getRealPath("/")+"picture";
			//��ȡ�ϴ���ԭͼ����Ե�ַ
			String imageUrl = uploadImage(file, realUploadPath);
			if(imageUrl.equals("false")){
				request.setAttribute("uploadFileError", " * �ϴ�ͼƬ��ʽ����ȷ��");
				request.setAttribute("provider", provider);
				return "provideradd";
			}
			provider.setProPicture(imageUrl);
		}
		Integer addResult = providerService.addProvider(provider);
		log.info("**********"+user.getUserName()+"��ӹ�Ӧ�̣�"+provider.getProCode()+"�������"+addResult);
		return "redirect:providerPage";
	}
	/*
	 * �ϴ�ͼƬ������ͼƬ����Ե�ַ
	 */
	private String uploadImage(CommonsMultipartFile file,String realUploadPath) throws IOException{
				//���Ŀ¼�������򴴽�Ŀ¼
		File uploadFile=new File(realUploadPath+"/rawImages");
		if(!uploadFile.exists()){
			uploadFile.mkdirs();
		}
		
		//����������
		InputStream inputStream=file.getInputStream();
		//���������ַURL
		String outputPath=realUploadPath+"/rawImages/"+file.getOriginalFilename();
		if(outputPath.endsWith(".jpg")||outputPath.endsWith(".png")||outputPath.endsWith(".gif")){
			//���������
			OutputStream outputStream=new FileOutputStream(outputPath);
			//���û�����
			byte[] buffer=new byte[1024];
			//���������뻺������������ӻ�����д��
			while((inputStream.read(buffer))>0){
				outputStream.write(buffer);
			}
			outputStream.close();
			//����ԭͼ�ϴ������Ե�ַ
			return "picture/rawImages/"+file.getOriginalFilename();
		}else{
			return "false";			
		}
	}
	/**
	 * ��֤��ӵĹ�Ӧ�̱���������Ƿ��ظ�
	 */
	@RequestMapping("/proCodeName")
	@ResponseBody
	public String proCodeName(@RequestParam("method") String method,@RequestParam("CordOrName") String name){
		String result = "";
		if(method.equals("proCode")){
			String str = providerService.proCodeTest(name);
			if(str == null)
				result = JSON.toJSONString("true");
			else
				result = JSON.toJSONString("false");
			log.info("**********��Ӧ�����ҳ����֤�����Ƿ��ظ������"+str);
		}else{
			String str = providerService.proNameTest(name);
			if(str == null)
				result = JSON.toJSONString("true");
			else
				result = JSON.toJSONString("false");
			log.info("**********��Ӧ�����ҳ����֤�����Ƿ��ظ������"+str);
		}
		return result;
	}
	/**
	 * ��ȡ��Ӧ�̵Ķ�����Ϣ
	 */
	@RequestMapping("/proGetBill")
	@ResponseBody
	public String proGetBill(@RequestParam(value="proid",defaultValue="0")Integer proid){
		if(proid == 0){
			return JSON.toJSONString("false");
		}
		List<Bill> billList = providerService.proGetBill(proid);
		log.info("**********��ȡ��Ӧ�̵Ķ�����Ϣ�����"+billList);
		
		if(billList.isEmpty()){
			return JSON.toJSONString("noBill");
		}else{
			for (Bill bill : billList) {
				System.out.println(bill.getCreationDate());
			}
			return JSON.toJSONString(billList);
		}
	}
}
