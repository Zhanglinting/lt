//登录页面中的脚本文件
//页面加载完毕后执行脚本
$(function(){
	//测试
	//console.log("ok");
	//登录按钮绑定事件
	$('#login').click(loginAction);//1.空参数引起点击事件2.加function(){}后绑定事件3.绑定到loginAction方法
     //获得焦点事件,失去焦点事件
	$('#count').focus().blur(checkUsername);//checkUsername()加括号直接调用了
	$('#password').blur(checkPassword);
	
	$('#regist_button').click(registAction);
	
	$('#regist_name').blur(checkRegistname);
	$('#regist_password').blur(checkRegistpwd);
	$('#final_password').blur(checkFinalpwd);
});
//检查注册用户名
function checkRegistname(){
	console.log('checkRegistname()');
	var name=$('#regist_name').val();
	console.log(name);
	if(!name){
		$('#registname_msg').show();
		$('#registname_msg span').html('用户名不能为空');
		return false;
	}
	var reg=/^\w{3,10}$/;// ^$从头到尾,\w单个任意字符(包含大小写),3到10个,
	if(reg.test(name)){
		$('#registname_msg span').empty();
		return true;
	}else{
		$('#registname_msg').show();
		$('#registname_msg span').html('必须3-10个字母或数字');
		return false;
	}
}
//检查注册密码
function checkRegistpwd(){
	var pwd = $('#regist_password').val();

	if(!pwd){
		$('#registpwd_msg').show();
		$('#registpwd_msg span').html('密码不能为空');
		return false;
	}
	var reg =/^\w{3,10}$/;
	if(reg.test(pwd)){
		$('#registpwd_msg span').empty();
		//检验通过
		return true;
	}else{
		$('#registpwd_msg').show();
		$('#registpwd_msg span').html('必须3-10个字母或数字');
		return false;
	}
}
//检查确认密码
function checkFinalpwd(){
	console.log('checkFinalpwd()');
	var pwd = $('#regist_password').val();
	var fpwd = $('#final_password').val();
	if(!fpwd){
		$('#warning_3').show();
		$('#warning_3 span').html('密码不能为空');
		return false;
	}
	if(pwd!=fpwd){
		$('#warning_3').show();
		$('#warning_3 span').html('密码输入不一致');
		return false;
	}
	//密码相等
	$('#warning_3 span').empty();
	return true;
}
//注册
function registAction(){
	
	//表单数据检验不通过,不能注册
	if(checkRegistname()+checkRegistpwd()+checkFinalpwd()!=3){
		return;
	}
	console.log('registAction');
	var url = 'user/regist.do';
	var param={
			name:$('#regist_name').val(),
            descr:$('#descr').val(),
            password:$('#regist_password').val(),
            confirm:$('#final_password').val()
			};
	console.log(param);
	//发起AJAX请求
	$.post(url,param,function(result){
		if(result.state==0){
			//注册成功
			console.log(result);
			console.log('注册成功');
			//注册成功以后跳到登录页面
			var user=result.data;
			$('#back').click();
			$('#count').val(user.name);
			$('#count-msg').empty();
			$('#password').focus();
		}else if(result.state==2){
			//用户名错误
			$('#registname_msg span').html(result.message);
			('#registname_msg').show();
		}else if(result.state==3){
			//密码错误
			$('#registpwd_msg span').html(result.message);
			$('#registpwd_msg').show();
		}else{
			alert(result.message);
		}
	});
	}
//检查用户名
function checkUsername(){
	console.log('checkUsername');
	var name=$('#count').val();	
	if(!name){
		$('#count-msg').html('用户名不能为空');
		return false;
	}
	var reg=/^\w{3,10}$/;// ^$从头到尾,\w单个任意字符(包含大小写),3到10个,
	if(reg.test(name)){
		$('#count-msg').empty();
		return true;
	}else{
		$('#count-msg').html('必须3-10个字母或数字');
		return false;
	}
}
//检查密码
function checkPassword(){
	console.log('checkPassword');
	var pwd = $('#password').val();
	if(!pwd){
		$('#password-msg').html('不能为空');
		return false;
	}
	var reg =/^\w{3,10}$/;
	if(reg.test(pwd)){
		$('#password-msg').empty();
		//检验通过
		return true;
	}else{
		$('#password-msg').html('必须3-10个字母或数字');
		return false;
	}
}

function loginAction(){
	//检验不通过不能登录
	if(checkPassword()+checkUsername()!=2){
		return;
	}
	//console.log('login click');
	//获取表单中的参数,利用AJAX发送到控制器,检查控制器返回值,
	//如果state==0成功转到edit.html
	var username = $('#count').val();
	var password = $('#password').val();
	//console.log(username);
	//console.log(password);
	//向服务器提交数据
	var url = 'user/login.do';
	var param = {name:username,password:password};//name和password(key)与服务器端参数一致
	$.post(url,param,function(result){//result 服务器返回的数据
		if(result.state==0){
			//成功
			console.log('登录成功');
			console.log(result.data);
			  //跳转到edit.html页面
			//登录成功以后保存userid到cookie
			var user = result.data;
			setCookie('userId',user.id);
		    location.href='edit.html';
		   
		}else if(result.state==2){
			//用户名错误
			$('#count-msg').html(result.message);
		}else if(result.state==3){
			//密码错误
			$('#password-msg').html(result.message);
		}else{
			//其它错误
			alert(result.message);
		}
	});
}