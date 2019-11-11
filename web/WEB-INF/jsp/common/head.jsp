<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/css/public.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/css/style.css" />
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>超市订单管理系统</h1>
        <div class="publicHeaderR">
            <div>
            	<span>您好！</span>
	            <div class="userTitle"> 
	            	${userSession.userName }
	            	<div class="getUser">
	            		<p>你的信息</p>
	            		<p>身份：
		            		<c:if test="${userSession.userRole ==1 }">系统管理员</c:if>
		            		<c:if test="${userSession.userRole ==2 }">经理</c:if>
		            		<c:if test="${userSession.userRole ==3 }">普通员工</c:if>
	            		</p>
	            		<p>编码：${userSession.userCode }</p>
	            		<p>性别：
		            		<c:if test="${userSession.gender == 1 }">男</c:if>
		            		<c:if test="${userSession.gender == 2 }">女</c:if>
		            	</p>
	            		<p>年龄：${userSession.age }</p>
	            		<p>出生日期：${userSession.birthday }</p>
	            		<p>手机：${userSession.phone }</p>
	            		<p>住址：${userSession.address }</p>
	            	</div>
	            </div> , 欢迎你！
	            <a href="${pageContext.request.contextPath }/UserLogin/logout">退出</a>
            </div>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="time">2015年1月1日  11:11  星期一</span>
        <a href="#">温馨提示：为了使页面能正常浏览，请使用最新浏览器（IE9+） </a>
    </section>
 <!--主体内容-->
<section class="publicMian ">
	<div class="left">
		<h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
		<nav>
			<ul class="list">
				<li><a href="${pageContext.request.contextPath }/orderManager/OrderPage">订单管理</a></li>
				<c:if test="${userSession.userRole == 1 || userSession.userRole == 2 }">
					<li><a href="${pageContext.request.contextPath }/providerManager/providerPage">供应商管理</a></li>
				</c:if>
				<c:if test="${userSession.userRole == 1 }">
					<li><a href="${pageContext.request.contextPath }/userManager/userPage">用户管理</a></li>
					<li><a href="${pageContext.request.contextPath }/userManager/passwordChange">密码修改</a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath }/UserLogin/logout">退出系统</a></li>
			</ul>
		</nav>
	</div>
		<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
		<input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>