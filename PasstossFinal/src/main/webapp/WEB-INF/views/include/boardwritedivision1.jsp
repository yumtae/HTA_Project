<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<style>
	#BoardModal1 .board_content {min-height:420px}
</style>

<!-- BoardModal1 -->
	<div class='modal BoardModal' id='BoardModal1' >
	      		<div class="modal-dialog">
		      		<div class="modal-content">
		      			<div class="modal-body">
		      				<form  name="headBoardForm" class='headBoardForm'  onsubmit="return false">
		      					<input hidden="hidden" />
		      					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		      					<sec:authorize access="isAuthenticated()">
									<sec:authentication property="principal" var="pinfo" />
								
		      						<input type="hidden" name="WRITER" value="${pinfo.username}"  >
		      						<input type="hidden" name="COMPANY_ID" value="${pinfo.companyId}"  >
		      					</sec:authorize>
		      					
		      					<input type="hidden" name="BOARD_DIVISION"  value="1"  >
		      					
	      					
	      						<div class='board_content_box'>
	                        		<dl class="post-author-info">
	                                   <b>게시물 작성</b>
	                                   	
	                                    <dd class="board_title_text_wrap">
	                                       <input type="text" class='board_input_common_style head_subject' name="SUBJECT" placeholder="제목을 입력하세요" value="">
	                                    </dd>
	                                </dl><!-- /post-author-info -->
	                                
	                              
	                                <div class='board_content'> 
	                                	 <textarea style='min-height:450px;bordeR:1px solid #aaa' class="board_input_common_style head_content" onkeydown="resize(this)" onkeyup="resize(this)"  name="CONTENT" placeholder="내용을 입력하세요"></textarea>
	                                </div>
	                                
	                                
	             
	                               
	                                
	                                
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
      	