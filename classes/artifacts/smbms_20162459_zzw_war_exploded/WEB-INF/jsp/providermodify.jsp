<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong>
		<span>供应商管理页面 >> 供应商修改页面</span>
	</div>
	<div class="providerAdd">
		<form id="providerForm" name="providerForm" method="post" enctype="multipart/form-data" 
		action="${pageContext.request.contextPath }/providerManager/modifyProvider">
				<!--div的class 为error是验证错误，ok是验证成功-->
				<input type="hidden" name="id" value="${provider.id }">
				<div>
					<label for="proCode">供应商编码：</label>
					<input type="text" name="proCode" id="proCode" value="${provider.proCode }" readonly="readonly"> 
				</div>
				<div>
					<label for="proName">供应商名称：</label>
					<input type="text" name="proName" id="proName" value="${provider.proName }"> 
					<font color="red"></font>
				</div>
              
				<div>
					<label for="proContact">联系人：</label>
					<input type="text" name="proContact" id="proContact" value="${provider.proContact }"> 
					<font color="red"></font>
				</div>
              
				<div>
					<label for="proPhone">联系电话：</label>
					<input type="text" name="proPhone" id="proPhone" value="${provider.proPhone }"> 
					<font color="red"></font>
				</div>
              
				<div>
					<label for="proAddress">联系地址：</label>
					<input type="text" name="proAddress" id="proAddress" value="${provider.proAddress }"> 
				</div>
				
				<div>
					<label for="proFax">传真：</label>
					<input type="text" name="proFax" id="proFax" value="${provider.proFax }">
				</div>
				
				<div>
					<label for="proDesc">描述：</label>
					<input type="text" name="proDesc" id="proDesc" value="${provider.proDesc }"> 
				</div>

				<div>
					<label>执照图片：</label>
					<span>
						<c:if test="${! empty provider.proPicture }">
							<img alt="营业执照图片" src="${pageContext.request.contextPath }/${provider.proPicture }" style="width: 100px;height: 100px;">
							<input type="hidden" name="proPicture" value="${provider.proPicture }"></br><label></label>
						</c:if>
						<input type="file" name="Picture" id="Picture" value="">
						<font color="white">
							<c:if test="${!empty uploadFileError}">${uploadFileError }</c:if>
							<c:if test="${empty uploadFileError}">图片格式应为*.jpg、*.gif、*.bmp</c:if>
						</font>
					</span>
				</div>
				<div class="providerAddBtn">
					<input type="button" name="save" id="save" value="保存">
					<input type="button" id="back" name="back" value="返回" >
				</div>
		</form>
	</div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/providermodify.js"></script>