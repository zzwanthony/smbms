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
 * 定单管理页面
 * @author 怒斥的安东尼
 *
 */
@Controller
@RequestMapping("/orderManager")
public class OrderController {

	/**
	 * 加入log4j日志
	 */
	private static final Logger log=Logger.getLogger(OrderController.class);
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	@Resource(name="providerService")
	private ProviderService providerService;
	private UtilPage page = new UtilPage();
	
	/**
	 * 跳转到定单管理页面首页
	 * @return
	 */
	@RequestMapping(value="/OrderPage")
	public String OrderPage(Map<String,Object> map){
		//设置当前页码为1
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
		log.info("**********访问定单管理页面");
		return "billlist";
	}
	/**
	 * 分页跳转/查询跳转
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
		//获取模糊查询用户数据总条数
		Integer tolCount;
		
		if(queryProductName.equals("") && queryProviderId == 0 && queryIsPayment == 0 && startTime.equals("") && endTime.equals("")){
			tolCount = orderService.getPageCount(1,null,null,null,null,null);
		}else{
			tolCount = orderService.getPageCount(2,queryProductName,queryProviderId,queryIsPayment,startTime,endTime);
		}
		
		page.setTotalCount(tolCount);//模糊查询的总数据条数
		Integer pageSize = page.getPageSize();
		List<Bill> billList = orderService.findOrderBySearch(queryProductName,queryProviderId,queryIsPayment,startTime,endTime,(currentPage-1)*pageSize, pageSize);
		//获取全部角色信息
		List<Provider> providerList = providerService.getAllProvider();

		map.put("billList", billList);
		map.put("providerList",providerList);
		
		map.put("queryProductName",queryProductName);
		map.put("queryProviderId",queryProviderId);
		map.put("queryIsPayment",queryIsPayment);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		//分页信息
		map.put("currentPageNo", currentPage);
		map.put("totalCount", page.getTotalCount());
		map.put("totalPageCount", page.getTotalPageCount());
		log.info("**********（定单页面模糊查询/页面分页跳转）queryProductName="+queryProductName+" queryProviderId="+queryProviderId+" queryIsPayment="+queryIsPayment
				+" startTime="+startTime+" endTime="+endTime);
		return "billlist";
	}
	
	/**
	 * 跳转到信息查询和信息修改页面
	 */
	@RequestMapping("/jumpToFindAndModify")
	public String jumpToFindAndModify(@RequestParam("method")String method,
										@RequestParam("billid") int billid,
										Map<String,Object> map){
		Bill bill = orderService.findBillById(billid);
		map.put("bill", bill);
		if(method.equals("view")){
			log.info("*********访问定单（"+billid+"）查询页面");
			return "billview";
		}
		else{
//			if(bill.getIsPayment()==2){
//				return "redirect:OrderPage";
//			}
			map.put("providerList", providerService.getAllProvider());
			log.info("*********访问定单（"+billid+"）修改页面");
			return "billmodify";
		}
	}
	/**
	 * 修改定单
	 */
	@RequestMapping("/modifyBill")
	public String modifyBill(Bill bill,HttpSession session){
		//修改时间
		Timestamp time = new Timestamp(System.currentTimeMillis());
		bill.setModifyDate(time);
		//修改人id
		User user=(User) session.getAttribute("userSession");
		
		if(user!=null){
			bill.setModifyBy(user.getId());			
		}else{
			bill.setModifyBy(null);
		}
		Integer result = orderService.modifyBill(bill);
		log.info("**********"+user.getUserName()+" 修改定单结果:"+result);
		return "redirect:OrderPage";
	}
	/**
	 * 删除定单
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
		log.info("**********删除定单（"+billid+"）结果："+result);
		return map;
	}
	/**
	 * 跳转到添加页面
	 */
	@RequestMapping("/jumpToAddPage")
	public String jumpToAddPage(Map<String,Object> map){
		map.put("providerList", providerService.getAllProvider());
		Integer lastBill = orderService.getLastBillId();
		map.put("billC","BILL2018_"+String.format("%03d", lastBill+1));
		log.info("**********访问定单添加页面");
		return "billadd";
	}
	
	/**
	 * 验证定单code是否重复
	 */
	/*@RequestMapping("/getBillCode")
	public String getBillCode(@RequestParam(value="BillCode",defaultValue="")String billCode){
		if(){
			
		}
		return "";
	}*/
	
	/**
	 * 添加定单
	 */
	@RequestMapping("/addBill")
	public String addBill(Bill bill, HttpSession session){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		bill.setCreationDate(time);
		//修改人id
		User user=(User) session.getAttribute("userSession");
		if(user!=null){
			bill.setCreatedBy(user.getId());
		}else{
			bill.setModifyBy(null);
		}
		Integer addResult = orderService.addBill(bill);
		if(addResult>0)
			System.out.println(user.getUserName()+" 添加定单成功！！");
		else
			System.out.println(user.getUserName()+" 添加定单失败！！");
		log.info("**********添加定单结果："+addResult);
		return "redirect:OrderPage";
	}
}
