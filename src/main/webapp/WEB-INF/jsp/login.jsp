<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

<%@include file="/WEB-INF/head/head.jsp"%>


<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- CSS -->
<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
<link rel="stylesheet" href="/ssm/css/assets/css/reset.css">
<link rel="stylesheet" href="/ssm/css/assets/css/supersized.css">
<link rel="stylesheet" href="/ssm/css/assets/css/style.css">

</head>

<body>

	<div class="page-container">
		<h1>Login</h1>
		<form id="login_form" method="post">
			<input type="text" id="login_username" class="username" placeholder="Username"> <input type="password" id="login_password" name="password" class="password" placeholder="Password">
			<a id=login_btn href="javascript:void(0);" style="margin-top:20px" class="easyui-linkbutton" data-options="width:'300px',height:'50px'">Sign me in</a>  
		</form>

	</div>

	<!-- Javascript -->
	<script src="/ssm/css/assets/js/supersized.3.2.7.min.js"></script>
	<script src="/ssm/css/assets/js/supersized-init.js"></script>

<script type="text/javascript">

$(function () {  
}); 
	//用户名不为空
	$('#login_username').blur(function(){
		console.log(name);
		var name=$('#login_username').val();
		console.log(name);
		if(name==undefined||name==""){
			$.messager.alert("注意","请输入用户名");
		}
		
		//console.log($('#login_username').val());		
	});
	//密码不能为空
	$('#login_password').blur(function(){
		console.log(name);
		var name=$('#login_password').val();
		console.log(name);
		if(name==undefined||name==""){
			$.messager.alert("注意","请输入密码");
		}
		
		//console.log($('#login_username').val());		
	});
	
	$('#login_btn').click(function()
	{
		$('#login_form').form('submit', {  
		    url:'/ssm/sys/login',    
		    onSubmit: function(){   
		    },    
		    success:function(data){ 
		    	window.location.href = "/ssm/sys/login";
		    }    
		    error:function(data){ 
		    	window.location.href = "/ssm/sys/login";
		    }  
		});  

		/* $('#login_form').submit(); */
	})
</script>
</body>

</html>

