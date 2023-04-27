<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류 처리 페이지</title>
<style>
	body{width:500px;margin:3em auto}
	div{color:orange;font-size:30px;text-align:Center}
	span{font-size:1.5rem;color:#5d5de2}
</style>
</head>
<body>
	<div>
		<p><img src="${pageContext.request.contextPath}/resources/image/error.png" width=100px>  </p>
		 <span>요청하신 <b>${url }</b> 처리에 오류가 발생하였습니다</span>
		<hr/>
		${message }
	
	</div>

</body>
</html>