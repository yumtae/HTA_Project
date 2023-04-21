<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function(){
	
		$('.join').click(function(){
			location.href="join.net";
			
		})
		
		
		
		const id = '${id}';
		if(id){
			$("#id").val(id);
			$("#remember").prop('checked', true);
		}
	})
</script>
<style>

	.container{width:600px;margin:30px auto;padding:30px}
	.join{background:red}
</style>



<title>로그인 페이지</title>
<link href='css/login.css' type='text/css' rel="stylesheet">
</head>
<body>
	
  <div class="container">

	<form name='Loginform' action="loginProcess.net" method="post"  style="border:1px solid #ccc;padding:30px">
	    <label for="id"><b>아이디</b></label>
	    <input type="text" placeholder="Enter id" id='id' name="id" >
	
	    <label for="passwd"><b>비밀번호</b></label>
	    <input type="password" placeholder="Enter passwd" name="pass" >
	
		<div class='form-group custom-control custom-checkbox'>
			<input type='checkbox' class='custom-control-input' id='remember' name='remember' value='store'>
			<label class='custom-control-label' for="remember" >아이디 기억하기</label>
		</div>
	
	    <div class="clearfix">
	     <button type="submit" class="signupbtn" >로그인</button>
	     <button type="button" class="join" style='width:100%'>회원가입</button>
	     
	    </div>

	</form>
  </div>
</body>
</html>
    
    
    