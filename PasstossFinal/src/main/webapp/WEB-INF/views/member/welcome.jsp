<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="../include/head.jsp" />
<jsp:include page="../include/js.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="../resources/css/welcome.css">
</head>

<body class="bg-gradient-primary">
	<div class="container">
		<div class="join-wrap">
			<div class="content">
				

				<div class="notice">
					<c:choose>
						<c:when test="${belong == 1}">
							<img src="../resources/image/welcome.png">
							<p class="title">패스토스 가입을 축하합니다.</p>
							<p class="sub-title">쉽고 빠른 협업, 패스토스와 함께하세요.</p>
						</c:when>
						<c:when test="${belong == 0}">
							<img src="../resources/image/승인대기.jpg">
							<p class="title">관리자의 가입승인을 기다리고 있어요</p>
							<p class="sub-title">관리자가 승인 완료 시 이용 가능합니다.</p>
						</c:when>
					</c:choose>
					
				</div>
				<div class="pb-4">
					<a href="login" class="btn btn-primary goLoginBtn">로그인 화면으로</a>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>