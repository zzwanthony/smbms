<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
        <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath }/userManager/updateUserMassage">
			<input type="hidden" name="method" value="modifyexe">
			<input type="hidden" name="id" value="${user.id }"/>
			<input type="hidden" name="modifyBy" value="${userSession.id }"/>
			 <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" value="${user.userName }"> 
					<font color="red"></font>
             </div>
             <c:if test="${user.userRole != 1 }">
	             <div>
	             	<label>用户密码：</label>
	             	<input type="text" name="userPassword" id="userPassword" value="${user.userPassword }">
	             	<font color="red"></font>
	             </div>
             </c:if>
			 <div>
                    <label >用户性别：</label>
                    <select name="gender" id="gender">
						<c:choose>
							<c:when test="${user.gender == 1 }">
								<option value="1" selected="selected">男</option>
					    		<option value="2">女</option>
							</c:when>
							<c:otherwise>
								<option value="1">男</option>
					    		<option value="2" selected="selected">女</option>
							</c:otherwise>
						</c:choose>
					 </select>
             </div>
			 <div>
                    <label for="data">出生日期：</label>
                    <input type="text" Class="Wdate" id="birthday" name="birthday" value="${user.birthday }"
					readonly="readonly"  onclick="WdatePicker();">
                    <font color="red"></font>
              </div>
			
		       <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="phone" id="phone" value="${user.phone }"> 
                    <font color="red"></font>
               </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="address" id="address" value="${user.address }">
                </div>
				<div>
                    <label >用户角色：</label>
                    <!-- 列出所有的角色分类 -->
					<input type="hidden" value="${user.userRole }" id="rid" />
					<select name="userRole" id="userRole">
						<c:forEach items="${roleList }" var="role">
							<option value="${role.id }"
								<c:if test="${role.id==user.userRole }">
									selected="selected"
								</c:if>
							>${role.roleName }</option>
						</c:forEach>
					</select>
        			<font color="red"></font>
                </div>
				<div class="providerAddBtn">
					<input type="button" name="save" id="save" value="保存" />
					<input type="button" id="back" name="back" value="返回"/>
				</div>
            </form>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/usermodify.js"></script>
