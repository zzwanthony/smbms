var proName = null;
var proContact = null;
var proPhone = null;
var saveBtn = null;
var backBtn = null;
var Picture = null;

$(function(){
	proName = $("#proName");
	var Name = proName.val();
	proContact = $("#proContact");
	proPhone = $("#proPhone");
	saveBtn = $("#save");
	backBtn = $("#back");
	Picture = $("#Picture");
	
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	proContact.next().html("*");
	proPhone.next().html("*");
	Picture.next().html("*");
	
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	proName.on("focus",function(){
		validateTip(proName.next(),{"color":"#666666"},"* 请输入供应商名称",false);
	}).on("blur",function(){
		if(proName.val() != null && proName.val() != "" && proName.val() != Name){
			$.ajax({
				type:"GET",
				url:path+"/providerManager/proCodeName",
				data:{method: "proName", CordOrName: proName.val()},
				dataType:"json",
				success:function(data){
					if(data == "true")
						validateTip(proName.next(),{"color":"green"},imgYes,true);
					else
						validateTip(proName.next(),{"color":"#F7FF00"},imgNo+" 名称已经存在，不能重复",false);
				}
			});
		}else if(proName.val() == ""){
			validateTip(proName.next(),{"color":"#F7FF00"},imgNo+" 供应商名称不能为空，请重新输入",false);
		}else{
			validateTip(proName.next(),{"color":"green"},imgYes,true);
		}
		
	});
	
	proContact.on("focus",function(){
		validateTip(proContact.next(),{"color":"#666666"},"* 请输入联系人",false);
	}).on("blur",function(){
		if(proContact.val() != null && proContact.val() != ""){
			validateTip(proContact.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proContact.next(),{"color":"#F7FF00"},imgNo+" 联系人不能为空，请重新输入",false);
		}
		
	});
	
	proPhone.on("focus",function(){
		validateTip(proPhone.next(),{"color":"#666666"},"* 请输入手机号",false);
	}).on("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(proPhone.val().match(patrn)){
			validateTip(proPhone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proPhone.next(),{"color":"#F7FF00"},imgNo + " 您输入的手机号格式不正确",false);
		}
	});
	
	Picture.on("click",function(){
		validateTip(Picture.next(),{"color":"white"},"* 图片格式为.jpg/.gif/.png",true);
	}).on("blur",function(){
		var fff=Picture.val();
		if(fff=="" || fff==null){
			validateTip(Picture.next(),{"color":"white"},"* 图片格式为.jpg/.gif/.png",true);
		}else{
			var aa=fff.split(".");
			//以“.”分隔上传文件字符串
			if(aa[aa.length-1]=='gif'||aa[aa.length-1]=='jpg'||aa[aa.length-1]=='png'){
				//判断图片格式{alert('图片格式正确！'); 
				validateTip(Picture.next(),{"color":"green"},imgYes,true);
			}else{
//			alert('对不起，你选择的图片格式不对\n图片格式应为*.jpg、*.gif、*.bmp');
				validateTip(Picture.next(),{"color":"#F7FF00"},"* 图片格式不正确",false);
			}
		}
	});
	
	saveBtn.on("click",function(){
		proName.blur();
		proContact.blur();
		proPhone.blur();
		Picture.blur();
		if(proName.attr("validateStatus") == "true" && 
				proContact.attr("validateStatus") == "true" && 
					proPhone.attr("validateStatus") == "true" &&
						Picture.attr("validateStatus") == "true"){
			if(confirm("是否确认提交数据")){
				$("#providerForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
		//alert("modify: "+referer);
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
});