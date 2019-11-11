<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - 超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/css/style.css" />
</head>
<body class="loginBackG">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>超市订单管理系统</h1>
			<div class="info">${message }</div>
        </header>
        <section class="loginCont">
	        <form class="loginForm" action="${pageContext.request.contextPath }/UserLogin/login"  name="actionForm" id="actionForm"  method="post" >
				<div class="inputbox">
                    <label for="userCode">用户名：</label>
					<input type="text" id="userCode" name="userCode" placeholder="请输入用户名" required/>
				</div>	
				<div class="inputbox">
                    <label for="userPassword">密码：</label>
                    <input type="password" id="userPassword" name="userPassword" placeholder="请输入密码" required/>
                </div>	
				<div class="subBtn">
                    <input type="submit" value="登录"/>
                </div>	
			</form>
        </section>
    </section>
</body>
</html>
