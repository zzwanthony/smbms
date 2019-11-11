var billObj;

//订单管理页面上点击删除按钮弹出删除框(billlist.jsp)
function deleteBill(obj){
	$.ajax({
		type:"GET",
		url:path+"/orderManager/deleteBill",
		data:{billid:obj.attr("billid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
				alert("删除成功");
				/*if(document.getElementById("tableName").rows.length==1){
					document.forms[0].pageIndex.value = document.forms[0].pageIndex.value - 1;
					document.forms[0].submit();
				}*/
				if(document.getElementById("tableName").rows.length==1){
//				page_nav(document.forms[0],$("#pageIndex")-1);
//				window.location.href=path+"/orderManager/orderSearch?queryProductName=   &queryProviderId=   &queryIsPayment=   &startTime=   &endTime=   &pageIndex=   ";
					var frm = document.forms[0];
					var pVal = frm.pageIndex.value;
					frm.pageIndex.value = pVal - 1;
					frm.submit();
				}else{
					document.forms[0].submit();
				}
			}else if(data.delResult == "notexist"){
				$('#yes').css("display","none");
				changeDLGContent("sorry，订单【"+obj.attr("billcc")+"】不存在");
			}
		},
		error:function(data){
			alert("sorry，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	
	
//	
//	$("#searchbutton").on("click",function(){
//		document.forms[0].pageIndex.value = 1;
//		document.forms[0].submit();
//	});
	
	$(".view").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/orderManager/jumpToFindAndModify?method=view&billid="+ obj.attr("billid");
	});
	
	$(".modify").on("click",function(){
		var obj = $(this);
		if(obj.attr("isPayment")==2){
			$('#yes').css("display","none");
			changeDLGContent("sorry，此订单【"+obj.attr("billcc")+"】已付款付款，不能修改！");
			openYesOrNoDLG();
		}else{
			$('#yes').css("display","inline-block");
			window.location.href=path+"/orderManager/jumpToFindAndModify?method=modify&billid="+ obj.attr("billid");			
		}
	});
	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteBill(billObj);
	});

	$(".delete").on("click",function(){
		billObj = $(this);
		if(billObj.attr("isPayment")==1){
			$('#yes').css("display","none");
			changeDLGContent("sorry，此订单【"+billObj.attr("billcc")+"】未付款，不能删除！");
		}else{
			$('#yes').css("display","inline-block");
			changeDLGContent("你确定要删除订单【"+billObj.attr("billcc")+"】吗？");
		}
		openYesOrNoDLG();
	});
	
	/*$(".deleteBill").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除订单【"+obj.attr("billcc")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/bill.do",
				data:{method:"delbill",billid:obj.attr("billid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("sorry，删除订单【"+obj.attr("billcc")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("sorry，订单【"+obj.attr("billcc")+"】不存在");
					}
				},
				error:function(data){
					alert("sorry，删除失败");
				}
			});
		}
	});*/
});