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
	 * 加入log4j日志
	 */
	private static final Logger log=Logger.getLogger(ProviderController.class);
	
	@Resource(name="providerService")
	private ProviderService providerService;
	
	UtilPage page = new UtilPage();
	/**
	 * 跳转到供应商页面首页
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
		log.info("**********访问供应商页面（模糊查询/分面查询），queryProCode="+queryProCode+" queryProName="+queryProName+" currentPage="+currentPage);
		return "providerlist";
	}
	
	/**
	 * 跳转到查看、修改页面
	 */
	@RequestMapping("/viewOrModify")
	public String viewOrModify(@RequestParam("method")String method,
								@RequestParam("proid") Integer proid,
								Map<String, Object> map){
		Provider provider = providerService.findProviderById(proid);
		map.put("provider", provider);
		if(method.equals("view")){
			log.info("**********访问供应商（"+proid+"）信息查看页面");
			return "providerview";
		}
		else{
			log.info("**********访问供应商（"+proid+"）信息修改页面");
			return "providermodify";
		}
	}
	
	/**
	 * 修改信息
	 */
	@RequestMapping("/modifyProvider")
	public String modifyProvider(Provider provider,HttpSession session,
			@RequestParam(value="Picture") CommonsMultipartFile file,
			HttpServletRequest request) throws IOException{
		//修改时间
		Timestamp time = new Timestamp(System.currentTimeMillis());
		provider.setModifyDate(time);
		//修改人id
		User user=(User) session.getAttribute("userSession");
		if(user!=null){
			provider.setModifyBy(user.getId());	
		}else{
			provider.setModifyBy(null);
		}
		if(!file.isEmpty()){
			//根据相对路径获取绝对路径，图片上传后位于元数据中
			String realUploadPath=request.getServletContext().getRealPath("/")+"picture";
			//获取上传后原图的相对地址
			String imageUrl = uploadImage(file, realUploadPath);
			if(imageUrl.equals("false")){
				request.setAttribute("uploadFileError", " * 上传图片格式不正确！");
				request.setAttribute("provider", provider);
				return "providermodify";
			}
			provider.setProPicture(imageUrl);			
		}
		Integer modifyResult = providerService.modifyProvider(provider);
		log.info("**********"+user.getUserName()+"修改供应商（"+provider.getProCode()+"）结果："+modifyResult);
		return "redirect:providerPage";
	}
	
	/**
	 * 删除供应商
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
		log.info("**********删除供应商（"+proid+"）结果："+delResult);
		return map;
	}
	
	/**
	 * 跳转到添加页面
	 */
	@RequestMapping("/jumpToAddPage")
	public String jumpToAddPage(){
		log.info("**********访问供应商添加页面");
		return "provideradd";
	}
	
	/**
	 * 添加供应商
	 */
	@RequestMapping("/addProvider")
	public String addProvider(Provider provider,HttpSession session,
			@RequestParam(value="Picture") CommonsMultipartFile file,
			HttpServletRequest request) throws IOException{
		//修改时间
		Timestamp time = new Timestamp(System.currentTimeMillis());
		provider.setCreationDate(time);
		//修改人id
		User user=(User) session.getAttribute("userSession");
		if(user!=null){
			provider.setCreatedBy(user.getId());	
		}else{
			provider.setCreatedBy(null);
		}
		if(!file.isEmpty()){
			//根据相对路径获取绝对路径，图片上传后位于元数据中
			String realUploadPath=request.getServletContext().getRealPath("/")+"picture";
			//获取上传后原图的相对地址
			String imageUrl = uploadImage(file, realUploadPath);
			if(imageUrl.equals("false")){
				request.setAttribute("uploadFileError", " * 上传图片格式不正确！");
				request.setAttribute("provider", provider);
				return "provideradd";
			}
			provider.setProPicture(imageUrl);
		}
		Integer addResult = providerService.addProvider(provider);
		log.info("**********"+user.getUserName()+"添加供应商（"+provider.getProCode()+"）结果："+addResult);
		return "redirect:providerPage";
	}
	/*
	 * 上传图片并返回图片的相对地址
	 */
	private String uploadImage(CommonsMultipartFile file,String realUploadPath) throws IOException{
				//如果目录不存在则创建目录
		File uploadFile=new File(realUploadPath+"/rawImages");
		if(!uploadFile.exists()){
			uploadFile.mkdirs();
		}
		
		//创建输入流
		InputStream inputStream=file.getInputStream();
		//生成输出地址URL
		String outputPath=realUploadPath+"/rawImages/"+file.getOriginalFilename();
		if(outputPath.endsWith(".jpg")||outputPath.endsWith(".png")||outputPath.endsWith(".gif")){
			//创建输出流
			OutputStream outputStream=new FileOutputStream(outputPath);
			//设置缓冲区
			byte[] buffer=new byte[1024];
			//输入流读入缓冲区，输出流从缓冲区写出
			while((inputStream.read(buffer))>0){
				outputStream.write(buffer);
			}
			outputStream.close();
			//返回原图上传后的相对地址
			return "picture/rawImages/"+file.getOriginalFilename();
		}else{
			return "false";			
		}
	}
	/**
	 * 验证添加的供应商编码和名称是否重复
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
			log.info("**********供应商添加页面验证编码是否重复结果："+str);
		}else{
			String str = providerService.proNameTest(name);
			if(str == null)
				result = JSON.toJSONString("true");
			else
				result = JSON.toJSONString("false");
			log.info("**********供应商添加页面验证名称是否重复结果："+str);
		}
		return result;
	}
	/**
	 * 获取供应商的定单信息
	 */
	@RequestMapping("/proGetBill")
	@ResponseBody
	public String proGetBill(@RequestParam(value="proid",defaultValue="0")Integer proid){
		if(proid == 0){
			return JSON.toJSONString("false");
		}
		List<Bill> billList = providerService.proGetBill(proid);
		log.info("**********获取供应商的定单信息结果："+billList);
		
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
