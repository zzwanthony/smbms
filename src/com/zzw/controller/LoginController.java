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
 * ����
 * @author ŭ��İ�����
 *
 */
@Controller
@RequestMapping("/UserLogin")
public class LoginController {
	/**
	 * ����log4j��־
	 */
	private static final Logger log=Logger.getLogger(LoginController.class);
	
	@Resource(name="userService")
	private UserService userService;

	//�����¼����
	@RequestMapping(value="/login")
	public String login(@RequestParam(value="userCode",defaultValue="")String userCode,
			@RequestParam(value="userPassword",defaultValue="")String userPassword,
			HttpSession session,Map<String,Object> map){
		if(userCode.equals("") && userPassword.equals(""))
			return "login";
		User user = userService.UserLogin(userCode,userPassword);
		if(null != user){//��¼�ɹ�
			//����session
			session.setAttribute("userSession", user);
			//ҳ����ת��frame.jsp��
			log.info("**********"+user.getUserName()+"����ɹ�");
			return "redirect:frame.html";
		}else{//����ʧ��
			map.put("message", "�û������������");
			log.error("**********����ʧ��");
			return "login";
		}
	}
	
	//��ת����¼ҳ��
	@RequestMapping(value="/frame.html")
	public String toFrame(HttpSession session){
		if(session.getAttribute("userSession") == null ){
			return "redirect:login.html";
		}
		return "frame";
	}
	
	//�˳�����ҳ��
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.invalidate();
		log.info("**********�˳�����");
		//�ض��򵽵�¼ҳ��
		return "login";
	}
	
	@RequestMapping(value="/error")
	public String error(){
		log.warn("**********�������ҳ��");
		return "error";
	}
	
	/**
	 * ��������֤�Ƿ���ȷ
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
		log.info("**********��֤�����������Ƿ���ȷ�����"+result);
		return result;
	}
	/**
	 * �޸�����������
	 */
	@RequestMapping(value="/doPassword")
	public String doPassword(@RequestParam("rnewpassword")String newPassword,HttpSession session,Map<String,Object> map){
		User user=(User) session.getAttribute("userSession");
		Integer result = userService.passwordChange(newPassword,user.getId());
		map.put("message", "�������޸ģ����صǣ�");
		log.info("**********�޸���������"+result);
		return "login";
	}
}

