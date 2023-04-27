<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<jsp:include page="../include/head.jsp" />

<style>
	.join-wrap {
	width: 700px;
	height:700px;
	margin: 3rem auto;
	background: white;
	border-radius:20px;
}

.join-title {	
	padding: 20px 0 16px;
    font-size: 36px;
    color: #333;
    font-weight: 700;
    text-align: center;
}
.join-subtitle{
	margin: 0 auto;
    padding: 0 20px;
    font-weight: 500;
    font-size: 18px;
    color: #333;
    text-align: center;
    line-height: 27px;
}
.join-form-wrap{
	width: 586px;
    margin: 80px auto 0 auto;
    text-align: center;
}
label{
	margin-top:30px;
	display:block;
	text-align:left;
}
.input-name{
    margin-bottom: 10px;
    padding: 3px 0;
    font-size: 16px;
    color: #333;
    font-weight: 700;
    text-align: left;
}
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
select{
	width: 560px;
    height: 52px;
    padding-left: 20px;
    font-size: 16px;
    background: #fff;
    border: 1px solid #ddd;
    color: #333;
    box-sizing: border-box;
    border-radius: 8px;
}
.prev, .next{
	margin-top: 2rem;
	height: 50px;
	float:left;
	width:47%;
	font-weight: 700;
}
.prev{
	border: 1px solid #4e73df;
	color: #4e73df;
}
.prev:hover{
	color:white;
	background: #4e73df;
}
.next{margin-left: 1rem}
</style>
<body class="bg-gradient-primary">
	<div class="container">

		<div class="join-wrap">
			<div class="join-title">새 회사 만들기</div>
			<div class="join-subtitle">회사 정보를 입력해 주세요</div>
			<div class="join-form-wrap">
				<form>
					<label>
						<div class="input-name">회사이름</div>
						<input type="text" class="join-input" placeholder="회사 이름">
						<p class="error-message"></p>
					</label>
					<label>
						<div class="input-name">회사 URL</div>
						<span>https://</span>
						<input type="text" class="join-input url" placeholder="회사 URL">
						<span>.passtoss.team</span>
						<p class="error-message"></p>
					</label>
					<label>
						<div class="input-name">회사 업종</div>
						<select>
							<option value="default">회사 업종을 선택하세요</option>
							<option value="IT">정보통신업(IT)</option>
							<option value="Franchise">F&B, 프랜차이즈</option>
							<option value="Logistics">도·소매 유통 판매</option>
							<option value="Entertain">엔터테인먼트, 여행, 예술</option>
							<option value="Public">공공 행정</option>
							<option value="Construct">건설 및 기간 산업</option>
							<option value="Tax">세무, 법무, 노무</option>
							<option value="Transportation">운수 및 물류</option>
							<option value="Medical">의료 보건, 사회 복지</option>
							<option value="Finance">금융, 보험, 부동산</option>
							<option value="Association">협회 및 단체</option>
							<option value="Research">교육 및 연구</option>
							<option value="PrimaryIndustry">1차 산업(농·임·수산업)</option>
							<option value="etc">기타</option>
						</select>
						<p class="error-message"></p>
					</label>
					
					<button type="button" class="btn btn-light prev" onClick="history.back()">이전</button>
					<button type="button" class="btn btn-primary next">다음</button>
									
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>