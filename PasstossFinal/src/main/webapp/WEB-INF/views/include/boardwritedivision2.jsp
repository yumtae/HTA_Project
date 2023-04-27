<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<!-- BoardModal1 -->
	<div class='modal BoardModal' id='BoardModal2' >
	      		<div class="modal-dialog">
		      		<div class="modal-content">
		      			<div class="modal-body">
		      				<form  name="headBoardForm"  class='headBoardForm'   onsubmit="return false">
		      					<input hidden="hidden" />
		      					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		      					<sec:authorize access="isAuthenticated()">
									<sec:authentication property="principal" var="pinfo" />
								
		      						<input type="hidden" name="WRITER" value="${pinfo.username}"  >
		      						
		      						<input type="hidden" name="COMPANY_ID" value="${pinfo.companyId}"  >
		      					</sec:authorize>
		      					
		      					
		      					
		      					
		      					
		      					<input type="hidden" name="BOARD_DIVISION"  value="2"  >
		      					
	      					
	      						<div class='board_content_box'>
	                        		<dl class="post-author-info">
	                                   <b>게시물 작성</b>
	                                   	
	                                    <dd class="board_title_text_wrap">
	                                       <input type="text" class='board_input_common_style head_subject' name="SUBJECT" placeholder="제목을 입력하세요" value="">
	                                    </dd>
	                                </dl><!-- /post-author-info -->
	                                
	                               <div class="board_status_box">
	                                	<i class="fa fa-clock"></i>
	                                	<div  class='head_board_status_li board_status_li'>
	                                		<span class="com_status_span_style board-primary on" >요청</span>
	                                		<span class="com_status_span_style board-Success " >진행</span>
	                                		<span class="com_status_span_style board-Info " >완료</span>
	                                		<span class="com_status_span_style board-warning " >보류</span>
	                                	</div>
	                                </div>
	                                
	                                
	                                <div class="board_projectmanagers_box rel">
	                                	<i class="fa fa-people-arrows"></i>
	                                	
	                                	<input type="text" class='board_add_projectmanager'
	                                	placeholder="담당자 추가" style="display: inline-block;">
	                                	<div class="projectmanager-search-results abo">
										  <!-- 검색 결과가 나타날 영역 -->
										</div>
	                                	
	                                	
	                                	<div class='board_projectmanagers_people_wrap inline'>
	                                		  <!-- 검색 결과를 출력할 -->
		                               
	                                	</div>
	                                	
	                                
	                                </div><!-- board_projectmanagers_box -->
	                                
	                                
	                                <div class='board_attr_more_add'>
	                                	<div>
	                                		<i class="fa fa-calendar"></i>
	                                		<span>시작일 추가</span>
	                                	</div>
	                                	
	                                	<div>
	                                		<i class="fa fa-calendar"></i>
	                                		<span>종료일 추가</span>
	                                	</div>
	                                	
	                                	<div>
	                                		<i class="fa fa-flag"></i>
	                                		<div class='rel priority_text'>
	                                			<span class='priority_add'>우선순위 추가</span>
	                                			<div class="priority_wrap">
	                                				<div><i class="fa fa-arrow-down"></i>낮음</div>
	                                				<div><i class="fa fa-stop"></i>보통</div>
	                                				<div><i class="fa fa-arrow-up"></i>높음</div>
	                                				<div> <i class="fa fa-exclamation"></i> 긴급</div>
	                                			</div>
	                                		</div>
	                                	</div>
	                                	
	              
	                                
	                                	<div>
	                                		<i class="fa fa-paragraph"></i>
	                                		<div  class='progressbar_wrap'>
	                                		
	                                			<%for(int i = 1 ; i<= 10 ; i++){%>
	                                			
	                                				<div  class='progressbar rel'><em><%=i*10 %>%</em></div>
	                                		
	                                				
	                                			<%} %>
	                                			
	                                			
	                                		</div>
	                                		<b class='progressbar_percent'>10</b>%
	                                		
	                                	</div>
	                                	
	                                </div><!-- /board_attr_more_add -->
	                                
	                                <hr/>
	                                <div class='board_content'> 
	                                	 <textarea class="board_input_common_style head_content" onkeydown="resize(this)" onkeyup="resize(this)"  name="CONTENT" placeholder="내용을 입력하세요"></textarea>
	                                </div>
	                                
	                                
	                                
	                                
	                                <div>
	                                	<i class="fa fa-th-list"></i>
	                                	<span>하위업무 <span class='sub_work_count'>0</span></span> 
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
	                                	
	                                	<div class="board_add_status_button">+ 하위업무 추가</div>
	                                	
	                                	<div class='board_status_li board_status_write hide' >
	                                		<div  class='board_status_toggle abo ' >
		                                		<span class="com_status_span_style board-primary on" >요청</span>
		                                		<span class="com_status_span_style board-Success on" >진행</span>
		                                		<span class="com_status_span_style board-Info on" >완료</span>
		                                		<span class="com_status_span_style board-warning on" >보류</span>
		                                	</div>
	                                	
	                                		<span class='com_status_span_style board-primary on'>요청</span>
	                                		<input id="head_sub_work_input" type="text" placeholder="업무명 입력" value="하위업무추가!@~">
	                                		<i id="head_sub_work_input_close" class="fa fa-close"></i>
	                                	</div>
	                                	
	                                </div><!-- /more_business_list -->
	                                
	                               
	                                
	                                
	                        	</div><!-- /board_content_box -->
	                        	
	                        	
	      						<label >
									파일 첨부
									<img src="${pageContext.request.contextPath}/resources/image/attach.png" style='width:30px' alt="파일첨부">
									<input type="file" name="UPLOADFILE" class='boardfile' style='display:none'>
								</label>
								<span class="boardfilevalue"></span>
						      	<i  class="fa fa-window-close file_close_btn" style='display:none' aria-hidden="true"></i>
	      					
	      					   <button  type="submit" class="btn btn-primary head_board_submit_btn">전송</button>
	      					   <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
	      				</form>
	      			</div>
	      		</div>
	      	</div>
      	</div><%--BoardModal --%>
      	