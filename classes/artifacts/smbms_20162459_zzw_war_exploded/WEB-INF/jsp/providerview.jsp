<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
 <div class="right" style="position:relative;">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商信息查看</span>
        </div>
        <div class="providerView">
			<input type="hidden" id="proid" value="${provider.id }">
			<input type="hidden"  id="proName" value="${provider.proName }">
			<c:if test="${empty provider}">
				<p><strong></strong><span>该供应商已被删除！！！</span></p>
			</c:if>
            <p><strong>供应商编码：</strong><span>${provider.proCode }</span></p>
            <p><strong>供应商名称：</strong><span>${provider.proName }</span></p>
            <p><strong>联系人：</strong><span>${provider.proContact }</span></p>
            <p><strong>联系电话：</strong><span>${provider.proPhone }</span></p>
            <p><strong>传真：</strong><span>${provider.proFax }</span></p>
            <p><strong>描述：</strong><span>${provider.proDesc}</span></p>
            <p><strong>营业执照：</strong>
            <span>
            <c:if test="${provider.proPicture != null }">
	            <img alt="营业执照图片" src="${pageContext.request.contextPath }/${provider.proPicture }" style="width: 100px;height: 100px;">
	            <input type="button" value="放大图片" id="bigPicture">
            </c:if>
            <c:if test="${provider.proPicture == null }">
            	未传过营业执照图片！！
            </c:if>
            </span></p>
			<div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="返回" >
            	<input type="button" id="billMassage" name="billMassage" value="订单信息" >
            </div>
        </div>
        <div id="billMaView" style="padding-left: 100px;"></div>
    </div>

	<!--点击放大安扭放大图片-->
	<!-- <div class="pictureSize"></div> -->
	<div class="pictureRemove">
		<img alt="营业执照图片" src="${pageContext.request.contextPath }/${provider.proPicture }" id="closePicture" style="width: 1300px;height: 700px;"><br>
	</div>
</section>


<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/providerview.js"></script>
