<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<jsp:include page="../AdminPage/leftMenu.jsp" />
<jsp:include page="../include/head.jsp" />
<script src="js/adminboard.js"></script>
<link rel="stylesheet" href="css/adminboard.css" type="text/css">
<title>ADMIN - 게시물 관리</title>
</head>
<body>
	<div class='container box_radius15 board_container'>
		<input type="hidden" id="category_val" value="${category_index}">
		<input type="hidden" id="search_field" value="${search_field}">
		<div class="category">
			<div>CATEGORY</div>
			<ul class="menu">
				<li><a href="AdminboardList.net?category=0">사내게시판</a></li>
				<li><a href="AdminboardList.net?category=1">부서게시판</a></li>
			</ul>
			<span class="title"></span>
		</div>
		<div class="content">
			<c:if test="${listcount == 0}">
				<table class="table">
					<thead>
						<tr>
							<c:if test="${category_index == 0}">
								<c:set var="col" value="6" />
							</c:if>
							<c:if test="${category_index == 1}">
								<c:set var="col" value="7" />
							</c:if>
							<th colspan="${col}">${category} 글목록<span class="count">|글
									개수 : ${listcount}</span></th>
						</tr>

						<tr>
							<th><input type="checkbox" class="selectAll"></th>
							<th><div>번호</div></th>
							<th><div>제목</div></th>
							<th><div>작성자</div></th>
							<c:if test="${category_index == 1}">
								<th><div>부서번호</div></th>
							</c:if>
							<th><div>작성날짜</div></th>
							<th><div>조회수</div></th>
						</tr>
					</thead>
				</table>
				<h3 style="text-align: center">등록된 글이 없습니다.</h3>
			</c:if>
			<c:if test="${listcount > 0}">
				<table class="table">
					<thead>
						<tr>
							<c:if test="${category_index == 0}">
								<c:set var="col" value="4" />
							</c:if>
							<c:if test="${category_index == 1}">
								<c:set var="col" value="5" />
							</c:if>
							<th colspan="${col}">${category} 글목록<span class="count">|글
									개수 : ${listcount}</span></th>
							<th>
								<div class="rows">
									<select class="form-control" id="viewcount">
										<option value="5">5개씩</option>
										<option value="10" selected>10개씩</option>
										<option value="15">15개씩</option>
										<option value="20">20개씩</option>
									</select>
								</div>
							</th>
							<th>
								<form class="delete" action="AdminBoardDelete.net" method="post">
									<input type="hidden" name="category" value="${category_index}">
									<input type="image" src="image/delete.png">
								</form>
							</th>
						</tr>
						<tr>
							<th><input type="checkbox" class="selectAll"></th>
							<th><div>번호</div></th>
							<th><div>제목</div></th>
							<th><div>작성자</div></th>
							<c:if test="${category_index == 1}">
								<th><div>부서번호</div></th>
							</c:if>
							<th><div>작성날짜</div></th>
							<th><div>조회수</div></th>
						</tr>
					</thead>

					<tbody>
						<c:set var="num" value="${listcount-(page-1)*limit}" />
						<c:forEach var="b" items="${boardlist}">
							<tr>
								<td><input type="checkbox" class="select"
									value="${b.board_num}"></td>
								<td>
									<%-- 번호 --%> <c:out value="${num}" /> <%-- num 출력 --%> <c:set
										var="num" value="${num-1}" /> <%-- num = num-1; 의미 --%>
								</td>
								<td>
									<%-- 제목 --%>
									<div>
										<%-- 답변글 제목앞에 여백 처리 부분
										board_re_lev,, board_num,
										board_subject, board_name, board_date,
										board_readcount : property 이름 --%>
										<c:if test="${b.board_re_lev != 0}">
											<%-- 답글인 경우 --%>
											<c:forEach var="a" begin="0" end="${b.board_re_lev*2}"
												step="1">
										&nbsp;
										</c:forEach>
											<img src='image/reply.png' style="width: 30px; height: 30px">
										</c:if>

										<c:if test="${b.board_re_lev == 0}">
											<%-- 원문인 경우 --%>
										&nbsp;
									</c:if>
										<c:if test="${category_index == 0}">
											<a href="FreeDetailAction.bof?num=${b.board_num}" class="board_view"> <c:if
													test="${b.board_subject.length()>=20}">
													<c:out value="${b.board_subject.substring(0,20)}..." />
												</c:if> <c:if test="${b.board_subject.length()<20}">
													<c:out value="${b.board_subject}" />
												</c:if>
											</a>[${b.cnt}]</c:if>
										<c:if test="${category_index == 1}">
											<a href="DeptDetailAction.bof?num=${b.board_num}" class="board_view"> <c:if
													test="${b.board_subject.length()>=20}">
													<c:out value="${b.board_subject.substring(0,20)}..." />
												</c:if> <c:if test="${b.board_subject.length()<20}">
													<c:out value="${b.board_subject}" />
												</c:if>
											</a>[${b.cnt}]</c:if>
									</div>
								</td>
								<td><div>${b.board_name}</div></td>
								<c:if test="${category_index == 1}">
									<td><div>${b.board_deptno}</div></td>
								</c:if>
								<td><div>${b.board_date}</div></td>
								<td><div>${b.board_readcount}</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<form action="AdminboardList.net?category=${category_index}"
					method="post" id="search">
					<div class="input-group">
						<select id="select_value" name="search_field">
							<option value="0" selected>제목</option>
							<option value="1">작성자</option>
						</select> <input name="search_word" type="text" class="form-control"
							placeholder="검색어를 입력하세요" value="${search_word}">
						<button class="btn btn-dark" type="submit" name="searchbutton">검색</button>
					</div>
				</form>

				<div class="center-block">
					<ul class="pagination justify-content-center">
						<c:if test="${page <= 1}">
							<li class="page-item"><a class="page-link gray">이전&nbsp;</a>
							</li>
						</c:if>
						<c:if test="${page > 1}">
							<li class="page-item"><a
								href="AdminboardList.net?page=${page - 1}" class="page-link">이전&nbsp;</a>
							</li>
						</c:if>

						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page}">
								<li class="page-item active"><a class="page-link">${a}</a></li>
							</c:if>
							<c:if test="${a != page}">
								<li class="page-item"><a
									href="AdminboardList.net?page=${a}" class="page-link">${a}</a></li>
							</c:if>
						</c:forEach>

						<c:if test="${page >= maxpage}">
							<li class="page-item"><a class="page-link gray">&nbsp;다음</a>
							</li>
						</c:if>
						<c:if test="${page < maxpage}">
							<li class="page-item"><a
								href="AdminboardList.net?page=${page + 1}" class="page-link">&nbsp;다음</a>
							</li>
						</c:if>
					</ul>
				</div>
			</c:if>
		</div>
	</div>
</body>