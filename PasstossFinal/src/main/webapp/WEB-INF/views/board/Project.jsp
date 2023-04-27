<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../include/head.jsp" />
<style>
.container-fluid ul {
	margin: 1rem;
}

.container-fluid ul li {
	min-width: 200px;
	max-width: 250px;
	list-style: none;
	float: left;
	margin: 20px;
}

.card {
	width: 250px;
	height: 150px;
}

.userImg {
	position: absolute;
	left: 15px;
	bottom: 10px;
}

.cursor-pointer {
	cursor: pointer;
}

.card-body:hover div {
	color: #4e73df !important;
}

.container-fluid ul li a {
	text-decoration: none;
}
</style>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="../include/sidebar.jsp" />
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<jsp:include page="../include/topbar.jsp" />
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-4 text-gray-800">내 프로젝트</h1>
					<ul>
						<c:if test="${!empty project}">
							<c:forEach var="p" items="${project}">
								<li>
									<a class="cursor-pointer">
										<div class="card border-left-primary shadow py-2">
											<div class="card-body">
												<div class="row no-gutters align-items-center">
													<div class="col mr-2">
														<div class="h5 font-weight-bold text-gray-800 mb-1">${b.project_name}</div>
													</div>
												</div>
											</div>
											<div class="userImg text-xs mb-0 font-weight-bold text-gray-800">
												<img src="../resources/image/user.png" style="width: 20px">${b.count}
											</div>
										</div>
									</a>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>

</body>

<!-- Footer -->
<jsp:include page="../include/footer.jsp" />


