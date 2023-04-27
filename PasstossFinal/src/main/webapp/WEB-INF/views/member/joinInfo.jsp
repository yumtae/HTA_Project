<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../include/head.jsp" />

<style>
.join-wrap {
	width: 700px;
	height:800px;
	margin: 3rem auto;
	background: white;
	border-radius:20px;
}

.join-title {	
	padding: 20px 0 16px;
    font-size: 36px;
    color: #333;
    font-weight: 700;
    text-align: center;
}
.join-subtitle{
	margin: 0 auto;
    padding: 0 20px;
    font-weight: 500;
    font-size: 18px;
    color: #333;
    text-align: center;
    line-height: 27px;
}
.join-form-wrap{
	width: 586px;
    margin: 80px auto 0 auto;
    text-align: center;
}
label{
	margin-top:30px;
	display:block;
	text-align:left;
}
.input-name{
	display: inline-block;
    margin-bottom: 10px;
    padding: 3px 0;
    font-size: 16px;
    color: #333;
    font-weight: 700;
    text-align: left;
}

.join-input{
	color: #333;
    font-size: 16px;
    height: 50px;
    width: 560px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-sizing: border-box;
    padding-left: 20px;
}

.join-input:focus{
	outline:none;
	color: #333;
    border: 1px solid #989898;
    box-shadow: 2px 2px 6px rgba(0,0,0,.15);
}

.join-button{
	margin-top:30px;
	width:560px;
	height: 50px;
	display:inline-block;
	float:left;
}
.modal-header, .modal-footer{
	border:none;
}
.authMessage{
	text-align:center;
}
input{border:0}
.authNumber{
	outline:none;
	border-bottom: 1px solid #777;
    margin-top: 10px;
    width: 100%;
    height: 40px;
    font-size: 20px;
    font-weight: 300;
    text-align: center;
}
.modal-title{
	color:black;
	font-weight: bold;
}
.modal-body{
	color:#555;
	font-weight: 500;
}
.submit{
	width:100%;
}
.input-wrap{position: relative;}
.authTime-count{
	position: absolute;
    right: 15px;
    top: 22px;
    color: #ff6b6b;
    font-weight: 500;
}
</style>
<body class="bg-gradient-primary">
	<div class="container">

		<div class="join-wrap">
			<div class="join-title">새 회사 만들기</div>
			<div class="join-subtitle">개인 정보를 입력해 주세요</div>
			<div class="join-form-wrap">
				<form>
					<label>
						<span class="input-name">이메일</span>
						<input type="text" class="join-input" name="id" placeholder="example@gmail.com">
						<p class="error-message"></p>
					</label>
					<label>
						<span class="input-name">이름</span>
						<input type="text" class="join-input" name="name" placeholder="홍길동">
						<p class="error-message"></p>
					</label>
					<label>
						<span class="input-name">비밀번호</span>
						<input type="password" class="join-input" name="password" placeholder="비밀번호(8~20자 영문,숫자,특수문자 조합)">
						<p class="error-message"></p>
					</label>
					<label>
						<span class="input-name">비밀번호 확인</span>
						<input type="password" class="join-input" placeholder="비밀번호 재입력">
						<p class="error-message"></p>
					</label>
					
					<button type="button" class="btn btn-primary join-button"
							data-bs-toggle="modal" data-bs-target="#emailAuth">다음</button>
				</form>
			</div>
		</div>

		<div class="modal" tabindex="-1" id="emailAuth">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">인증번호 입력</h5>
					</div>
					<div class="modal-body">
						<p class="authMessage">${id} 으로<br>5자리 인증번호가 전송되었습니다.</p>
						<div class="input-wrap">
							<input type="text" class="authNumber" placeholder="인증번호를 입력하세요">
							<div class="authTime-count">3:00</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary submit">확인</button>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
	<jsp:include page="../include/js.jsp"></jsp:include>
	
	<script>
		$(".submit").click(function(){
			location.href="${pageContext.request.contextPath}/member/newCompanyInfo"
		})
	</script>
</body>
</html>