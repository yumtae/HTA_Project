<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../include/head1.jsp" />
<jsp:include page="../include/js.jsp"></jsp:include>
<style>
	.container{padding-top:8rem;}
	.message{color:red}
</style>
<body class="bg-gradient-primary">

	<div class="container">
		<!-- 로그인 창 -->
		<div id="loginForm">
			<div class="row justify-content-center">
				<div class="col-xl-10 col-lg-12 col-md-9">
					<div class="card o-hidden border-0 shadow-lg my-5">
						<div class="card-body p-0">
							<!-- Nested Row within Card Body -->
							<div class="row">
								<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
								<div class="col-lg-6">
									<div class="p-5">
										<div class="text-center">
											<h1 class="h4 text-gray-900 mb-4">로그인</h1>
										</div>
										<form class="user" method="post" action="loginProcess">
											<div class="form-group">
												<input type="email" class="form-control form-control-user" name="id" id="InputId" placeholder="아이디">
											</div>
											<div class="form-group">
												<input type="password" class="form-control form-control-user" name="password" id="InputPassword" placeholder="비밀번호">
											</div>
											<p class="message">
												<c:if test="${!empty errorMsg}">${errorMsg}</c:if>
											</p>
											<div class="form-group">
												<div class="custom-control custom-checkbox small">
													<input type="checkbox" class="custom-control-input" name="remember-me" id="customCheck">
													<label class="custom-control-label" for="customCheck">자동 로그인</label>
												</div>
											</div>
											<input type="submit" class="btn btn-primary btn-user btn-block" value="로그인">
										</form>
										<hr>
										<div class="text-center">
											<a id="goForgotPass" class="small" href="forgotPw">비밀번호 찾기</a>
										</div>
										<div class="text-center">
											<a class="small" href="join">회원가입</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>