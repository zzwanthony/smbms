<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>警告！非法页面访问！</title>
    <style type="text/css">
    	body{
			background-color:#EBEBEB;
    	}
    	img{
    		border-radius:50px;
    		background-color: gainsboro;
			box-shadow: 10px 10px 10px gainsboro,
					10px -10px 10px gainsboro,
					-10px 10px 10px gainsboro,
					-10px -10px 10px gainsboro;
    	}
</style>
  </head>
  <body>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("img").on("click",function(){
				history.back(-1);
			})
	});
	</script>
  <div style="width: 800px; margin: 0 auto; margin-top:100px;text-align: center;">
	<img alt="图片" src="${pageContext.request.contextPath }/static/bgImages/backGround4.png">
  </div>
  </body>
</html>
