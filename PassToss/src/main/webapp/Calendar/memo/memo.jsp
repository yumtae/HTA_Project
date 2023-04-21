<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<%-- <script src="js/view.js"></script>   --%>
<%-- <link rel="stylesheet" href="css/view.css" type="text/css">  --%>

</head>
<body>
	<div class="container">
		<%-- 게시글이 있는 경우--%>
		<c:if test="${listcount > 0 }">

			<table class="table table-striped">
				<thead>

					<tr>
						<th><div>번호</div></th>
						<th><div>제목</div></th>
						<th><div>작성자</div></th>
						<th><div>날짜</div></th>

					</tr>
				</thead>

				<!-- ajax로 바디 부분 바꾸기 -->
	<!-- ajax로 바디 부분 바꾸기 -->
	<!-- ajax로 바디 부분 바꾸기 -->
				<tbody>
					<c:set var="num" value="${listcount-(page-1)*limit }"/>
					<c:forEach var="b" items="${memolist}">
						<tr>
						<td><%--번호 --%>
				<c:out value="${memo_num}"/> <%--num 출력 --%>
				<c:set var="num" value="${num-1}"/> <%-- num=num-1; 의미 --%>
				</td>
							<td><div>${b.memo_num}</div></td>
							<td><div>${b.memo}</div></td>
							<td><div>${b.id}</div></td>
							<td><div>${b.reg_date}</div></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="center-block">
				<ul class="pagination justify-content-center">
					<%-- 현재 페이지가 1이면 이전으로 이동할 페이지가 없으므로 페이지색 gray --%>
					<c:if test="${page <= 1 }">
						<li class="page-item"><a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>
					<c:if test="${page > 1 }">
						<li class="page-item"><a href="MemoList.me?page=${page-1}"
							class="page-link gray">이전&nbsp;</a></li>
					</c:if>

					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a==page }">
							<li class="page-item active"><a class="page-link">${a}</a>
							</li>
						</c:if>
						<c:if test="${a != page }">
							<li class="page-item"><a href="MemoList.me?page=${a}"
								class="page-link">${a}</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${page >= maxpage }">
						<li class="page-item"><a class="page-link gray">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage }">
						<li class="page-item"><a href="MemoList.me?page=${page+1}"
							class="page-link">&nbsp;다음</a></li>
					</c:if>
				</ul>
			</div>




		</c:if>
		<%--게시글이 없는 경우 --%>
		<c:if test="${listcount==0}">
			<h3 style="text-align: center">등록된 글이 없습니다.</h3>



		</c:if>

		<div class="comment-area">
			<div class="comment-head">
				<h3 class="comment-count">
					메모 <sup id="count"></sup>
					<%--superscript(윗첨자) --%>
				</h3>
				<div class="comment-order">
					<ul class="comment-order-list">
					</ul>
				</div>
			</div>
			<%-- comment-head end--%>
			<ul class="comment-list">
			</ul>
			<div class="comment-write">
				<div class="comment-write-area">
					<b class="comment-write-area-name">${id}</b> <span
						class="comment-write-area-count">0/200</span>
					<textarea placeholder="메모를 남겨보세요" rows="1"
						class="comment-write-area-text" maxLength="200"></textarea>

				</div>
				<div class="register-box">
					<div class="button btn-cancel">취소</div>
					<%-- 댓글의 취소는 display:none, 등록만 보이도록 합니다.--%>
					<div class="button btn-register">등록</div>
				</div>
			</div>
			<%--comment-write end--%>
		</div>
		<%-- comment-area end--%>
	</div>
	<%-- class="container" end --%>


</body>
</html>