package com.zzw.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzw.pojo.Bill;
import com.zzw.pojo.Provider;
import com.zzw.pojo.User;
import com.zzw.service.OrderService;
import com.zzw.service.ProviderService;
import com.zzw.util.UtilPage;
/**
 * ��������ҳ��
 * @author ŭ��İ�����
 *
 */
@Controller
@RequestMapping("/orderManager")
public class OrderController {

	/**
	 * ����log4j��־
	 */
	private static final Logger log=Logger.getLogger(OrderController.class);
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	@Resource(name="providerService")
	private ProviderService providerService;
	private UtilPage page = new UtilPage();
	
	/**
	 * ��ת����������ҳ����ҳ
	 * @return
	 */
	@RequestMapping(value="/OrderPage")
	public String OrderPage(Map<String,Object> map){
		//���õ�ǰҳ��Ϊ1
		page.setCurrentPageNo(1);
		Integer tolCount = orderService.getPageCount(1,null,null,null,null,null);
		page.setTotalCount(tolCount);
		List<Bill> bill = orderService.findAllBill(0,page.getPageSize());
		List<Provider> providerList = providerService.getAllProvider();
		map.put("billList",bill);
		map.put("providerList", providerList);
		map.put("currentPageNo", page.getCurrentPageNo());
		map.put("totalCount", page.getTotalCount());
		map.put("totalPageCount", page.getTotalPageCount());
		log.info("**********���ʶ�������ҳ��");
		return "billlist";
	}
	/**
	 * ��ҳ��ת/��ѯ��ת
	 */
	@RequestMapping(value="/orderSearch")
	public String orderSearch(@RequestParam(value="queryProductName",defaultValue="") String queryProductName,
						@RequestParam(value="queryProviderId",defaultValue="0") Integer queryProviderId,
						@RequestParam(value="queryIsPayment",defaultValue="0") Integer queryIsPayment,
						@RequestParam(value="startTime",defaultValue="") String startTime,
						@RequestParam(value="endTime",defaultValue="") String endTime,
						@RequestParam(value="pageIndex",defaultValue="1") Integer currentPage,
						Map<String,Object> map){
		page.setCurrentPageNo(currentPage);
		//��ȡģ����ѯ�û�����������
		Integer tolCount;
		
		if(queryProductName.equals("") && queryProviderId == 0 && queryIsPayment == 0 && startTime.equals("") && endTime.equals("")){
			tolCount = orderService.getPageCount(1,null,null,null,null,null);
		}else{
			tolCount = orderService.getPageCount(2,queryProductName,queryProviderId,queryIsPayment,startTime,endTime);
		}
		
		page.setTotalCount(tolCount);//ģ����ѯ������������
		Integer pageSize = page.getPageSize();
		List<Bill> billList = orderService.findOrderBySearch(queryProductName,queryProviderId,queryIsPayment,startTime,endTime,(currentPage-1)*pageSize, pageSize);
		//��ȡȫ����ɫ��Ϣ
		List<Provider> providerList = providerService.getAllProvider();

		map.put("billList", billList);
		map.put("providerList",providerList);
		
		map.put("queryProductName",queryProductName);
		map.put("queryProviderId",queryProviderId);
		map.put("queryIsPayment",queryIsPayment);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		//��ҳ��Ϣ
		map.put("currentPageNo", currentPage);
		map.put("totalCount", page.getTotalCount());
		map.put("totalPageCount", page.getTotalPageCount());
		log.info("**********������ҳ��ģ����ѯ/ҳ���ҳ��ת��queryProductName="+queryProductName+" queryProviderId="+queryProviderId+" queryIsPayment="+queryIsPayment
				+" startTime="+startTime+" endTime="+endTime);
		return "billlist";
	}
	
	/**
	 * ��ת����Ϣ��ѯ����Ϣ�޸�ҳ��
	 */
	@RequestMapping("/jumpToFindAndModify")
	public String jumpToFindAndModify(@RequestParam("method")String method,
										@RequestParam("billid") int billid,
										Map<String,Object> map){
		Bill bill = orderService.findBillById(billid);
		map.put("bill", bill);
		if(method.equals("view")){
			log.info("*********���ʶ�����"+billid+"����ѯҳ��");
			return "billview";
		}
		else{
//			if(bill.getIsPayment()==2){
//				return "redirect:OrderPage";
//			}
			map.put("providerList", providerService.getAllProvider());
			log.info("*********���ʶ�����"+billid+"���޸�ҳ��");
			return "billmodify";
		}
	}
	/**
	 * �޸Ķ���
	 */
	@RequestMapping("/modifyBill")
	public String modifyBill(Bill bill,HttpSession session){
		//�޸�ʱ��
		Timestamp time = new Timestamp(System.currentTimeMillis());
		bill.setModifyDate(time);
		//�޸���id
		User user=(User) session.getAttribute("userSession");
		
		if(user!=null){
			bill.setModifyBy(user.getId());			
		}else{
			bill.setModifyBy(null);
		}
		Integer result = orderService.modifyBill(bill);
		log.info("**********"+user.getUserName()+" �޸Ķ������:"+result);
		return "redirect:OrderPage";
	}
	/**
	 * ɾ������
	 */
	@RequestMapping("/deleteBill")
	@ResponseBody
	public Object deleteBill(@RequestParam("billid") Integer billid){
		Map<String,Object> map=new HashMap<String,Object>();
		Integer result = orderService.deleteBill(billid);
		if(result>0){
			map.put("delResult","true");
		}else{
			map.put("delResult","notexist");
		}
		log.info("**********ɾ��������"+billid+"�������"+result);
		return map;
	}
	/**
	 * ��ת�����ҳ��
	 */
	@RequestMapping("/jumpToAddPage")
	public String jumpToAddPage(Map<String,Object> map){
		map.put("providerList", providerService.getAllProvider());
		Integer lastBill = orderService.getLastBillId();
		map.put("billC","BILL2018_"+String.format("%03d", lastBill+1));
		log.info("**********���ʶ������ҳ��");
		return "billadd";
	}
	
	/**
	 * ��֤����code�Ƿ��ظ�
	 */
	/*@RequestMapping("/getBillCode")
	public String getBillCode(@RequestParam(value="BillCode",defaultValue="")String billCode){
		if(){
			
		}
		return "";
	}*/
	
	/**
	 * ��Ӷ���
	 */
	@RequestMapping("/addBill")
	public String addBill(Bill bill, HttpSession session){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		bill.setCreationDate(time);
		//�޸���id
		User user=(User) session.getAttribute("userSession");
		if(user!=null){
			bill.setCreatedBy(user.getId());
		}else{
			bill.setModifyBy(null);
		}
		Integer addResult = orderService.addBill(bill);
		if(addResult>0)
			System.out.println(user.getUserName()+" ��Ӷ����ɹ�����");
		else
			System.out.println(user.getUserName()+" ��Ӷ���ʧ�ܣ���");
		log.info("**********��Ӷ��������"+addResult);
		return "redirect:OrderPage";
	}
}
