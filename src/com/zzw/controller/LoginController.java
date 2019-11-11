package com.zzw.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zzw.pojo.User;
import com.zzw.service.UserService;

/**
 * 登入
 * @author 怒斥的安东尼
 *
 */
@Controller
@RequestMapping("/UserLogin")
public class LoginController {
	/**
	 * 加入log4j日志
	 */
	private static final Logger log=Logger.getLogger(LoginController.class);
	
	@Resource(name="userService")
	private UserService userService;

	//处理登录请求
	@RequestMapping(value="/login")
	public String login(@RequestParam(value="userCode",defaultValue="")String userCode,
			@RequestParam(value="userPassword",defaultValue="")String userPassword,
			HttpSession session,Map<String,Object> map){
		if(userCode.equals("") && userPassword.equals(""))
			return "login";
		User user = userService.UserLogin(userCode,userPassword);
		if(null != user){//登录成功
			//放入session
			session.setAttribute("userSession", user);
			//页面跳转（frame.jsp）
			log.info("**********"+user.getUserName()+"登入成功");
			return "redirect:frame.html";
		}else{//登入失败
			map.put("message", "用户名或密码错误！");
			log.error("**********登入失败");
			return "login";
		}
	}
	
	//跳转到登录页面
	@RequestMapping(value="/frame.html")
	public String toFrame(HttpSession session){
		if(session.getAttribute("userSession") == null ){
			return "redirect:login.html";
		}
		return "frame";
	}
	
	//退出登入页面
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.invalidate();
		log.info("**********退出登入");
		//重定向到登录页面
		return "login";
	}
	
	@RequestMapping(value="/error")
	public String error(){
		log.warn("**********进入出错页面");
		return "error";
	}
	
	/**
	 * 旧密码验证是否正确
	 */
	@RequestMapping(value="/passwordTest")
	@ResponseBody
	public String passwordTest(@RequestParam("oldpassword")String password,HttpSession session){
		User user=(User) session.getAttribute("userSession");
		String result;
		if(user==null)
			result = JSON.toJSONString("sessionError");
		else if(password==null||password.equals("")){
			result = JSON.toJSONString("error");
		}else if(user.getUserPassword().equals(password)){
			result = JSON.toJSONString("true");
		}else{
			session.invalidate();
			result = JSON.toJSONString("false");
		}
		log.info("**********验证旧密码输入是否正确结果："+result);
		return result;
	}
	/**
	 * 修改密码请求处理
	 */
	@RequestMapping(value="/doPassword")
	public String doPassword(@RequestParam("rnewpassword")String newPassword,HttpSession session,Map<String,Object> map){
		User user=(User) session.getAttribute("userSession");
		Integer result = userService.passwordChange(newPassword,user.getId());
		map.put("message", "密码已修改，请重登！");
		log.info("**********修改密码结果："+result);
		return "login";
	}
}

