<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
       <div class="location">
           <strong>你现在所在的位置是:</strong>
           <span>订单管理页面</span>
       </div>
       <div class="search">
       <form method="post" action="${pageContext.request.contextPath }/orderManager/orderSearch">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>商品名称：</span>
			<input name="queryProductName" type="text" style="width: 100px;" value="${queryProductName }">
			 
			<span>供应商：</span>
			<select name="queryProviderId">
				<c:if test="${providerList != null }">
				   <option value="0">--请选择--</option>
				   <c:forEach var="provider" items="${providerList}">
				   		<option <c:if test="${provider.id == queryProviderId }">selected="selected"</c:if>
				   		value="${provider.id}">${provider.proName}</option>
				   </c:forEach>
				</c:if>
       		</select>
			 
			<span>是否付款：</span>
			<select name="queryIsPayment">
				<option value="0">--请选择--</option>
				<option value="1" ${queryIsPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>
				<option value="2" ${queryIsPayment == 2 ? "selected=\"selected\"":"" }>已付款</option>
       		</select>
       		
			<span>创建时间范围：</span>
			 	<input type="text" name="startTime"  Class="Wdate" style="width:100px;" placeholder="最早时间"
					readonly="readonly" onclick="WdatePicker();" value="${startTime }">至
				<input type="text" name="endTime" Class="Wdate" style="width:100px;" placeholder="最晚时间"
					readonly="readonly" onclick="WdatePicker();" value="${endTime }">
					
			 <input type="hidden" name="pageIndex" value="${currentPageNo }"/>
			 <input	value="查 询" type="submit" id="searchbutton">
			 <a href="${pageContext.request.contextPath }/orderManager/jumpToAddPage">添加订单</a>
		</form>
       </div>
       <!--账单表格 样式和供应商公用-->
      <table class="providerTable" cellpadding="0" cellspacing="0" id="tableName">
          <tr class="firstTr">
              <th width="10%">订单编码</th>
              <th width="15%">商品名称</th>
              <th width="20%">供应商</th>
              <th width="10%">订单金额</th>
              <th width="15%">是否付款</th>
              <th width="15%">创建时间</th>
              <th width="15%">操作</th>
          </tr>
			<c:forEach var="bill" items="${billList }" varStatus="status">
				<tr>
					<td>
						<span><a class="view" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }>${bill.billCode }</a></span>
					</td>
					<td>
						<span>${bill.productName }</span>
					</td>
					<td>
						<c:if test="${userSession.userRole == 1 || userSession.userRole == 2 }">
							<span><a class="toProviderView" href="${pageContext.request.contextPath }/providerManager/viewOrModify?method=view&proid=${bill.providerId }">${bill.providerName}</a></span>
						</c:if>
						<c:if test="${userSession.userRole == 3 }">
							<span>${bill.providerName}</span>						
						</c:if>
					</td>
					<td>
						<span>${bill.totalPrice}</span>
					</td>
					<td>
						<span>
							<c:if test="${bill.isPayment == 1}">未付款</c:if>
							<c:if test="${bill.isPayment == 2}">已付款</c:if>
						</span>
					</td>
					<td>
						<span>
							<fmt:formatDate value="${bill.creationDate}" pattern="yyyy-MM-dd"/>
						</span>
					</td>
					<td>
						<span><a class="modify" href="javascript:;" billid=${bill.id } billcc=${bill.billCode } isPayment=${bill.isPayment }><img class="imgDel" src="${pageContext.request.contextPath }/static/images/xiugai.png" alt="修改" title="修改"/></a></span>
						<span><a class="delete" href="javascript:;" billid=${bill.id } billcc=${bill.billCode } isPayment=${bill.isPayment }><img class="imgDel" src="${pageContext.request.contextPath }/static/images/schu.png" alt="删除" title="删除"/></a></span>
					</td>
				</tr>
			</c:forEach>
      </table>
			<!-- 分页显示 -->
			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
		  	<c:import url="rollpage.jsp">
	          	<c:param name="totalCount" value="${totalCount}"/>
	          	<c:param name="currentPageNo" value="${currentPageNo}"/>
	          	<c:param name="totalPageCount" value="${totalPageCount}"/>
          	</c:import>
  </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/billlist.js"></script>