<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面</span>
        </div>
        <div class="search">
        	<form method="post" action="${pageContext.request.contextPath }/providerManager/providerPage">
				<input name="method" value="query" type="hidden">
				<span>供应商编码：</span>
				<input name="queryProCode" type="text" value="${queryProCode }">
				<span>供应商名称：</span>
				<input name="queryProName" type="text" value="${queryProName }">
				
			 	<input type="hidden" name="pageIndex" value="${currentPageNo}"/>
				<input value="查 询" type="button" id="searchbutton">
				<a href="${pageContext.request.contextPath }/providerManager/jumpToAddPage">添加供应商</a>
			</form>
        </div>
        <!--供应商操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0" id="tableName">
            <tr class="firstTr">
                <th width="10%">供应商编码</th>
                <th width="25%">供应商名称</th>
                <th width="10%">联系人</th>
                <th width="10%">联系电话</th>
                <th width="15%">传真</th>
                <th width="15%">创建时间</th>
                <th width="15%">操作</th>
            </tr>
            <c:forEach var="provider" items="${providerList }" varStatus="status">
				<tr>
					<td>
					<span><a class="view" href="javascript:;" proid=${provider.id } proname=${provider.proName }>${provider.proCode }</a></span>
					</td>
					<td>
					<span>${provider.proName }</span>
					</td>
					<td>
					<span>${provider.proContact}</span>
					</td>
					<td>
					<span>${provider.proPhone}</span>
					</td>
					<td>
					<span>${provider.proFax}</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${provider.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="modify" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img class="imgDel" src="${pageContext.request.contextPath }/static/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a class="delete" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img class="imgDel" src="${pageContext.request.contextPath }/static/images/schu.png" alt="删除" title="删除"/></a></span>
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
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>你确定要删除该供应商吗？</p>
           <a href="#" id="yes">确定</a>
           <a href="#" id="no">取消</a>
       </div>
   </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/providerlist.js"></script>
