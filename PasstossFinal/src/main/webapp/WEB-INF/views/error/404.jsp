<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404에러</title>
<style>
	body{width:800px;margin:3em auto}
	div{color:orange;font-size:30px;text-align:Center}
	span{font-size:1.3rem;color:#5d5de2}
</style>
</head>
<body>
	<div>
		${exception }<br/>
		<p><img src="${pageContext.request.contextPath}/resources/image/error.png" width=100px>  </p>
		 <span>요청하신 <b>${url }</b>이 존재하지 않습니다</span>
		<hr/>
	
	</div>

</body>
</html>