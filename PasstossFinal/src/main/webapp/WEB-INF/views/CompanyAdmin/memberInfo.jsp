<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

      	<!-- calUpdateModal -->
<div class="modal fade" id="memInfo" tabindex="-1" role="dialog">
	<div id="resultMsg"><p>내 정보를 수정하였습니다.<p></div>
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="header .bg-gradient-light">
				<span id="profileSetting" class="profile-title">프로필 수정</span>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span class="close-btn" aria-hidden="true">×</span>
				</button>
				<img id="myImg" class="profile-img" src="../upload/${profileImg}">
				<label id="profileImgBtn" class="imageBtn">
					<input id="profileImg" type="file" name="profile_img" class="d-none" accept=".jpg, .jpeg, .png">
				</label>				
			</div>
			<div class="body">
				<div class="categories">
					<ul>
						<li class="category">
							<a id="myInfo" href="#">
								<i class="fa-solid fa-gear"></i>내 정보
							</a>
						</li>
						<li class="category">
							<a id="myPassword" href="#">
								<i class="fa-solid fa-lock"></i>비밀번호
							</a>
						</li>
						<li class="category">
							<a id="notice" href="#">
								<i class="fa-solid fa-bell"></i>알림
							</a>
						</li>
						<li class="category">
							<a id="language" href="#">
								<i class="fa-solid fa-language"></i>언어
							</a>
						</li>
					</ul>
				</div>
				<div id="myInfoSetting" class="modify-menus">
					<input type="hidden" name="updateType" value="member">
					<table class="table">
						<tr class="menu">
							<td class="infoName">아이디</td>
							<td class="info">
								<input type="text" name="id" value="${pinfo.username}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">이름</td>
							<td class="info">
								<input type="text" name="username" value="${pinfo.name}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">회사명</td>
							<td class="info">
								<input type="text" value="${pinfo.companyName}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">부서</td>
							<td class="info">
								<input type="text" name="dept" value="${pinfo.dept}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">직책</td>
							<td class="info">
								<input type="text" name="position" value="${pinfo.position}" disabled>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">휴대폰 번호</td>
							<td class="info">
								(+82)&nbsp;&nbsp;
								<input id="phoneNum" name="phone" type="text" 
										class="input" value="${pinfo.phone}" disabled>
								<img src="../resources/image/edit.png" class="d-none edit">
								<div id="validateMsg" class="message"></div>
							</td>
						</tr>
					</table>
					<div class="btn-wrap">
						<button id="myInfoCancel" class="btn btn-light d-none">취소</button>
						<button id="myInfoModify" class="btn btn-primary">수정</button>
						<button id="myInfoSubmit" class="btn btn-primary d-none">확인</button>
					</div>
				</div>
				<div id="myPasswordSetting" class="modify-menus d-none">
					<table class="table">
						<tr class="menu">
							<td class="infoName">새 비밀번호</td>
							<td class="info">
								<input type="password" name="password" class="input" disabled>
								<img src="../resources/image/edit.png" class="edit d-none">
								<div id="passwordMsg" class="message"></div>
							</td>
						</tr>
						<tr class="menu">
							<td class="infoName">새 비밀번호 확인</td>
							<td class="info">
								<input type="password" name="passwordRe" class="input" disabled>
								<img src="../resources/image/edit.png" class="edit d-none">
								<div id="passwordReMsg" class="message"></div>
							</td>
						</tr>
					</table>
					<div class="btn-wrap">
						<div id="message" class="message"></div>
						<button id="myPassCancel" class="btn btn-light d-none">취소</button>
						<button id="myPassModify" class="btn btn-primary">수정</button>
						<button id="myPassSubmit" class="btn btn-primary d-none">확인</button>
					</div>
				</div>
			</div>



		</div>
	</div>
</div>
      	
    <script>
    $("#updateCalendar").on("click", function(event) {
		var title = $("#calendar_title").val();
		var content = $("#calendar_content").val();
		var start_date = $("#calendar_start_date").val();
		start_date = moment(start_date).format('YYYY-MM-DD HH:mm');
		var end_date = $("#calendar_end_date").val();
		end_date = moment(end_date).format('YYYY-MM-DD HH:mm');
		/* var num=$("event._def.extendedProps.num").val(); */
		var num=$("#BOARD_NUM").val();
		//내용 입력 여부 확인
		if (content == null || content == "") {
			alert("내용을 입력하세요.");
		} else if (start_date == ""
				|| end_date == "") {
			alert("날짜를 입력하세요.");
		} else if (new Date(end_date)
				- new Date(start_date) < 0) { // date 타입으로 변경 후 확인
			alert("종료일이 시작일보다 먼저입니다.");
		} else { // 정상적인 입력 시
			
		
 // 확인 클릭 시
/*arg.event.remove();
console.log(arg.event); 
var events = new Array(); // Json 데이터를 받기 위한 배열 선언
var obj = new Object();
obj.num = arg.event._def.extendedProps.num;
/*obj.num = arg.event._def.defId;*/

//	console.log(obj.num)
// events.push(obj);
/* 					console.log(obj); */


var settings = {
	  "url": "http://localhost:9600/passtoss/cal/calUpdate",
	  "method": "POST",
	  "headers": {
	    "Content-Type": "application/x-www-form-urlencoded"
	  },
	  "data": {
		  "BOARD_NUM" : num,
		  "SUBJECT": title,
		    "CONTENT": content,
		    "START_DATE": start_date,
		    "END_DATE": end_date
	    
	  },
	};
	$.ajax(settings).done(function (response) {
		location.reload(); 
	console.log(response);
	  getlist();
	 // calendar.render();
	  $("#calUpdateModal").modal("hide"); // modal 나타내기
	});
								
}
});
    </script>  	
      	