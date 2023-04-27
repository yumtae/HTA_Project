<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<jsp:include page="../include/head.jsp" />

<style>
.join-wrap {
	width: 700px;
	height: 500px;
	margin: 3rem auto;
	background: white;
	border-radius: 20px;
}
.content{text-align:center;}
img {
	width: 300px;
}
.notice{
	color:#333;	
	margin:20px 0 30px 0;
}
p{margin-bottom:0;}
.notice p:nth-child(1){
	font-size:20px;
	font-weight:700;
}
.goMain{width:500px;}
</style>
<body class="bg-gradient-primary">
	<div class="container">
		<div class="join-wrap">
			<div class="content">
				<img src="../resources/image/work-together.jpg">

				<div class="notice">
					<p>관리자의 가입승인을 기다리고 있어요</p>
					<p>관리자가 승인 완료 시 이용 가능합니다.</p>
				</div>
				<button type="button" class="btn btn-primary goMain">로그인 화면으로 이동</button>
			</div>
		</div>
	</div>
	<jsp:include page="../include/js.jsp"></jsp:include>
	<script>
		$(".goMain").click(function(){
			location.href="${pageContext.request.contextPath}/member/login"
		})
	</script>
</body>
</html>