<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<jsp:include page="../include/manager.jsp" />
<style>
.menu_tabs {
	background: #fff;
	padding-left: 0;
	border: 1px solid #e3e6f0;
	border-bottom: 0;
	border-radius: 0.35rem 0.35rem 0 0;
	margin: 0;
	min-width: 900px;
	display: flex;
}

a:hover {
	color: black;
}

.tab {
	width: 140px;
	list-style: none;
	text-align: center;
}

.tab:hover { .link { font-weight:700;
	
}

cursor
:pointer
;
	
	
transition
:
 
all
 
ease
 
0
.2s
;

	
box-shadow
:
 
inset
 
0
 
-4px
 
0
 
0
 
#4e73df
;


}
.active {
	font-weight: 700;
	box-shadow: inset 0 -4px 0 0 #4e73df;
}

.link {
	display: block;
	padding: 0.75rem 30px 0.75rem 30px;
	width: 140px;
	color: black;
	font-size: 15px;
}

.link:hover {
	text-decoration: none;
}

.card {
	min-width: 900px;
	border-radius: 0 0 0.35rem 0.35rem;
}

.search-wrap {
	display: flex;
}

.search-group {
	font-size: 13px;
	font-weight: 700;
	height: 35px;
	outline: none;
	border: 1px solid #e3e6f0;
}

.search {
	border-left: none;
	border-right: none;
	width: 200px;
	font-weight: 500;
}

.search-options {
	width: 100px;
}

.search-btn {
	background: white;
}
</style>
<body id="page-top">
	<div id="wrapper">
		<jsp:include page="sidebar.jsp" />
		<div id="content-wrapper">
			<div id="content">
				<div class="container-fluid">
					<div id="member-info">
						<input type="hidden" name="company_id" value="${pinfo.companyId}">
						<div class="title mt-4 mb-4">구성원</div>
						<hr>
						<ul class="menu_tabs">
							<li class="tab">
								<a class="link active" href="memberInfo?">정상(3)</a>
							</li>
							<li class="tab">
								<a class="link" href="#item-2">가입대기(5)</a>
							</li>
						</ul>
						<div class="card shadow mb-4">
							<div class="card-body">
								<div class="header">
									<form action="memberInfo" class="search-wrap">
										<select name="" class="search-options search-group">
											<option value="name">이름</option>
											<option value="dept">부서</option>
											<option value="position">직책</option>
										</select>
										<input type="text" class="search search-group" placeholder="검색어를 입력해주세요">
										<input type="submit" class="search-btn search-group" value="검색">
									</form>
									
									<select>
										<option value="10">10</option>
										<option value="20">20</option>
									</select>
								</div>
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable">
										<thead>
											<tr>
												<th>이름</th>
												<th>부서</th>
												<th>직책</th>
												<th>이메일</th>
												<th>연락처</th>
												<th>가입일</th>
												<th>상태</th>
												<th>관리자</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
											<tr>
												<td>염재영</td>
												<td></td>
												<td></td>
												<td>duawodud66@naver.com</td>
												<td>010-6233-0272</td>
												<td>2011/04/25</td>
												<td>정상</td>
												<td>Y</td>
											</tr>
										</tbody>
									</table>
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