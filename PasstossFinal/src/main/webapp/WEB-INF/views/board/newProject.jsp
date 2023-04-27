<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<jsp:include page="../include/head1.jsp"></jsp:include>
<jsp:include page="../include/js.jsp"></jsp:include>
<script src="../resources/js/board/newProject.js"></script>
<link rel="stylesheet" href="../resources/css/newProject.css">

<body class="bg-gradient-primary">
	<form id="createProject" action="createProject" method="post">
		<sec:authentication property="principal" var="pinfo" />
		<input type="hidden" name="company_id" value="${pinfo.companyId}">
		<div class="main-wrap">
			<div class="title">프로젝트 생성</div>
			<div class="input-wrap">
				<label>
					<div class="input-name">프로젝트 명</div>
					<input type="text" class="input" name="project_name" placeholder="제목">
					<p class="message"></p>
				</label>
				<label>
					<div class="input-name">프로젝트 설명</div>
					<textarea type="text" class="input input-textarea" name="description" placeholder="프로젝트에 관한 설명(옵션)"></textarea>
				</label>
				<label>
					<div class="input-name">게시글 작성 권한 설정</div>
					<select name="write_auth" class="input">
						<option value="0">전체</option>
						<option value="1">관리자만</option>						
					</select>
				</label>
				<label>
					<div class="input-name">댓글 작성 권한 설정</div>
					<select name="comment_auth" class="input">
						<option value="0">전체</option>
						<option value="1">관리자만</option>						
					</select>
				</label>
				<label>
					<div class="input-name">파일 조회/다운로드 권한 설정</div>
					<select name="file_auth" class="input">
						<option value="0">전체</option>
						<option value="1">관리자만</option>						
					</select>
				</label>

				<button id="create" type="submit" class="btn btn-primary">프로젝트 생성</button>

			</div>
		</div>
	</form>
</body>
</html>