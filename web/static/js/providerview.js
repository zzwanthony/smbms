var backBtn = null;

$(function(){
	backBtn = $("#back");
	proid = $("#proid");
	
	backBtn.on("click",function(){
		//alert("view : "+referer);
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
	//获取供应商的定单信息
	$("#billMassage").on("click",function(){
		$.ajax({
			type:"GET",
			url:path+"/providerManager/proGetBill",
			data:{proid:proid.val()},
			dataType:"json",
			success:function(data){
				var massage = "<h2>供应商【"+$("#proName").val()+"】的订单历史信息展示：</h2>";
				if(data == "false"){//查询出错
					massage += "<p style=\"color:#F7FF00\">查询出错！！！</p>";
				}else if(data == "noBill"){//该供应商无定单信息
					massage += "<p style=\"color:blue\">该供应商无定单信息!!!</p>";
				}else {//展示信息
					$.each(data,function(i,o){
						massage += "<div  style=\"display: inline-block;padding-left: 20px;padding-bottom: 20px;\"><h3>"+(i+1)+"</h3>";
						massage += "订单编号："+o.billCode;
						massage += "</br>商品名称："+o.productName;
						massage += "</br>商品单位："+o.productUnit;
						massage += "</br>商品数量："+o.productCount;
						massage += "</br>总金额："+o.totalPrice;
						massage += "</br>是否付款：";
						if(o.isPayment==1)
							massage += "<span style=\"color:blue\">已付款</span>";
						else
							massage += "<span style=\"color:#F7FF00\">未付款</span>";
						massage += "</div>";
					});
				}
				$("#billMaView").html(massage);
			},
			error:function(data){
				//请求出错
				$("#billMaView").html("<p style=\"color:#F7FF00\">获取定单信息错误</p>");
			}
		});
	});
	
	$("#bigPicture").on("click",function(){
		$('.pictureRemove').css('display', 'block');
	});
	$("#closePicture").on("click",function(){
		$('.pictureRemove').css('display', 'none');
	});
});