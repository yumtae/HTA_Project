<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<jsp:include page="../include/head1.jsp" />
<jsp:include page="../include/js.jsp"></jsp:include>
<script src="../resources/js/member/forgotPw.js"></script>
<link rel="stylesheet" href="../resources/css/forgotPw.css">

<body class="bg-gradient-primary">

	<div class="container">

		<!-- 비밀번호 찾기 -->
		<div id="forgotPassword">
			<div class="row justify-content-center">
				<div class="col-xl-10 col-lg-12 col-md-9">
					<div class="card o-hidden border-0 shadow-lg my-5">
						<div class="card-body p-0">
							<!-- Nested Row within Card Body -->
							<div class="row">
								<div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
								<div class="col-lg-6">
									<div class="p-5 mt-7">
										<div class="text-center">
											<h1 class="h4 text-gray-900 mb-2">비밀번호 찾기</h1>
											<p class="mb-4">이메일 인증 후 비밀번호를 재설정 하세요</p>
										</div>
										<form class="user">
											<div class="form-group">
												<input type="email" class="form-control form-control-user" id="InputEmail" placeholder="이메일 주소 입력">
												<p class="message"></p>
											</div>
											<button type="button" id="authNumSend" class="btn btn-primary btn-user btn-block mt-4">이메일 인증</button>											
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 이메일 인증 모달 -->
		<div class="modal" tabindex="-1" id="emailAuth">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">인증번호 입력</h5>
					</div>
					<div class="modal-body">
						<p class="authInfo">
							<span></span>
							으로<br>5자리 인증번호가 전송되었습니다.
						</p>
						<div class="input-wrap">
							<input type="text" id="authNumber" class="authInput" maxlength="6" placeholder="인증번호를 입력하세요">
							<div id="authTime-count"></div>
						</div>
						<p id="authMessage"></p>
					</div>
					<div class="modal-footer">
						<button type="button" id="authNumCheck" class="btn btn-primary">확인</button>
					</div>
				</div>
			</div>
		</div>


		<div id="resetPw" class="d-none">
			<div class="resetPw-wrap">
				<div class="resetPw-title">비밀번호 재설정</div>
				<div class="resetPw-subtitle">새 비밀번호를 입력하세요</div>
				<div class="resetPw-form-wrap">
					<label>
						<span class="input-name">이메일</span>
						<input type="text" id="id" class="resetPw-input" name="id" readOnly>
					</label>
					<label>
						<span class="input-name">새 비밀번호</span>
						<input type="password" class="resetPw-input " name="password" placeholder="비밀번호(8~20자 영문,숫자,특수문자 조합)" maxlength="20">
						<p id="password-message" class="message"></p>
					</label>
					<label>
						<span class="input-name">새 비밀번호 확인</span>
						<input type="password" class="resetPw-input" name="passwordRe" placeholder="비밀번호 재입력" maxlength="20">
						<p id="passwordRe-message" class="message"></p>
					</label>
					<button type="button" id="NewPassword" class="btn btn-primary resetPw-button">비밀번호 재설정</button>
				</div>
			</div>

			<div class="modal" tabindex="-1" id="resetComplete">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<img id="completeImg" src="../resources/image/resetPw.png">
						<p class="title">비밀번호 변경 완료</p>
						<p class="content">비밀번호 변경이 완료되었습니다.</p>
						<p class="content">새로운 비밀번호로 로그인 해주세요.</p>
						<a href="login" class="btn btn-primary">로그인 화면</a>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	
</body>
</html>