<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authentication property="principal" var="pinfo" />

<script>
	let alarmId = '${pinfo.username}';
	getalarmlog(alarmId)
	
	
	$(function(){
		
		var alarminterval = setInterval(function() {
		    getalarmlog(alarmId);
		}, 3000); // 5초마다 호출
		
		// 중지하려면
		$(document).on('click', '#alertsDropdown', function() {
			clearInterval(alarminterval);
		})
		
		
		
	})
	

	
	
	
</script>



<c:set var="profileImg" value="${pinfo.profile_img}"/>
<c:if test="${empty profileImg}">
	<c:set var="profileImg" value="profile-default.png"/>
</c:if>
<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="logout-Msg">로그아웃 하시겠습니까?</div>
			<div class="logout-form-wrap">
				<form action="${pageContext.request.contextPath}/member/logout" method="post">
					<button class="btn btn-light logout-btn" type="button" data-dismiss="modal">취소</button>
					<input type="submit" class="btn btn-primary logout-btn" value="확인">
				</form>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="infoUpdate" tabindex="-1" role="dialog">
	<div id="resultMsg"><p>내 정보를 수정하였습니다.<p></div>
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="header .bg-gradient-light">
				<span id="profileSetting" class="profile-title">프로필 수정</span>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span class="close-btn" aria-hidden="true">×</span>
				</button>
				<img id="myImg" class="profile-img" src="../upload/${profileImg}">
				<label id="profileImgBtn" class="imageBtn">
					<input id="profileImg" type="file" name="profile_img" class="d-none" accept=".jpg, .jpeg, .png">
				</label>				
			</div>
			<div class="body">
				<div class="categories">
					<ul>
						<li class="category">
							<a id="myInfo" href="#">
								<i class="fa-solid fa-gear"></i>내 정보
							</a>
						</li>
						<li class="category">
							<a id="myPassword" href="#">
								<i class="fa-solid fa-lock"></i>비밀번호
							</a>
						</li>
						<li class="category">
							<a id="notice" href="#">
								<i class="fa-solid fa-bell"></i>알림
							</a>
						</li>
						<li class="category">
							<a id="language" href="#">
								<i class="fa-solid fa-language"></i>언어
							</a>
						</li>
					</ul>
				</div>
				<div id="myInfoSetting" class="modify-menus">
					<input type="hidden" name="updateType" value="member">
					<table class="table">
						<tr class="menu">
							<td class="infoName">아이디</td>
							<td class="info">
								<input type="text" name="id" value="${pinfo.username}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">이름</td>
							<td class="info">
								<input type="text" name="username" value="${pinfo.name}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">회사명</td>
							<td class="info">
								<input type="text" value="${pinfo.companyName}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">부서</td>
							<td class="info">
								<input type="text" name="dept" value="${pinfo.dept}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">직책</td>
							<td class="info">
								<input type="text" name="position" value="${pinfo.position}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">휴대폰 번호</td>
							<td class="info">
								(+82)&nbsp;&nbsp;
								<input id="phoneNum" name="phone" type="text" 
										class="input" value="${pinfo.phone}" disabled>
								<img src="../resources/image/edit.png" class="d-none edit">
								<div id="validateMsg" class="message"></div>
							</td>
						</tr>
					</table>
					<div class="btn-wrap">
						<button id="myInfoCancel" class="btn btn-light d-none">취소</button>
						<button id="myInfoModify" class="btn btn-primary">수정</button>
						<button id="myInfoSubmit" class="btn btn-primary d-none">확인</button>
					</div>
				</div>
				<div id="myPasswordSetting" class="modify-menus d-none">
					<table class="table">
						<tr class="menu">
							<td class="infoName">새 비밀번호</td>
							<td class="info">
								<input type="password" name="password" class="input" disabled>
								<img src="../resources/image/edit.png" class="edit d-none">
								<div id="passwordMsg" class="message"></div>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">새 비밀번호 확인</td>
							<td class="info">
								<input type="password" name="passwordRe" class="input" disabled>
								<img src="../resources/image/edit.png" class="edit d-none">
								<div id="passwordReMsg" class="message"></div>
							</td>
						</tr>
					</table>
					<div class="btn-wrap">
						<div id="message" class="message"></div>
						<button id="myPassCancel" class="btn btn-light d-none">취소</button>
						<button id="myPassModify" class="btn btn-primary">수정</button>
						<button id="myPassSubmit" class="btn btn-primary d-none">확인</button>
					</div>
				</div>
			</div>



		</div>
	</div>
</div>

<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<sec:authorize access="isAuthenticated()">		
		<!-- Sidebar Toggle (Topbar) -->
		<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
			<i class="fa fa-bars"></i>
		</button>

		<!-- Topbar Search -->
		<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
			<div class="input-group">
				<input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
				<div class="input-group-append">
					<button class="btn btn-primary" type="button">
						<i class="fas fa-search fa-sm"></i>
					</button>
				</div>
			</div>
		</form>

		<!-- Topbar Navbar -->
		<ul class="navbar-nav ml-auto">

			<!-- Nav Item - Search Dropdown (Visible Only XS) -->
			<li class="nav-item dropdown no-arrow d-sm-none">
				<a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="fas fa-search fa-fw"></i>
				</a>
				<!-- Dropdown - Messages -->
				<div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
					<form class="form-inline mr-auto w-100 navbar-search">
						<div class="input-group">
							<input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">
									<i class="fas fa-search fa-sm"></i>
								</button>
							</div>
						</div>
					</form>
				</div>
			</li>

			<!-- Nav Item - Alerts -->
			<li class="nav-item dropdown no-arrow mx-1">
				<a class="nav-link dropdown-toggle" href="#1" onclick="readalarmlog('${pinfo.username}')" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="fas fa-bell fa-fw"></i>
					
				</a>
				<!-- Dropdown - Alerts -->
				<div id="gnb_alarm_wrap" class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
					<h6 class="dropdown-header">새글 알림</h6>
			
					
					<div id="getalarmpos">
					
					</div>
					
				
					<a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
				</div>
			</li>

			<!-- Nav Item - Messages -->
			<!-- <script src="../resources/js/board/topbarchat.js"></script>	 -->
			<li class="nav-item dropdown no-arrow mx-1 messagesdrop" id="showdrop1">
				<a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="fas fa-envelope fa-fw"></i>
					<!-- Counter - Messages -->
				</a>
				<!-- Dropdown - Messages -->
				<div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" id="showdrop2" aria-labelledby="messagesDropdown">
					<h6 class="dropdown-header">채팅</h6>
					<div class="input-group chatsearch" >
						<input type="text" class="form-control bg-light small" id="chat_Search"
								placeholder="채팅방 검색" aria-label="Search" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-light" type="button">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
 					<script src="../resources/js/chat/chatList.js"></script> 
				</div>	
			</li>

			<!-- Nav Item - contact -->
			<script src="../resources/js/chat/contactlist.js"></script>
			<script src="https://kit.fontawesome.com/584b0a0b8f.js" crossorigin="anonymous"></script>
			<li class="nav-item dropdown no-arrow mx-1">
				<a class="nav-link dropdown-toggle barcontact" href="../myChat/contactlist" id="alertsDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="fa-solid fa-address-book fa-lg" style="color: #d9d9d9;"></i>
				</a>
				<!-- Dropdown - contact -->
				<div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown" id="contactdrop">
					<h6 class="dropdown-header">연락처</h6>
					<div class="input-group chatsearch" id="contactinput">
						<input type="text" class="form-control bg-light small" placeholder="이름 검색" aria-label="Search" aria-describedby="basic-addon2" id="contactsearch">

						<div class="input-group-append">
							<button class="btn btn-light" type="button">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</div>
			</li>

			<div class="topbar-divider d-none d-sm-block"></div>

			<!-- Nav Item - User Information -->
			<li class="nav-item dropdown no-arrow">
				<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<span class="mr-2 d-none d-lg-inline text-gray-600 small">${pinfo.name}</span>
					<img id="myProfileImg" class="img-profile rounded-circle" src="../upload/${profileImg}">
				</a>
				<!-- Dropdown - User Information -->
				<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
					<a class="dropdown-item" href="#" data-toggle="modal" data-target="#infoUpdate">
						<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 프로필
					</a>
					<a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
						<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> 로그아웃
					</a>
				</div>
			</li>

		</ul>
	</sec:authorize>
</nav>

