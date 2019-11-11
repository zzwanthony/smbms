var proCode = null;
var proName = null;
var proContact = null;
var proPhone = null;
var addBtn = null;
var backBtn = null;
var Picture = null;

$(function(){
	proCode = $("#proCode");
	proName = $("#proName");
	proContact = $("#proContact");
	proPhone = $("#proPhone");
	addBtn = $("#add");
	backBtn = $("#back");
	Picture = $("#Picture");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	proCode.next().html("*");
	proName.next().html("*");
	proContact.next().html("*");
	proPhone.next().html("*");
	Picture.next().html("*");
	
	function proCodeAndName(obj1,obj2){
		$.ajax({
			type:"GET",
			url:path+"/providerManager/proCodeName",
			data:{method: obj1, CordOrName: obj2.val()},
			dataType:"json",
			success:function(data){
				if(data == "true")
					validateTip(obj2.next(),{"color":"green"},imgYes,true);
				else
					validateTip(obj2.next(),{"color":"#F7FF00"},imgNo+" 名称已经存在，不能重复",false);
			}
		});
	}
	
	/*
	 * 验证
	 * 失焦blur\获焦focus
	 * jquery的方法传递
	 */
	proCode.on("blur",function(){
		if(proCode.val() != null && proCode.val() != ""){
			proCodeAndName("proCode",proCode)
		}else if(proCode.val() == ""){
			validateTip(proCode.next(),{"color":"#666666"},"* 请输入供应商编码",false);
		}
		/*else{
			validateTip(proCode.next(),{"color":"#F7FF00"},imgNo+" 编码不能为空，请重新输入",false);			
		}*/
	}).on("focus",function(){
		//显示友情提示
		validateTip(proCode.next(),{"color":"#666666"},"* 请输入供应商编码",false);
	});
	
	proName.on("focus",function(){
		validateTip(proName.next(),{"color":"#666666"},"* 请输入供应商名称",false);
	}).on("blur",function(){
		if(proName.val() != null && proName.val() != ""){
			proCodeAndName("proName",proName)
		}else{
			validateTip(proName.next(),{"color":"#F7FF00"},imgNo+" 供应商名称不能为空，请重新输入",false);
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
		if(proPhone.val()==""){
			validateTip(proPhone.next(),{"color":"#F7FF00"},imgNo + " 手机号不能为空",false);
		}else if(proPhone.val().match(patrn)){
			validateTip(proPhone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proPhone.next(),{"color":"#F7FF00"},imgNo + " 您输入的手机号格式不正确",false);
		}
	});
	
	Picture.on("click",function(){
		validateTip(Picture.next(),{"color":"white"},"* 图片格式只能为.jpg/.gif/.png",false);
	}).on("blur",function(){
		var fff=Picture.val();
		if(fff=="" || fff==null){
			validateTip(Picture.next(),{"color":"white"},"* 图片格式只能为.jpg/.gif/.png",false);
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

	addBtn.bind("click",function(){
		if(proCode.attr("validateStatus") != "true"){
			confirm("编码输入有错，无法保存")
			proCode.blur();
		}else if(proName.attr("validateStatus") != "true"){
			confirm("供应商名称未输入，无法保存")
			proName.blur();
		}else if(proContact.attr("validateStatus") != "true"){
			confirm("联系人输入有错，无法保存")
			proContact.blur();
		}else if(proPhone.attr("validateStatus") != "true"){
			confirm("号码输入有错，无法保存")
			proPhone.blur();
		}else if(Picture.attr("validateStatus") != "true"){
			confirm("上传图片格式不正确！无法保存")
			proPhone.blur();
		}
		else{
			if(confirm("是否确认提交数据")){
				$("#providerForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
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