<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../include/head.jsp" />
<link href="../resources/css/filebox.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/584b0a0b8f.js" crossorigin="anonymous"></script>

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
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">
							<b>"PassToss"</b> 업무 공유방
						</h1>
						<a href="#"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
							class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
					</div>
					
				   <div  id="fileborder1">
				     <div>
					   <div class="input-group" id="filesearch">
							<div class="input-group-append">
								<button class="btn btn-light" type="button" 
										style="border-right: none; background-color:white;">
									<i class="fas fa-search fa-sm"></i>
								</button>
							</div>
							<input type="text" class="form-control bg-light small"
								placeholder="파일명을 입력해주세요" aria-label="Search"
								aria-describedby="basic-addon2" 
								style="border-left: none; background-color:white !important;">
					   </div>
					   <div id="listicon">
						  <a href=""><i class="fa-solid fa-border-all" style="color: #a2a5a9;"></i></a>
						  <a href=""><i class="fa-solid fa-bars" style="color: #a2a5a9; margin-left:5px"></i></a>
					   </div>
					 </div>
					   
 				   </div> <!-- container -->
				  
				   <div id="fileborder2">
					<ul>
					  <li>
						<div class="filename"><span>파일명</span></div>
						<div class="username"><span>작성자</span></div>
						<div class="date"><span>작성일</span></div>
					  </li>
					</ul>
				   </div>
				   <div  id="fileborder3">
				   	<ul>
					  <li>
						<div class="filename"><span>파일명</span></div>
						<div class="username"><span>작성자</span></div>
						<div class="date"><span>작성일</span></div>
					  </li>
					</ul>
				   </div>
				  


				</div>
				<!-- /container-fluid -->



			</div>
			<!-- /content -->

			<jsp:include page="../include/footer.jsp" />

		</div>
		<!-- End of Content Wrapper -->



	</div>
	<!-- End of Page Wrapper -->






</body>