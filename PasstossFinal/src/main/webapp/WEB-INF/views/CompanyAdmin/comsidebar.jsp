<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>




<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordionAdmin" id="accordionSidebarAdmin">

	<!-- Sidebar - Brand -->
	<a class="sidebar-brand d-flex align-items-center justify-content-center" href="#">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-solid fa-p"></i>
		</div>
		<div class="sidebar-brand-text mx-3">PassToss</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Dashboard 
	<li class="nav-item">
		<a class="nav-link" href="${pageContext.request.contextPath}/member/login">
			<i class="fas fa-fw fa-regular fa-house"></i>
			<span>로그인 페이지로 이동</span>
		</a>
	</li>
	<li class="newProject" class="nav-item">
		<a href="newProject" class="btn newPro-btn">
			<img class="newPro-icon" src="../resources/image/newProject.png">새 프로젝트
		</a>
	</li>
-->
	<!-- Divider 
	<hr class="sidebar-divider">
-->
	
	
	<!-- Nav Item - Pages Collapse Menu 
	<li class="nav-item active">
		<a class="nav-link" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
			<span>모아보기</span>
		</a>-->
		
		<!-- 
		<div id="AdmincollapsePages" class="Admincollapse show" aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Login Screens:</h6>
				<a class="collapse-item">
					<i class="fa-solid fa-chart-simple" href="${pageContext.request.contextPath}/CompanyAdmin/adminView"></i>&nbsp;회사 정보
				</a>
				<a class="collapse-item" href="${pageContext.request.contextPath}/CompanyAdmin/comMember">
					<i class="fas fa-fw fa-folder"></i>&nbsp;구성원 관리
				</a>
			</div>
		</div>
	</li>
 -->
	<br>

	<!-- Nav Item - Tables -->
	<li class="nav-item">
		<a class="nav-link" href="${pageContext.request.contextPath}/admin/main">
			<i class="fas fa-fw fa-duotone fa-user-gear"></i>
			<span>회사 정보 관리</span>
		</a>
	</li>
	
	
	<!-- Nav Item - Charts -->
	<li class="nav-item">
		<a class="nav-link" href="${pageContext.request.contextPath}/admin/comMember">
			<i class="fas fa-fw fa-regular fa-user-plus"></i>
			<span>구성원 관리</span>
		</a>
	</li>

<br>
	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">



</ul>
<!-- End of Sidebar -->