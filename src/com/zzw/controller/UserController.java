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
 * 用户管理页面请求处理
 * @author 怒斥的安东尼
 *
 */
@Controller
@RequestMapping("/userManager")
public class UserController {

	/**
	 * 加入log4j日志
	 */
	private static final Logger log=Logger.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	private UtilPage page = new UtilPage();
	/**
	 * 跳转进入用户管理页面(第一页)
	 */
	@RequestMapping(value="/userPage")
	public String userPage(Map<String,Object> map){
		//设置当前页码为1
		page.setCurrentPageNo(1);

		int tolCount = userService.getPageCount(1,null,null);
		page.setTotalCount(tolCount);
		List<User> userList = userService.findAllUser(1,page.getPageSize());
		
		//获取全部角色信息
		List<Role> roleList = roleService.findAllRole();
		map.put("userList", userList);
		map.put("roleList",roleList);
		map.put("currentPageNo", page.getCurrentPageNo());
		map.put("totalCount", page.getTotalCount());
		map.put("totalPageCount", page.getTotalPageCount());
		log.info("**********访问用户管理页面");
		return "userlist";
	}
	
	/**
	 * 通过搜索查询用户
	 */
	@RequestMapping(value="/userSearch")
	public String userSearch(@RequestParam(value="queryname",defaultValue="") String queryname,
								@RequestParam(value="queryUserRole",defaultValue="0") Integer queryUserRole,
								@RequestParam(value="pageIndex",defaultValue="1") Integer currentPage,
								Map<String,Object> map){
		page.setCurrentPageNo(currentPage);
		//获取模糊查询用户数据总条数
		int tolCount;
		if(queryname.equals("")&&queryUserRole==0){
			tolCount = userService.getPageCount(1,null,null);
		}else{
			tolCount = userService.getPageCount(2,queryname,queryUserRole);
		}
		page.setTotalCount(tolCount);//模糊查询的总数据条数
		int pageSize = page.getPageSize();
		List<User> userList = userService.findUserBySearch(queryname,queryUserRole,(currentPage-1)*pageSize, pageSize);
		//获取全部角色信息
		List<Role> roleList = roleService.findAllRole();
		
		map.put("userList", userList);
		map.put("roleList",roleList);
		map.put("queryUserName", queryname);
		map.put("queryUserRole", queryUserRole);
		map.put("currentPageNo", currentPage);
		map.put("totalCount", page.getTotalCount());
		map.put("totalPageCount", page.getTotalPageCount());
		log.info("**********（用户管页面模糊查询/页面分页跳转）queryname="+queryname+" queryUserRole="+queryUserRole+" currentPage="+currentPage);
		return "userlist";
	}
	
	/**
	 * 删除用户信息
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
		log.info("**********删除了用户（"+uid+"）结果："+delReuslt);
		return jsonString;
	}
	
	
	/**
	 * 根据id返回用户信息
	 */
	@RequestMapping(value="/findUserById")
	public String findUserById(@RequestParam("method") String method,
								@RequestParam("uid") Integer uid,Map<String,Object> map){
		User user = userService.findUserByid(uid);
		map.put("user", user);
		if(method.equals("view")){//跳转到用户信息查看页面
			log.info("**********访问用户（"+uid+"）信息查询页面");
			return "userview";
		}
		if(method.equals("modify")){//跳转到修改信息页面
			List<Role> role = roleService.findAllRole();
			map.put("roleList", role);
			log.info("**********访问用户（"+uid+"）信息修改页面");
			return "usermodify";
		}
		log.info("**********访问用户（"+uid+"）信息出错");
		return "error";
	}
	
	
	/**
	 * 修改用户信息
	 */
	@RequestMapping(value="/updateUserMassage")
	public String updateUserMassage(User user,Map<String,Object> map){
		java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
		user.setModifyDate(time);
		System.out.println(user.toString());
		Integer modifyResult = userService.updateUserMassage(user);
		log.info("**********修改用户（"+user.getUserName()+"）信息结果"+modifyResult);
		return "redirect:userPage";
	}
	
	/**
	 * 跳转到添加用户页面
	 */
	@RequestMapping(value="/addUser")
	public String addUser(Map<String,Object> map){
		List<Role> role = roleService.findAllRole();
		map.put("roleList", role);
		log.info("**********访问用户添加页面");
		return "useradd";
	}
	
	/**
	 * 用户名验证
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
		log.info("**********验证用户名（"+userCode+"）结果："+jsonString);
		return jsonString;
	}
	
	/**
	 * 增加用户
	 */
	@RequestMapping(value="/addUserMassage")
	public String addUserMassage(User user){
		java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
		user.setCreationDate(time);
		Integer addResult = userService.addUserMassage(user);
		log.info("**********添加用户（"+user.getId()+"）信息结果："+addResult);
		return "redirect:userPage";
	}
	
	/**
	 * 跳转到密码修改页面
	 * @return
	 */
	@RequestMapping(value="/passwordChange")
	public String passwordChange(){
		log.info("**********访问密修改页面");
		return "pwdmodify";
	}

}
