<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/board/main">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-solid fa-p"></i>
		</div>
		<div class="sidebar-brand-text mx-3">PassToss</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item active">
		<a class="nav-link" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
			<span>모아보기</span>
		</a>
		<div id="collapsePages" class="collapse show" aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Login Screens:</h6>
				<a class="collapse-item" href="${pageContext.request.contextPath}/board/subwork">
					<i class="fa-solid fa-chart-simple"></i>&nbsp;업무현황
				</a>
				<a class="collapse-item" href="${pageContext.request.contextPath}/cal/main">
					<i class="fa-regular fa-calendar"></i>&nbsp;캘린더
				</a>
				<a class="collapse-item" href="${pageContext.request.contextPath}/file/filebox">
					<i class="fas fa-fw fa-folder"></i>&nbsp;파일함
				</a>
			</div>
		</div>
	</li>

	<!-- Nav Item - Charts -->
	<li class="nav-item">
		<a class="nav-link" href="charts.html">
			<i class="fas fa-fw fa-regular fa-user-plus"></i>
			<span>직원 초대</span>
		</a>
	</li>

	<!-- Nav Item - Tables -->
	<li class="nav-item">
		<a id="settings" class="nav-link" href="#" onClick="window.open('../manager/companyInfo')">
			<i class="fas fa-fw fa-duotone fa-user-gear"></i>
			<span>관리자</span>
		</a>
	</li>

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>
<!-- End of Sidebar -->