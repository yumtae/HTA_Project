<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<jsp:include page="../include/head.jsp" />

<style>
.join-wrap {
	width: 700px;
	height: 500px;
	margin: 3rem auto;
	background: white;
	border-radius: 20px;
}

.join-title {
	padding: 20px 0 16px;
	font-size: 36px;
	color: #333;
	font-weight: 700;
	text-align: center;
}

.join-subtitle {
	margin: 0 auto;
	padding: 0 20px;
	font-weight: 500;
	font-size: 18px;
	color: #333;
	text-align: center;
	line-height: 27px;
}

.join-form-wrap {
	width: 586px;
	margin: 80px auto 0 auto;
	text-align: center;
}
.input-name{
    margin-bottom: 10px;
    padding: 3px 0;
    font-size: 16px;
    color: #333;
    font-weight: 700;
    text-align: left;
}
label span{color:#333;}
.join-input{
	color: #333;
    font-size: 16px;
    height: 50px;
    width: 560px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-sizing: border-box;
    padding-left: 20px;
}
.join-input:focus, select:focus{
	outline:none;
	color: #333;
    border: 1px solid #989898;
    box-shadow: 2px 2px 6px rgba(0,0,0,.15);
}
.url{width:400px;}
.helpMsg{
	float:left;
	font-size:15px;
	font-weight:600;
	color:#4e73df;
}
.join-button{
	width:560px;
	height: 50px;
	display:inline-block;
	float:left;
}
.modal-header, .modal-footer{
	border:none;
}
.modal-header{padding-bottom:0;}
.modal-title{
	color:black;
	font-weight: bold;
}
.modal-body{
	color:#555;
	font-weight: 500;
}
.input-wrap{
	margin:0 auto;
	width:100%;
	height: 100px;
	background:#4e73df1a;
	border-radius: 5px;
	text-align: center;
}
.urlInfo{vertical-align:center;}
.submit{
	width:50%;
	margin:0 auto;
}
</style>
</head>
<body class="bg-gradient-primary">
	<div class="container">

		<div class="join-wrap">
			<div class="join-title">기존 회사 참여</div>
			<div class="join-subtitle">이미 사용중인 회사라면 URL입력후 참여하세요</div>
			<div class="join-form-wrap">
				<form>
					<label>
						<div class="input-name">회사 URL</div>
						<span>https://</span>
						<input type="text" class="join-input url" placeholder="회사 URL">
						<span>.passtoss.team</span>
						<p class="error-message"></p>
					</label>
					
					<p class="helpMsg">회사 URL주소는 관리자를 통해 확인할 수 있습니다.</p>
					
					<button type="button" class="btn btn-primary join-button"
							data-bs-toggle="modal" data-bs-target="#companyInfo">참여하기</button>
				</form>
			</div>
		</div>
		
		<div class="modal" tabindex="-1" id="companyInfo">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">회사 정보 확인 후 시작하세요!</h5>
					</div>
					<div class="modal-body">
						<div class="input-wrap">
							<img src="">
							<p class="urlInfo">https://${url}.passtoss.team</p>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary submit">참여하기</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$(".submit").click(function(){
				location.href="${pageContext.request.contextPath}/member/authorization"
			})
		</script>
		
	</div>
	
	<jsp:include page="../include/js.jsp"></jsp:include>
</body>
</html>