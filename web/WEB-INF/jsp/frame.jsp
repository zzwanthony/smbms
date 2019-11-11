<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
    <div class="right">
        <img class="wColck" src="${pageContext.request.contextPath }/static/bgImages/bird.gif" alt=""/>
        <div class="wFont">
            <h2 style="color: white;">${userSession.userName }</h2>
            <p style="color: white;">欢迎来到超市订单管理系统!</p>
        </div>
       	<img alt="laugth" src="${pageContext.request.contextPath }/static/bgImages/facePicture.png">
    </div>
</section>
<%@include file="./common/foot.jsp" %>
