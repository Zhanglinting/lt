	
//验证用户名是否存在
function hasName(){
	$("#warning_1").css("display","none")
	var username = $("#regist_username").val().trim();
	var msg = username+":"+"12";
	var base64_msg = Base64.encode(msg);
	$.ajax({
		url:"http://localhost:8080/cloud_note/user/login.do",
		type:"post",
		dataType:"json",
		beforeSend:function(xhr){
			xhr.setRequestHeader(
				"Authorization","basic "+base64_msg	
			);
		},
		success:function(result){
			if(result.status!=1){
				$("#warning_1").css("display","block")
			}
		}
	});
}


//注册新用户
function regist(){

	$(".warning").css("display","none")
   		
   		//获取表单数据
   		var name = $("#regist_username").val();
   		var nickname = $("#nickname").val();
   		var regist_password = $("#regist_password").val();
   		var final_password = $("#final_password").val();
   		
   		//验证两次输入密码输入是否一致
   		if(regist_password!=final_password){
   			$("#warning_3").css("display","block")
   		}
   		
   		//验证是否有没通过验证的
   		flag = true;
   		for(var i = 0;i<$("div .warning").length;i++){
   			if($("div .warning:eq("+i+")").css("display")!="none"){
   				flag = false;
   			}
   		}
   		
   		//验证通过 发送请求
   		if(flag){
        	$.ajax({
				url:"http://localhost:8080/cloud_note/user/regist.do",
			type:"post",
			data:{cn_user_name:name,cn_user_desc:nickname,cn_user_password:regist_password},
			dataType:"json",
			success:function(result){
				if(result.status==0){//注册成功
					alert("注册成功"+"\n"+"欢迎你，返回登录吧:) :"+name);
					$("#back").click();
				}else if(result.status==1){//用户名为空
					$("#warning_11").css("display","block")
				}else if(result.status==2){//昵称为空
					$("#warning_44").css("display","block")
				}else if(result.status==3){//密码为空
					$("#warning_22").css("display","block")
				}
			},
			error:function(){
				alert("注册异常");
				}
			});
    	}
   	}