<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body{
		background-image:url(../static/bgImages/backGround2.jpg);
		background-size:100% 100%;
		background-attachment:fixed;
		background-color:#CCCCCC;
	}
	img{
   		border-radius:50px;
   		background-color: #526A00;
		box-shadow: 10px 10px 10px #526A00,
					10px -10px 10px #526A00,
					-10px 10px 10px #526A00,
					-10px -10px 10px #526A00;
    }
</style>
</head>
<body>
	<div style="width: 1000px; margin: 0 auto; margin-top:100px;">
		<a href="${pageContext.request.contextPath }/UserLogin/logout" class="returnLogin">
			<img alt="图片" src="${pageContext.request.contextPath }/static/bgImages/backGround9.png">
		</a>
	</div>
</body>
</html>