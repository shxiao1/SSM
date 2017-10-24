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
			<input type="text" name="username" class="username" placeholder="Username"> <input type="password" name="password" class="password" placeholder="Password">
			<a id=login_btn href="javascript:void(0);" style="margin-top:20px" class="easyui-linkbutton" data-options="width:'300px',height:'50px'">Sign me in</a>  
		</form>

	</div>

	<!-- Javascript -->
	<script src="/ssm/css/assets/js/supersized.3.2.7.min.js"></script>
	<script src="/ssm/css/assets/js/supersized-init.js"></script>

<script type="text/javascript">

$(function () {  
}); 

	$('#login_btn').click(function()
	{
		$('#login_form').form('submit', {  
		    url:'/ssm/sys/login',    
		    onSubmit: function(){   
		    },    
		    success:function(data){ 
		    	alert(data);
		    }    
		});  

		/* $('#login_form').submit(); */
	})
</script>
</body>

</html>

