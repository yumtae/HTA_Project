<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../include/head.jsp" />

<style>
.join-wrap {
	margin: 3rem auto;
	width: 700px;
	height:700px;
	background: white;
	border-radius:20px;
}

.join-title {	
	padding-top: 40px;
	font-size: 36px;
	color: #333;
	font-weight: 700;
	text-align: center;
	margin-bottom: 100px;
}

.join-menu {
	position: relative;
	width:80%;
	justify-content:center;
	margin: 30px auto;
	padding: 44px 170px 56px 30px;
	background: #fcfbff;
	border: 1px solid #c6b6f6;
	border-radius: 20px;
	cursor: pointer;
}

.join-menu:hover{
	color: #623ad6;
	border-color:#623ad6;
}

b{
	font-size:30px;
	color: #623ad6;
}

img{
	display: inline-block;
    width: 170px;
    position: absolute;
    right: 9px;    
}
.newCompanyImg{bottom:0}
</style>

<body class="bg-gradient-primary">

	<div class="container">
	
		<!-- 회원가입 선택 -->
		<div class="join-wrap">
			<div class="join-title">회원가입</div>
			<div class="join-menus">
				<div class="join-menu">
					<div class="NewCompany">
						<b>새 회사 만들기</b>
						<p>새로운 회사 또는 조직을 만들어 시작합니다.</p>
						<img class="newCompanyImg" src="../resources/image/Company.png">
					</div>					
				</div>
				<div class="join-menu">
					<div class="joinCompany">
						<b>기존 회사 참여</b>
						<p>참여할 회사의 URL로 참여합니다.</p>
						<img src="../resources/image/join.png">
					</div>					
				</div>
			</div>
		</div>
		
	</div>
	<script>
		$(".NewCompany").click(function(){
			location.href="${pageContext.request.contextPath}/member/newCompany"
		})
		$(".joinCompany").click(function(){
			location.href="${pageContext.request.contextPath}/member/joinCompany"
		})
	</script>
</body>
</html>