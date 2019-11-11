var userObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:path+"/userManager/userDelete",
		data:{uid:obj.attr("userid")},
		dataType:"json",
		success:function(data){
			if(data == "true"){//删除成功：移除删除行
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
			}else if(data == "false"){//删除失败
				$('#yes').css("display","none");
				changeDLGContent("sorry，不能删除当前登入账号【"+obj.attr("username")+"】");
			}else if(data == "notexist"){
				$('#yes').css("display","none");
				changeDLGContent("sorry，用户【"+obj.attr("username")+"】不存在");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("sorry，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	
	$("#searchbutton").on("click",function(){
		document.forms[0].pageIndex.value = 1;
		document.forms[0].submit();
	});
	
	/**
	 * bind、live、delegate
	 * on
	 */
	$(".view").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/userManager/findUserById?method=view&uid="+ obj.attr("userid");
	});
	
	$(".modify").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/userManager/findUserById?method=modify&uid="+ obj.attr("userid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});

	$(".delete").on("click",function(){
		userObj = $(this);
		if(userObj.attr("nowUserId")==userObj.attr("userid")){
			$('#yes').css("display","none");
			changeDLGContent("警告：不能删除当前登入用户【"+userObj.attr("username")+"】！！！");
		}else{
			$('#yes').css("display","inline-block");
			changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");	
		}
		openYesOrNoDLG();
	});
	
	/*$(".deleteUser").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除用户【"+obj.attr("username")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/jsp/user.do",
				data:{method:"deluser",uid:obj.attr("userid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除用户【"+obj.attr("username")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，用户【"+obj.attr("username")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});*/
});