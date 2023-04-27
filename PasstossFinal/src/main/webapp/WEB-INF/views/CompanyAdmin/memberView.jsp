<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<jsp:include page="comhead.jsp" />

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../resources/css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="../resources/css/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<!-- <input type="hidden" id="mem" name="mem" value="{m.username}"> -->

<style>
.bo_w_select {
    width: 100%; /* 가로 사이즈 */
    padding: 10px; /* 내부여백 */
    padding-left: 12px;
    border: 1px solid #ddd;
    background: url("../image/arrow_down_18dp.png") no-repeat right 50%; /* 화살표 위치 */
    background-size: 30px; /* 화살표 크기 */
    border-radius: 4px;
    box-sizing: border-box;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    font-size: 12px;
    color: #000;
    outline:none;
}
.bo_w_select:hover {border: 1px solid #aaa;} /* 마우스오버 */
</style>

<script>
	$(function() {
		$("tr> td:nth-child(3) > a").click(function(event) {
			const answer = confirm("정말 삭제하시겠습니까?");
			console.log(answer); //취소를 선택한 경우 false
			if (!answer) { //취소를 클릭한경우
				event.preventDefault(); //이동하지 않습니다.
			}
		}) //삭제 클릭 end 
	}) //ready end
</script>
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="comsidebar.jsp" />
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<jsp:include page="comtopbar.jsp" />
				<!-- End of Topbar -->


				<!-- Begin Page Content -->
				<div class="container-fluid">


					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Member List</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<c:if test="${listcount > 0}">


										<thead>
											<tr>

												<th>아이디</th>
												<th>이름</th>
												<th>회사명</th>


												<th>가입 상태</th>
											
												<th>삭제</th>

											</tr>
										</thead>

										<tbody>
											<c:forEach var="m" items="${memberlist }">

												<tr>
													<!-- <td><a href="${pageContext.request.contextPath}info?id=${m.id}">${m.id}</a></td> -->
													<td><a href="#" data-toggle="modal"
														data-target="#infoUpdate">${m.id}</a>
														<input type="hidden" name="id" value="${m.id}">
													<td>${m.username}</td>
													<td><c:choose>
															<c:when test="${m.company_id== 1}">파스토스</c:when>
															<c:when test="${m.company_id== 2}">zconi</c:when>
														</c:choose> <input type="hidden" value="${m.company_id}"></td>
										
											<td>
											<c:choose>
											<c:when test="${m.belong== 0}">
										<a href="${pageContext.request.contextPath}/admin/update?id=${m.id}">
															<button class="btn btn-success" >가입 승인</button></a>
															</c:when>
															<c:when test="${m.belong == 1}">
																<button class="btn btn-light" >승인완료</button>
																
																
																</c:when>
					</c:choose>
													</td>
											<!-- 
														
	<td><a
														href="${pageContext.request.contextPath}/admin/delete?id=${m.id}">
															<button class="btn btn-danger"
																onclick="javascript:btn();">삭제</button>
													</a></td>
<div class="notice">
					<c:choose>
						<c:when test="${belong == 1}">
							<img src="../resources/image/welcome.png">
							<p class="title">패스토스 가입을 축하합니다.</p>
							<p class="sub-title">쉽고 빠른 협업, 패스토스와 함께하세요.</p>
						</c:when>
						<c:when test="${belong == 0}">
							<img src="../resources/image/승인대기.jpg">
							<p class="title">관리자의 가입승인을 기다리고 있어요</p>
							<p class="sub-title">관리자가 승인 완료 시 이용 가능합니다.</p>
						</c:when>
					</c:choose>



													<!-- <td><a href="delete?id=${m.id}">삭제</a></td> -->
													<td><a
														href="${pageContext.request.contextPath}/admin/delete?id=${m.id}">
															<button class="btn btn-danger"
																onclick="javascript:btn();">삭제</button>
													</a></td>
													<!-- 	<td><button type="button" class="btn btn-danger" href="#" data-toggle="modal" data-target="#infoUpdate">삭제</button> -->
												</tr>








											</c:forEach>
										</tbody>
								</table>



							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

				</c:if>
				<jsp:include page="../include/footer.jsp" />
			</div>
</body>
</html>
<script>



	function btn() {
		if (!confirm('정말 삭제하시겠습니까?')) {
			return;
		}
	}
	
	
	$("#upbe").on("click", function(event) {
		
		
		
		var settings = {
				  "url": "http://localhost:9600/passtoss/admin/update",
				  "method": "POST",
				 
				  "headers": {
				    "Content-Type": "application/x-www-form-urlencoded"
				   
				  },
				  "data": {
				    "id": "choi_ssori@naver.com"
				    "belong": "1"
				  }
				};

				$.ajax(settings).done(function (response) {
					alert("승인완료");
					location.reload();
				  console.log(response);
				});
	}
	);
	
</script>


<!-- 
<script>
	$(function(){
			$(window).on('load',function(){
				$("#admin_table_wrap").mCustomScrollbar({
					axis:"x",
					advanced:{
						autoExpandHorizontalScroll:true
					},
				});
				
				
			})
		
	})
</script>

 -->
<!-- <script src="../resources/js/bootstrap/bootstrap.bundle.js"></script> -->


<!-- Bootstrap core JavaScript
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>-->

<!-- Core plugin JavaScript-->
<script src="../resources/js/bootstrap/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages
    <script src="js/sb-admin-2.min.js"></script>-->

<!-- Page level plugins -->
<script src="../resources/js/bootstrap/jquery.dataTables.min.js"></script>
<script src="../resources/js/bootstrap/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="../resources/js/bootstrap/datatables-demo.js"></script>