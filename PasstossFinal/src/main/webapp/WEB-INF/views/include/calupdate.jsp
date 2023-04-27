<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

      	<!-- calUpdateModal -->
	<div class='modal BoardModal' id='calUpdateModal' >
	      		<div class="modal-dialog">
		      		<div class="modal-content">
		      			<div class="modal-body">
		      				<form  name="calUpdateForm" onsubmit="return false" >
		      				   	<input type="hidden" id="BOARD_NUM" name="BOARD_NUM" value="${boarddata.BOARD_NUM}">
		      					<input hidden="hidden" />
		      					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		      					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal" var="pinfo" />

						<input type="hidden" id="WRITER" name="WRITER" value="${pinfo.username}">
					</sec:authorize>
		      					<input type="hidden" name="COMPANY_ID" value="1"  >
		      					
		      					
		      					<input type="hidden" name="BOARD_DIVISION"  value="3"  >
		      					
		      					
		      					
		      					
		      					
		      					
             	
             			
             			
	      					
	      					
	      						<div class='board_content_box'>
	                        		<dl class="post-author-info">
	                                   <b>일정 수정/삭제</b>
	                                   	
	                                    <dd class="board_title_text_wrap">
	                                       <input type="text" class='board_input_common_style head_subject' name="calendar_title" id="calendar_title" placeholder="제목을 입력하세요" value=${b.SUBJECT}>
	                                    </dd>
	                                </dl><!-- /post-author-info -->
	                                
	                            	                                
	                                <div class="board_projectmanagers_box board_projectmanagers_box">
	                                	<i class="fa fa-people-arrows"></i>
	                                	
	                                	<input type="text" class='board_add_projectmanager'
	                                	placeholder="담당자 추가" style="display: inline-block;">
	                                	
	                                	
	                                	<div class='board_projectmanagers_people'>
	                                		<img src="${pageContext.request.contextPath}/image/profile-default.png">
	                                		<b>김현지</b>
	                                		<a href='#'><i class="fa fa-window-close"></i></a>
	                                	</div>
	                                
	                                	
	                                
	                                </div><!-- board_projectmanagers_box -->
	                                
	                                
	                                <div class='board_attr_more_add'>
	                                	<div>
	                                		<i class="fa fa-calendar start"></i>
	                                		<span>시작일 ${b.START_DATE}</span>
	                                		<input type="datetime-local"								class="form-control" id="calendar_start_date"
												name="calendar_start_date"> 
	                                	</div>
	                                	
	                                	<div>
	                                		<i class="fa fa-calendar end"></i>
	                                		<span>종료일 ${b.END_DATE}</span>
	                                		<input type="datetime-local"
												class="form-control" id="calendar_end_date"
												name="calendar_end_date"> 
	                                	</div>
	                                	
	                                		                                	
	              
	                                
	                                	
	                                </div><!-- /board_attr_more_add -->
	                                
	                                <hr/>
	                                <div class='board_content'> 
	                                	 <textarea class="board_input_common_style head_content" onkeydown="resize(this)" onkeyup="resize(this)"  id="calendar_content"
												name="calendar_content" placeholder="내용을 입력하세요">${b.CONTENT}</textarea>
	                                </div>
	                                
	                                
	                                
	                                
	                              
	                             
	                                <div  class="more_business_list head_more_business_list">
	                                 <div class='board_ststus_li_form_list'>
		                                		<!--
		                                		이런식으로 글 추가
		                                		<div  class='board_status_li rel'>
		                                		
		                                		<span class='com_status_span_style board-primary on'>요청</span> 하위요청 추가내용 
		                                		<i class="fa fa-window-close business_list_remove_btn right" aria-hidden="true"></i>
		                                	</div> -->
		                                	
	                                	</div>
	                                	
	                                	
	                                	
	                                	
	                                	
	                                </div><!-- /more_business_list -->
	                                
	                               
	                                
	                                
	                        	</div><!-- /board_content_box -->
	                      
	                        	
	      					
								
	      					   <button  type="submit" class="btn btn-primary calUpbtn"  id="updateCalendar">수정</button>
	      					   <button type="button" class="btn btn-success" data-dismiss="modal">취소</button>
	      					   <button type="button" class="btn btn-danger" data-dismiss="modal" id="deleteCalendar">삭제</button>
	      					   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	      				</form>
	      			</div>
	      		</div>
	      	</div>
      	</div><%--BoardModal --%>
      	
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
      	