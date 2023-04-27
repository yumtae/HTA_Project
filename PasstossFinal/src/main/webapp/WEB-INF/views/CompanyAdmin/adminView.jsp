<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
							<h6 class="m-0 font-weight-bold text-primary">Company List</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<c:if test="${listcount > 0}">




										<thead>
											<tr>
												<th>회사명</th>
												<th>담당자</th>
												<th>URL</th>
												<th>업종</th>
												<th>가입일</th>
												<th>상태</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach var="p" items="${companylist }">
												<tr>
													<td><a href="info?id=${p.company_name}">${p.company_name}</a></td>

													<td>${p.ceo_id}</td>
													<td>${p.url}</td>
													<td>${p.type}</td>
													<td>${p.create_date}</td>
													<td><button type="button" class="btn btn-danger"
															onclick="javascript:btn()"
															; href="deleteCom?id=${p.company_id}" id="deleteCompany">삭제</button></td>


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
		alert('삭제하시겠습니까?');
	}
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