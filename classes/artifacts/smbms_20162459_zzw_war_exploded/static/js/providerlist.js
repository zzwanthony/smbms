var providerObj;

//供应商管理页面上点击删除按钮弹出删除框(providerlist.jsp)
function deleteProvider(obj){
	$.ajax({
		type:"GET",
		url:path+"/providerManager/deletePro",
		data:{proid:obj.attr("proid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
				alert("删除成功");
				if(document.getElementById("tableName").rows.length==1){
					var frm = document.forms[0];
					var pVal = frm.pageIndex.value;
					frm.pageIndex.value = pVal - 1;
					frm.submit();
				}else{
					document.forms[0].submit();					
				}
			}else if(data.delResult == "false"){//删除失败
				$('#yes').css("display","none");
				changeDLGContent("sorry，删除供应商【"+obj.attr("proname")+"】出错，未删除成功！");
			}else if(data.delResult == "notexist"){
				$('#yes').css("display","none");
				changeDLGContent("sorry，供应商【"+obj.attr("proname")+"】不存在");
			}else {
				$('#yes').css("display","none");
				changeDLGContent("sorry，该供应商【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单且未付款，不能删除");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeProv').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeProv').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}
$(function(){
	
	$("#searchbutton").on("click",function(){
		document.forms[0].pageIndex.value = 1;
		document.forms[0].submit();
	});
	
	$(".view").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/providerManager/viewOrModify?method=view&proid="+ obj.attr("proid");
	});
	
	$(".modify").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/providerManager/viewOrModify?method=modify&proid="+ obj.attr("proid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteProvider(providerObj);
	});

	$(".delete").on("click",function(){
		providerObj = $(this);
		$('#yes').css("display","inline-block");
		changeDLGContent("你确定要删除供应商【"+providerObj.attr("proname")+"】吗？");
		openYesOrNoDLG();
	});
	
//	$(".deleteProvider").on("click",function(){
//		var obj = $(this);
//		if(confirm("你确定要删除供应商【"+obj.attr("proname")+"】吗？")){
//			$.ajax({
//				type:"GET",
//				url:path+"/providerManager/deleteProvider",
//				data:{method:"delprovider",proid:obj.attr("proid")},
//				dataType:"json",
//				success:function(data){
//					if(data.delResult == "true"){//删除成功：移除删除行
//						alert("删除成功");
//						obj.parents("tr").remove();
//					}else if(data.delResult == "false"){//删除失败
//						alert("对不起，删除供应商【"+obj.attr("proname")+"】失败");
//					}else if(data.delResult == "notexist"){
//						alert("对不起，供应商【"+obj.attr("proname")+"】不存在");
//					}else{
//						alert("对不起，该供应商【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单，不能删除");
//					}
//				},
//				error:function(data){
//					alert("对不起，删除失败");
//				}
//			});
//		}
//	});
});