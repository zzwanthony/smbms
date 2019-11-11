package com.zzw.controller;

import com.alibaba.fastjson.JSON;
import com.zzw.pojo.Role;
import com.zzw.pojo.User;
import com.zzw.service.RoleService;
import com.zzw.service.UserService;
import com.zzw.util.UtilPage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
/**
 * �û�����ҳ��������
 * @author ŭ��İ�����
 *
 */
@Controller
@RequestMapping("/userManager")
public class UserController {

	/**
	 * ����log4j��־
	 */
	private static final Logger log=Logger.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	private UtilPage page = new UtilPage();
	/**
	 * ��ת�����û�����ҳ��(��һҳ)
	 */
	@RequestMapping(value="/userPage")
	public String userPage(Map<String,Object> map){
		//���õ�ǰҳ��Ϊ1
		page.setCurrentPageNo(1);

		int tolCount = userService.getPageCount(1,null,null);
		page.setTotalCount(tolCount);
		List<User> userList = userService.findAllUser(1,page.getPageSize());
		
		//��ȡȫ����ɫ��Ϣ
		List<Role> roleList = roleService.findAllRole();
		map.put("userList", userList);
		map.put("roleList",roleList);
		map.put("currentPageNo", page.getCurrentPageNo());
		map.put("totalCount", page.getTotalCount());
		map.put("totalPageCount", page.getTotalPageCount());
		log.info("**********�����û�����ҳ��");
		return "userlist";
	}
	
	/**
	 * ͨ��������ѯ�û�
	 */
	@RequestMapping(value="/userSearch")
	public String userSearch(@RequestParam(value="queryname",defaultValue="") String queryname,
								@RequestParam(value="queryUserRole",defaultValue="0") Integer queryUserRole,
								@RequestParam(value="pageIndex",defaultValue="1") Integer currentPage,
								Map<String,Object> map){
		page.setCurrentPageNo(currentPage);
		//��ȡģ����ѯ�û�����������
		int tolCount;
		if(queryname.equals("")&&queryUserRole==0){
			tolCount = userService.getPageCount(1,null,null);
		}else{
			tolCount = userService.getPageCount(2,queryname,queryUserRole);
		}
		page.setTotalCount(tolCount);//ģ����ѯ������������
		int pageSize = page.getPageSize();
		List<User> userList = userService.findUserBySearch(queryname,queryUserRole,(currentPage-1)*pageSize, pageSize);
		//��ȡȫ����ɫ��Ϣ
		List<Role> roleList = roleService.findAllRole();
		
		map.put("userList", userList);
		map.put("roleList",roleList);
		map.put("queryUserName", queryname);
		map.put("queryUserRole", queryUserRole);
		map.put("currentPageNo", currentPage);
		map.put("totalCount", page.getTotalCount());
		map.put("totalPageCount", page.getTotalPageCount());
		log.info("**********���û���ҳ��ģ����ѯ/ҳ���ҳ��ת��queryname="+queryname+" queryUserRole="+queryUserRole+" currentPage="+currentPage);
		return "userlist";
	}
	
	/**
	 * ɾ���û���Ϣ
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/userDelete")
	@ResponseBody
	public String userDelete(@RequestParam(value="uid", defaultValue="0")int uid,HttpSession session){
		Integer delReuslt = userService.deleteUser(uid);
		User user=(User) session.getAttribute("userSession");
		String jsonString = "";
		if(uid==0){
			jsonString = JSON.toJSONString("notexist");
		}else if(uid==user.getId()){
			jsonString = JSON.toJSONString("false");
		}
		else{
			jsonString = JSON.toJSONString("true");
		}
		log.info("**********ɾ�����û���"+uid+"�������"+delReuslt);
		return jsonString;
	}
	
	
	/**
	 * ����id�����û���Ϣ
	 */
	@RequestMapping(value="/findUserById")
	public String findUserById(@RequestParam("method") String method,
								@RequestParam("uid") Integer uid,Map<String,Object> map){
		User user = userService.findUserByid(uid);
		map.put("user", user);
		if(method.equals("view")){//��ת���û���Ϣ�鿴ҳ��
			log.info("**********�����û���"+uid+"����Ϣ��ѯҳ��");
			return "userview";
		}
		if(method.equals("modify")){//��ת���޸���Ϣҳ��
			List<Role> role = roleService.findAllRole();
			map.put("roleList", role);
			log.info("**********�����û���"+uid+"����Ϣ�޸�ҳ��");
			return "usermodify";
		}
		log.info("**********�����û���"+uid+"����Ϣ����");
		return "error";
	}
	
	
	/**
	 * �޸��û���Ϣ
	 */
	@RequestMapping(value="/updateUserMassage")
	public String updateUserMassage(User user,Map<String,Object> map){
		java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
		user.setModifyDate(time);
		System.out.println(user.toString());
		Integer modifyResult = userService.updateUserMassage(user);
		log.info("**********�޸��û���"+user.getUserName()+"����Ϣ���"+modifyResult);
		return "redirect:userPage";
	}
	
	/**
	 * ��ת������û�ҳ��
	 */
	@RequestMapping(value="/addUser")
	public String addUser(Map<String,Object> map){
		List<Role> role = roleService.findAllRole();
		map.put("roleList", role);
		log.info("**********�����û����ҳ��");
		return "useradd";
	}
	
	/**
	 * �û�����֤
	 */
	@RequestMapping(value="/userNameTest")
	@ResponseBody
	public String userNameTest(@RequestParam("userCode") String userCode){
		if(userCode.equals("") || userCode == null)
			return JSON.toJSONString("false");
		String code = userService.userNameTest(userCode);
		String jsonString = null;
		if(code != null){
			jsonString= JSON.toJSONString("exist");
		}else{
			jsonString = JSON.toJSONString("noExist");
		}
		log.info("**********��֤�û�����"+userCode+"�������"+jsonString);
		return jsonString;
	}
	
	/**
	 * �����û�
	 */
	@RequestMapping(value="/addUserMassage")
	public String addUserMassage(User user){
		java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
		user.setCreationDate(time);
		Integer addResult = userService.addUserMassage(user);
		log.info("**********����û���"+user.getId()+"����Ϣ�����"+addResult);
		return "redirect:userPage";
	}
	
	/**
	 * ��ת�������޸�ҳ��
	 * @return
	 */
	@RequestMapping(value="/passwordChange")
	public String passwordChange(){
		log.info("**********�������޸�ҳ��");
		return "pwdmodify";
	}

}
