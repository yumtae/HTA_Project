<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<!-- BoardModal3 calendarModal -->

<div class='modal BoardModal' id='calendarModal'>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<form name="headcalForm" onsubmit="return false">
					<input hidden="hidden" /> 
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 
					
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal" var="pinfo" />

						<input type="hidden" id="WRITER" name="WRITER" value="${pinfo.username}">
						<input type="hidden" name="COMPANY_ID" value="${pinfo.companyId}"  >
						
					</sec:authorize>
					
			
					
					<input type="hidden" name="BOARD_DIVISION" value="3">



					<div class='board_content_box cal'>
						<dl class="post-author-info">
							<b>일정 등록</b>

							<dd class="board_title_text_wrap add">
								<input type="text" class='board_input_common_style head_subject'
									name="calendar_title_add" id="calendar_title_add"
									placeholder="제목을 입력하세요" value>
							</dd>
						</dl>
						<!-- /post-author-info -->


						   
	                                <div class="board_projectmanagers_box board_projectmanagers_box">
	                                	<i class="fa fa-people-arrows"></i>
	                                	
	                                	<input type="text" class='board_add_projectmanager'
	                                	placeholder="담당자 추가" style="display: inline-block;">
	                                	
	                                	
	                                	<div class='board_projectmanagers_people'>
	                                		<img src="${pageContext.request.contextPath}/image/profile-default.png">
	                                		<b>홍길동</b>
	                                		<a href='#'><i class="fa fa-window-close"></i></a>
	                                	</div>
	                                
	                                	
	                                
	                                </div><!-- board_projectmanagers_box -->


						<div class='board_attr_more_add'>
							<div>
								<i class="fa fa-calendar"></i> <span>시작일 추가</span> <input
									type="datetime-local" class="form-control"
									id="calendar_start_date_add" name="calendar_start_date_add">
							</div>

							<div>
								<i class="fa fa-calendar"></i> <span>종료일 추가</span> <input
									type="datetime-local" class="form-control"
									id="calendar_end_date_add" name="calendar_end_date_add">
							</div>





						</div>
						<!-- /board_attr_more_add -->

						<hr />
						<div class='board_content'>
							<textarea class="board_input_common_style head_content"
								id="calendar_content_add" name="calendar_content_add"
								placeholder="내용을 입력하세요"></textarea>
						</div>






						<div class="more_business_list head_more_business_list">
							<div class='board_ststus_li_form_list'>
								<!--
		                                		이런식으로 글 추가
		                                		<div  class='board_status_li rel'>
		                                		
		                                		<span class='com_status_span_style board-primary on'>요청</span> 하위요청 추가내용 
		                                		<i class="fa fa-window-close business_list_remove_btn right" aria-hidden="true"></i>
		                                	</div> -->

							</div>





						</div>
						<!-- /more_business_list -->




					</div>
					<!-- /board_content_box -->




					<button type="submit" class="btn btn-primary addCalbtn" id="addCalendar">추가</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}">
				</form>
			</div>
		</div>
	</div>
</div>
<%--BoardModal --%>

<script>
	$("#addCalendar").on("click", function() { // modal의 추가 버튼 클릭 시
		var title = $("#calendar_title_add").val();
		var content = $("#calendar_content_add").val();
		var start_date = $("#calendar_start_date_add").val();
		start_date = moment(start_date).format('YYYY-MM-DD HH:mm');
		var end_date = $("#calendar_end_date_add").val();
		end_date = moment(end_date).format('YYYY-MM-DD HH:mm');
		/* var user=$("event._def.extendedProps.user").val(); */
		var user= $("#WRITER").val();
		//var num=$("event._def.extendedProps.num");
		//내용 입력 여부 확인
		if (content == null || content == "") {
			alert("내용을 입력하세요.");
		} else if (start_date == "" || end_date == "") {
			alert("날짜를 입력하세요.");
		} else if (new Date(end_date) - new Date(start_date) < 0) { // date 타입으로 변경 후 확인
			alert("종료일이 시작일보다 먼저입니다.");
		} else { // 정상적인 입력 시

			var insert = {
				"url" : "http://localhost:9600/passtoss/cal/calAdd",
				"method" : "POST",
				"timeout" : 0,
				"headers" : {
					"Content-Type" : "application/x-www-form-urlencoded"
				},
				"data" : {
					// "BOARD_NUM": num, 
					"COMPANY_ID" : "1",
					"BOARD_DIVISION" : "3",
					"WRITER" : user,
					"SUBJECT" : title,
					"CONTENT" : content,
					"START_DATE" : start_date,
					"END_DATE" : end_date
				}
			};
			$.ajax(insert).done(function(response) {
				console.log("get=" + response);
				location.reload();
				getlist();
				calendar.render();

				$("#calendarModal").modal("hide"); // modal 나타내기
			});

		}
	});
</script>
