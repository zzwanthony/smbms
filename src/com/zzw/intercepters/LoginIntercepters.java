package com.zzw.intercepters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zzw.pojo.User;
/**
 * �Զ�����������δ�������Ա���ɽ������ҳ��
 * @author ŭ��İ�����
 *
 */
public class LoginIntercepters extends HandlerInterceptorAdapter{
	//������֤
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//��½ҵ������֤�Ƿ��½
		HttpSession session = request.getSession();
		User userKey = (User) session.getAttribute("userSession");
		
		String requestUri = request.getRequestURI();  //  /smbms-20162459-zzw/orderManager/OrderPage
//        String contextPath = request.getContextPath();  //  /smbms-20162459-zzw
//        String url = requestUri.substring(contextPath.length()); //  /orderManager/OrderPage
//        System.out.println("requestUri:"+requestUri);
//        System.out.println("contextPath:"+contextPath);
//        System.out.println("url:"+url);
//		
		if (userKey != null) {//�û��ǵ���״̬��������
			if(userKey.getUserRole()==2){
				if(requestUri.indexOf("/providerManager") != -1 || requestUri.indexOf("/orderManager") != -1 )
					return true;
				else{
					request.getRequestDispatcher("/WEB-INF/jsp/warning.jsp").forward(request, response);
					return false;
				}
			}else if(userKey.getUserRole()==3){
				if(requestUri.indexOf("/orderManager") != -1 )
					return true;
				else{
					request.getRequestDispatcher("/WEB-INF/jsp/warning.jsp").forward(request, response);
					return false;
				}
			}else{
				return true;
			}
		}else{
			//����״̬�����������أ���ת��error.jspҳ��
			response.sendRedirect(request.getContextPath()+"/UserLogin/error");
			//response.sendRedirect(contextPath+"/WEB-INF/jsp/error.jsp");
			return false;
		}
	}
}
