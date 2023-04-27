<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../include/head1.jsp" />
<jsp:include page="../include/js.jsp"></jsp:include>
<script src="../resources/js/member/joinInfo.js"></script>
<link rel="stylesheet" href="../resources/css/signUp.css">

<body class="bg-gradient-primary">

	<div class="container">
		<form id="joinForm" action="joinPro" method="post">
			<input type="hidden" name="memberType">
			<input type="hidden" name="auth_status" value="0">
			<input type="hidden" name="belong" value="0">
			<input type="hidden" id="companyId">
			<input type="hidden" id="ceo_id">
			
			<!-- 회원가입 선택 -->
			<div id="join-select">
				<div class="join-wrap">
					<div class="join-title">회원가입</div>
					<div class="join-menus">
						<div class="join-menu">
							<div id="NewCompany">
								<b>새 회사 만들기</b>
								<p>새로운 회사 또는 조직을 만들어 시작합니다.</p>
								<img class="menuImg" src="../resources/image/Company.png">
							</div>
						</div>
						<div class="join-menu">
							<div id="joinCompany">
								<b>기존 회사 참여</b>
								<p>참여할 회사의 URL로 참여합니다.</p>
								<img class="menuImg" src="../resources/image/joinCompany.png">
							</div>
						</div>
					</div>					
				</div>
			</div>

			<!-- 가입 정보 입력 -->
			<div id="join-info" class="d-none">
				<div class="join-wrap">
					<div class="join-title">새 회사 만들기</div>
					<div class="join-subtitle">개인 정보를 입력해 주세요</div>
					<div class="join-form-wrap">
						<label>
							<span class="input-name">이메일</span>
							<input type="text" class="join-input info-input" name="id" placeholder="example@gmail.com(50자 이내)" maxlength="50">
							<p id="id-message" class="message"></p>
						</label>
						<label>
							<span class="input-name">이름</span>
							<input type="text" class="join-input info-input" name="username" placeholder="홍길동" maxlength="20">
							<p id="name-message" class="message"></p>
						</label>
						<label>
							<span class="input-name">비밀번호</span>
							<input type="password" class="join-input info-input" name="password" placeholder="비밀번호(8~20자 영문,숫자,특수문자 조합)" maxlength="20">
							<p id="password-message" class="message"></p>
						</label>
						<label>
							<span class="input-name">비밀번호 확인</span>
							<input type="password" class="join-input info-input" name="passwordRe" placeholder="비밀번호 재입력" maxlength="20">
							<p id="passwordRe-message" class="message"></p>
						</label>
						<button type="button" id="goEmailAuth" class="btn btn-primary join-button" data-bs-toggle="modal" data-bs-target="#emailAuth">다음</button>
					</div>
				</div>

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
								<button type="button" id="goCompanyInfo" class="btn btn-primary">확인</button>
								<button type="submit" id="companyJoin" class="btn btn-primary d-none memberType">확인</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 회사 정보 입력 -->
			<div id="join-companyInfo" class="d-none">
				<div class="join-wrap">
					<div class="join-title">새 회사 만들기</div>
					<div class="join-subtitle">회사 정보를 입력해 주세요</div>
					<div class="join-form-wrap">

						<label>
							<div class="input-name">회사이름</div>
							<input type="text" id="companyName" class="join-input company-input" name="company_name" placeholder="회사 이름">
							<p class="message"></p>
						</label>
						<label>
							<div class="input-name">회사 URL</div>
							<span>https://</span>
							<input type="text" id="companyURL" class="join-input url company-input" name="url" placeholder="회사 URL">
							<span>.passtoss.team</span>
							<p id="urlMessage" class="message"></p>
						</label>
						<label>
							<div class="input-name">회사 업종</div>
							<select id="joinType" name="type" class="company-input">
								<option value="">회사 업종을 선택하세요</option>
								<option value="IT">정보통신업(IT)</option>
								<option value="Franchise">F&B, 프랜차이즈</option>
								<option value="Logistics">도·소매 유통 판매</option>
								<option value="Entertain">엔터테인먼트, 여행, 예술</option>
								<option value="Public">공공 행정</option>
								<option value="Construct">건설 및 기간 산업</option>
								<option value="Tax">세무, 법무, 노무</option>
								<option value="Transportation">운수 및 물류</option>
								<option value="Medical">의료 보건, 사회 복지</option>
								<option value="Finance">금융, 보험, 부동산</option>
								<option value="Association">협회 및 단체</option>
								<option value="Research">교육 및 연구</option>
								<option value="PrimaryIndustry">1차 산업(농·임·수산업)</option>
								<option value="etc">기타</option>
							</select>
							<p id="selectMessage" class="message"></p>
						</label>

						<button type="submit" id="goJoinPro" class="btn btn-primary submit memberType">회원가입</button>

					</div>
				</div>
			</div>

			<!-- 회사 URL 입력 -->
			<div id="join-companyURL" class="d-none">
				<div class="join-wrap">
					<div class="join-title">기존 회사 참여</div>
					<div class="join-subtitle">이미 사용중인 회사라면 URL입력후 참여하세요</div>
					<div class="join-form-wrap">

						<label>
							<div class="input-name">회사 URL</div>
							<span>https://</span>
							<input id="joinCompanyURL" type="text" class="join-input url" placeholder="회사 URL">
							<span>.passtoss.team</span>
							<p id="urlMessage" class="message"></p>
						</label>

						<p class="helpMsg">회사 URL주소는 관리자를 통해 확인할 수 있습니다.</p>

						<button type="button" id="joinURL" class="btn btn-primary join-button">다음</button>

					</div>
				</div>

				<div class="modal" tabindex="-1" id="companyInfo">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">회사 정보 확인 후 시작하세요!</h5>
							</div>
							<div class="modal-body">
								<div class="input-wrap">
									<img id="companyLogo" src="../logo/blank.jpg">
									<p id="comName"></p>
									<p class="urlInfo">https://<span id="comUrl"></span>.passtoss.team</p>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" id="goJoinInfo" class="btn btn-primary submit">참여하기</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>


</body>
</html>