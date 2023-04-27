<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:authentication property="principal" var="pinfo" />
<html>
<jsp:include page="../include/manager.jsp" />
<link href="../resources/css/companyInfo.css" rel="stylesheet" type="text/css">
<script src="../resources/js/manager/companyInfo.js"></script>
<body id="page-top">
	<script>
		$(function() {
			let resultMsg = "${result}";
			console.log(resultMsg);
			if (resultMsg == 'companyUpdate') {
				$("#resultMessage").text('회사 정보가 변경되었습니다.');
				$("#resultWrap").fadeIn(0).fadeOut(1500);
			}
		})
	</script>	
	<div id="wrapper">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div id="content-wrapper">
			<div id="content">
				<div class="container-fluid">
					<div id="company-info" class="view">
						<form id="company-info-form" action="updateCompany" method="post" enctype="multipart/form-data">
							<input type="hidden" name="company_id" value="${pinfo.companyId}">
							<div class="title mt-4 mb-4">회사 정보</div>
							<hr>
							<div class="setting-item">
								<h6>회사명</h6>
								<input name="company_name" type="text" value="${pinfo.companyName}">
								<p id="name-message" class="message"></p>
							</div>
							<div class="setting-item">
								<c:set var="logo" value="${pinfo.logo}" />
								<h6>로고 설정</h6>
								<div class="img-wrap">									
									<c:if test="${empty logo}">
										<c:set var="logo" value="blank.jpg" />
									</c:if>
									<img id="companyLogo" src="../logo/${logo}">
								</div>
								<label class="logo btn btn-secondary">
									로고변경
									<input name="logoFile" type="file" accept=".png, .jpg, .jpeg" class="d-none">
								</label>
								<c:if test="${logo != 'blank.jpg'}">
									<button id="deleteLogo" class="btn btn-danger">삭제</button>
								</c:if>
								<span id="logo-message" class="message"></span>
								<p class="notice">권장사항: 400*100px, PNG/최대 500KB</p>

							</div>
							<div class="setting-item">
								<h6>회사 URL</h6>
								<span>https://</span>
								<input name="url" class="company-url" type="text" value="${pinfo.url}">
								<span>.passtoss.team</span>
								<p id="url-message" class="message"></p>
							</div>
							<div class="setting-item">
								<h6>직원 참여 옵션</h6>
								<input name="access_option" type="radio" value="0" checked>
								<span class="option-message">관리자의 가입 승인 후, 참여하도록 설정합니다.</span>
								<br>
								<input name="access_option" type="radio" value="1">
								<span class="option-message">기존회사 참여로 가입 시, 자동 참여되도록 설정합니다.</span>
							</div>
							<input type="submit" class="btn btn-primary" value="저장">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>