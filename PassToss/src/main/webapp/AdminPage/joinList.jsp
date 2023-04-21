<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>ADMIN - 회원 관리</title>
<link rel="stylesheet" href="css/joinList.css" type="text/css">
<jsp:include page="../AdminPage/leftMenu.jsp" />
<jsp:include page="../include/head.jsp" />
<script src="js/joinList.js"></script>
</head>
<body>
	<input type="hidden" id="search_field" value="${search_field}">
	<div class='container box_radius15 board_container'>
	<input type="hidden" id="category_val" value="${category_index}">
		<div class="category">
			<div>CATEGORY</div>
			<ul class="menu">
				<li><a href="AdminMemberList.net?category=0"
					class="authority">준회원 명단</a></li>
				<li><a href="AdminMemberList.net?category=1"
					class="authority">정회원 명단</a></li>
			</ul>
		</div>
		<div class="content">
			<c:if test="${listcount == 0}">
				<table class="table">
					<thead>
						<tr>
							<th colspan="6">${category}명단 <span class="count">|
									가입인원 : ${listcount}</span></th>
						</tr>
						<tr>
							<th><input type="checkbox" id="selectAll"></input></th>
							<th>번호</th>
							<th>id</th>
							<th>이름</th>
							<th>부서번호</th>
							<th>가입일자</th>
						</tr>
					</thead>
				</table>
				<c:if test="${!empty search_word}">
					<c:set var="result" value="검색결과가 없습니다."></c:set>
				</c:if>
				<c:if test="${empty search_word}">
					<c:set var="result" value="회원이 없습니다."></c:set>
				</c:if>
				<h3 style="text-align:center">${result}</h3>
			</c:if>
			
			<c:if test="${listcount > 0}">
				<table class="table">
					<thead>
						<tr>
							<th colspan="5">${category}명단 <span class="count">|
									가입인원 : ${listcount}</span></th>
							<th>
								<div class="dropdown">
									<img src="image/more.png"
										class="btn btn-sm dropdown-toggle more" role="button"
										data-bs-toggle="dropdown" aria-expanded="false">
									<ul class="dropdown-menu">
										<li><button type="button" class="dropdown-item authorize">권한수정</button></li>
										<li><form action="AdminDelete.net" method="post"
												id="delete">
												<input type="submit" class="dropdown-item delete" value="삭제">
											</form></li>
									</ul>
								</div>
							</th>
						</tr>
						<tr>
							<th><input type="checkbox" id="selectAll"></input></th>
							<th>번호</th>
							<th>id</th>
							<th>이름</th>
							<th>부서번호</th>
							<th>가입일자</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="num" value="${listcount-(page-1)*limit}" />
						<c:forEach var="m" items="${joinlist}">						
							<tr class="line">
								<td><input type="checkbox" class="select" value="${m.id}"></td>
								<td><c:out value="${num}" /> <c:set var="num"
										value="${num - 1}" /></td>
								<td><button type="button" class="info"
										data-bs-toggle="modal" data-id="${m.id}"
										data-bs-target="#modal1">${m.id}</button></td>
								<td>${m.name}</td>
								<td>${m.deptno}</td>
								<td>${m.joindate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<form action="AdminMemberList.net?category=${category_index}"
					method="post" id="search">
					<div class="input-group">
						<select id="select_value" name="search_field">
							<option value="0" selected>id</option>
							<option value="1">이름</option>
							<option value="2">부서번호</option>
						</select> <input name="search_word" type="text" class="form-control"
							placeholder="검색어를 입력하세요" value="${search_word}">
						<button class="btn btn-dark" type="submit" name="searchbutton">검색</button>
					</div>
				</form>


				<div class="center-block">
					<ul class="pagination justify-content-center">
						<c:if test="${page <= 1}">
							<li class="page-item"><a class="page-link gray">이전&nbsp;</a></li>
						</c:if>
						<c:if test="${page > 1}">
							<li class="page-item"><a
								href="AdminMemberList.net?page${page-1}&search_field=${search_field}&search_word=${search_word}"
								class="page-link">이전</a>&nbsp;</li>
						</c:if>

						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page}">
								<li class="page-item active"><a class="page-link">${a}</a></li>
							</c:if>
							<c:if test="${a != page}">
								<c:url var="go" value="AdminMemberList.net">
									<c:param name="search_field" value="${search_field}" />
									<c:param name="search_word" value="${search_word}" />
									<c:param name="page" value="${a}" />
								</c:url>
								<li class="page-item"><a href="${go}" class="page-link">${a}</a>
								</li>
							</c:if>
						</c:forEach>

						<c:if test="${page >= maxpage}">
							<li class="page-item"><a class="page-link gray">&nbsp;다음</a></li>
						</c:if>
						<c:if test="${page < maxpage}">
							<c:url var="next" value="AdminMemberList.net">
								<c:param name="search_field" value="${search_field}" />
								<c:param name="search_word" value="${search_word}" />
								<c:param name="page" value="${page+1}" />
							</c:url>
							<li class="page-item"><a href="${next}" class="page-link">&nbsp;다음</a>
							</li>
						</c:if>
					</ul>
				</div>
			</c:if>
		</div>
	</div>

	<!-- 아이디 정보 Modal1 -->
	<div class="modal fade" id="modal1" tabindex="-1"
		aria-labelledby="idInfoLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="idInfoLabel">Modal title</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">내용</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 권한수정 Modal2 -->
	<div class="modal fade" id="modal2" tabindex="-1"
		aria-labelledby="idInfoLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">권한수정</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">

					<!-- <div class="modal-footer"> -->
					<button type='submit' class='btn btn-primary' id='authorizebtn'>권한설정</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
</body>