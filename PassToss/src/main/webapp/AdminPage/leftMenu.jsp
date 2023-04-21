<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/leftMenu.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<c:if test="${empty id }">
	<script>
		location.href="LoginAction.net";
	</script>
</c:if>
<script>
	$(function() {
		$(".dropdown-btn").each(function() {
			$(this).click(function() {
				$(this).next(".dropdown-container").slideToggle();
			})
		})
	})
</script>

<div class="sidenav">
	<b style='margin: 10px auto 50px;display: block;text-align: center;font-size:2em;color:#fff'>PassToss</b>
	<c:if test="${id=='admin' }">
		<button class="dropdown-btn">
			관리자페이지 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a href="AdminMemberList.net">회원관리</a> 
			<a href="AdminboardList.net">게시물관리</a>
		</div>
	</c:if>	
	<a href="FreeList.bof">사내게시판</a>
	<button class="dropdown-btn">
		부서게시판 <i class="fa fa-caret-down"></i>
	</button>
	<div class="dropdown-container">
		<a href="DeptList.bod">게시물</a> 
		<a href="calendar.cal">캘린더</a>
	</div>
	<button class="dropdown-btn">
		마이페이지 <i class="fa fa-caret-down"></i>
	</button>
	<div class="dropdown-container">
		<a href="memberinfo.net">정보수정</a> 
		<a href="memberBoardList.net">게시물관리</a>
	</div>
	<a href="Business_status.bs">업무현황</a>
</div>