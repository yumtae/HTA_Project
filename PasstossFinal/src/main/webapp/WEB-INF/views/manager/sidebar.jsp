<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="resultWrap" class="messageWrap">
	<p id="resultMessage" class="resultMsg"></p>
</div>
<ul class="navbar-nav bg-dark sidebar sidebar-dark accordion" id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/manager/companyInfo">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-solid fa-p"></i>
		</div>
		<div class="sidebar-brand-text mx-3">PassToss</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item">
		<div class="category">회사</div>
	</li>
	<li class="nav-item">
		<a id="company-info-setting" class="nav-link" href="${pageContext.request.contextPath}/manager/companyInfo"> 회사정보 </a>
	</li>
	<li class="nav-item">
		<a id="member-manage-setting" class="nav-link" href="${pageContext.request.contextPath}/manager/memberInfo"> 구성원 관리 </a>
	</li>
</ul>