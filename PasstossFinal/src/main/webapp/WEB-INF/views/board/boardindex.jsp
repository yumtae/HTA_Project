<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../include/head.jsp"/>


<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
      	<jsp:include page="../include/sidebar.jsp"/>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

			 <!-- Topbar -->
               <jsp:include page="../include/topbar.jsp"/>
				<!-- End of Topbar -->
				
				
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800"><b>"PassToss"</b> 업무 공유방</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                    <!-- Content Row -->

                    <div class="row">

                  
                        <!-- main_left_div -->
                        <div id="main_left_div" class="col-xl-7 col-lg-10">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">업무 리포트  ${listcountAll}</h6>
                                    <div class="dropdown no-arrow">
                                        <a class="dropdown-toggle " href="#"  id="dropdownMenuLink" >
                                            <i class="fas fa-angle-double-down fa-sm fa-fw text-gray-400"></i>
                                            <i class="fas fa-angle-double-up fa-sm fa-fw text-gray-400"></i>
                                        </a>
                                   
                                    </div>
                                </div>

			

                                <div class="card-body">
                                    <div id="chart-pie" class="chart-pie   ">
                                    	<div id="board_chard_box" class=' vertical_top' >
                                        	<canvas id="myPieChart"></canvas>
										</div>
										<div id="board_chard_text" class='inline vertical_top'>
												<div>
													<span >
			                                            <i class="fas fa-circle text-primary"></i> 요청
			                                        </span>
			                                        <span >
			                                            <i class="fas fa-circle text-success"></i> 진행
			                                        </span>
			                                     </div>
			                                     <div>
			                                        <span >
			                                            <i class="fas fa-circle text-warning"></i> 보류
			                                        </span>
													<span >
			                                            <i class="fas fa-circle text-info"></i> 완료
			                                        </span>
	                                        	</div>
	                                       </div>
                                    </div><!-- /chart-pie -->
                                  

                                </div><!-- /card-body -->
                           
                           
                           </div><!-- card -->
                           
                           
                                
                           
                           <div id="createPostArea" class="work-design-wrapper" >
	                       		 <ul id="createPostUl" class="work-design-group">
	                       		 	<li class="post-filter BoardModal1" data-toggle="modal"
                           data-target="#BoardModal1"><i class="fa fa-file-alt"></i><span>글</span></li>
	                       		 	<li class="post-filter BoardModal2" data-toggle="modal"
                           data-target="#BoardModal2"><i class="fa fa-business-time"></i><span>업무</span></li>
	                       		 	<li class="post-filter" data-toggle="modal"
                           data-target="#calendarModal"><i class="fa fa-calendar"></i><span>일정</span></li>
	                       		 	<li class="post-filter" data-post-code="4"><i class="fa fa-tasks"></i><span>할 일</span></li>
	                       		 	<li class="post-filter" data-post-code="5"><i class="fa fa-archive"></i><span>투표</span></li></ul>
		                        <div class="work-desing-element" data-toggle="modal"
                           data-target="#BoardModal1">
		                            <p class="work-desing-text inline" >내용을 입력하세요.</p>
		                            <div class="work-icon-group inline">
		                                <i class="fa fa-file"></i>
		                                <i class="fa fa-photo-video"></i>
		                                <i class="fa fa-hashtag"></i>
		                                <i class="fa fa-sign"></i>
		                            
		                            </div>
		                        </div>
		                    </div>
                           
                          
                        <div id="board_main_wrap">
                        	<div class="session">
                        		<b style='color:#000'>전체  ${listcount }</b>
                        	</div>
                        	
                        	
                    
                	<div class="board_content_comment_box_wrap">        	
			                  	<c:if test="${listcount > 0 }">
			                  	<sec:authorize access="isAuthenticated()">
									<sec:authentication property="principal" var="pinfo" />
												
			             			<c:forEach var="b" items="${boardlist }">
			             			
			             				<c:if test="${b.BOARD_DIVISION != 0}" >
			                        	<div class="board_content_comment_box board_content_comment_box${b.BOARD_NUM}">
			                        	
				                        	<div class='board_content_box '>
				                        		
				                        		
						                        		<dl class="post-author-info">
						                                    <dt>
						                                        <strong class=" "><span class='board_profile_img'><img src="${pageContext.request.contextPath}/image/profile-default.png"> </span> ${b.USERNAME}</strong>
						                                        <span class="board_date">${b.WRITE_DATE}</span>
						                                        <div class='rel right'>
						                                        	<i class="fa fa-dot-circle board_each_dot pointer "></i>
						                                        	<ul class='board_each_menu hide'>
						                                        		<li><a href="#1" onclick="updateBoardModal(${b.BOARD_NUM})" data-toggle="modal" 
						                                        		data-target="#BoardUpdateModal">
						                                        			<i class="fa fa-newspaper"></i> 수정</a>
						                                        		</li>
						                                        		<li><a href="#1" onclick="delete_board(${b.BOARD_NUM},event.target)"><i class="fa fa-trash"></i> 삭제</a></li>
						                                        	</ul>
						                                        </div>
						                                    </dt>
						                                    <dd class="board_title_text_wrap">
						                                        <strong>${b.SUBJECT}</strong>
						                                        <div class='board_title_num'>업무번호 ${b.BOARD_NUM}</div>
						                                    </dd>
						                                </dl><!-- /post-author-info -->
						                                
							                                
							                     
					                        		<!-- 게시분류2번일때 추가1-->
					                        		<c:if test="${b.BOARD_DIVISION == 2}" >
					                        		
						                                
						                                
						                                <div class="board_status_box">
						                                	<i class="fa fa-clock"></i>
						                                	<div class='board_status_li'>
						                                		<script>$('.board_status_box').last().html(returnStatusAll('${b.STATUS }'))</script>
						                                	</div>
						                                </div>
						                                
						                                
						                                <div class="board_projectmanagers_box">
						                                	<i class="fa fa-people-arrows"></i>
						                                	
						                                	  <c:if test="${not empty b.projectManagersList}">
																   
																   <c:forEach var="manager" items="${b.projectManagersList}">
															       		<div class='board_projectmanagers_people'>
															       			<span class="projectmanagers_id hide"> ${manager.ID} </span>
									                                		<img src="${pageContext.request.contextPath}/image/profile-default.png">
									                                		<b>${manager.USERNAME}</b>
									                                	</div>
																   </c:forEach>
																   
															 </c:if>
						                                	
						                              
						                                	
						                                	
						                                	
						                                	
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
						                                		<div class='priority_text'>
						                                			<script>$('.priority_text').last().html(returnPriority('${b.PRIORITY }'))</script>
						                                		</div>
						                                	</div>
						                                	
						                                	
						                                	<div>
						                                		<i class="fa fa-paragraph"></i>
						                                		<div class='progressbar_wrap'>
						                                			<script>$('.progressbar_wrap').last().html(writeProgressBar(${b.PROGRESS}))</script>
															
						                                		</div> 
						                                		<b class='progressbar_percent'>${b.PROGRESS}</b>%
						                                	</div>
						                                	
						                                </div><!-- /board_attr_more_add -->
				                                	</c:if><!-- 게시분류2번일때 -->
				                                
				                                <div class='board_content'> ${b.CONTENT}</div>
				                                
				                                
				                                
				                                <!-- 게시분류2번일때 하위업무추가-->
					                        		<c:if test="${b.BOARD_DIVISION == 2}" >
					                        		
					                        			
							                                <div>
							                                	<i class="fa fa-th-list"></i>
							                                	<span>하위업무 <span class="sub_work_count">${fn:length(b.subBoardList)}</span></span> 
							                                </div>
							                                
							                                <div class="more_business_list">
							                                
							                                	 
								                                	 <c:if test="${not empty b.projectManagersList}">
																		   
																		   <c:forEach var="sb" items="${b.subBoardList}">
																	       		
											                                			<div class='board_status_li'>
											                                				<script>$('.board_status_li').last().html(returnStatus('${sb.STATUS }'))</script>
											                                				${sb.SUBJECT } 
											                                				<a href='#1' onclick='subWorkRemove(${sb.BOARD_NUM } ,event.target) '>
											                                					<i class='fa fa-window-close business_list_remove_btn right' aria-hidden='true'></i>
											                                				</a>
											                                			</div>
											            
											                                	
																		   </c:forEach>
																		   
																	 </c:if>
																
															
							                                </div><!-- /more_business_list -->
				                                </c:if>
				                                <!-- 게시분류2번일때 -->
				                               
										          <%-- 파일을 첨부한 경우 --%>
									               <c:if test="${!empty b.FILE_NAME}">
									               	<div class='board_file_form'>
									                  <img src="${pageContext.request.contextPath}/resources/image/down.png" width="10px">
									                   	<form method="post" action="down" class='inline' style="height:0px">
									                   		<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> -->
									                   		<input type="hidden" value="${b.FILE_NAME}" name="filename">
									                   		<input type="hidden" value="${b.ORIGINAL_FILE_NAME}" name="original">
									                   		<input type="submit" value="${b.ORIGINAL_FILE_NAME}" >
									                   	</form>
									                  </div>
									               </c:if>
				                               
				                             
  <!-----------------게시분류 3번일때 ---------------------------->
		                        		<c:if test="${b.BOARD_DIVISION == 3}" >
		                        		
			                                
			                     
			                                
			                               
			                                
			                                
			                                <div class='board_attr_more_add'>
			                                	<div>
			                                		<i class="fa fa-calendar"></i>
			                                		 <span>시작일 ${b.START_DATE}</span> 
			                                		<%--<span>시작일 <fmt:formatDate value="${b.START_DATE}" pattern="YYYY-MM-DD HH:mm"/></span> --%>
			                                	</div>
			                                	
			                                	<div>
			                                		<i class="fa fa-calendar"></i>
			                                		 <span>종료일 ${b.END_DATE}</span> 
			                                		<%--
			                                		<span>종료일 <fmt:formatDate value="${b.END_DATE}" pattern="YYYY-MM-DD HH:mm"/></span>--%>
			                            
			                                	</div>
			                                	
			                                	
			                                	
			                                	
			                                </div><!-- /board_attr_more_add -->
	                                	</c:if>
<!---------------------- 게시분류3번일때 끝 ------------------------>
				                               
				                                
				                                
				                        	</div><!-- /board_content_box -->
				                        	
				                        	 <div class="board_comment_wrap">
				                        	 
				                        	 
				                        	 	<c:if test="${not empty b.getCommentList}">
															   
														   <c:forEach var="c" items="${b.getCommentList}">
													       		
													       	<div class='board_comment_box'>
							                        	 		<div>
						                                			<img class='board_comment_profile' src='../image/profile-default.png'>
						                                		</div>
								                                <div>
						                                			<div>${c.USERNAME } <span>${c.REG_DATE }</span>  <a href="#0" onclick='commentRemove(${c.NUM},event.target)'>삭제</a> </div>
						                                			<div>${c.CONTENT }</div>
						                                		</div>
							                                </div>	
							                                	
														   </c:forEach>
														   
													 </c:if>
													 
														 
				                      
			                                	
			                               
			                                	<div class="board_comment_write_wrap">
			                                		<div>
			                                			<img class='board_comment_profile' src="${pageContext.request.contextPath}/image/profile-default.png">
			                                		</div>
			                                		
		                                		
						      				
							      					
							      					
			                                		<div class='board_comment_input_wrap'>
			                                			<form  class='inline comment_form' onsubmit="return false">
			                                				<input type="hidden" class ="forcommentnum" value="${b.BOARD_NUM}">
			                                				<input type="hidden" class ="forcommentid"  value="${pinfo.username}">
				                                			<input type="text" class ="forcommenttext" placeholder="Enter클릭시 입력" >
			                                			</form>
			                                		</div>
				                                	
				                                	
			                                	</div>
			                                	
			                                	
			                                	
			                                </div><!-- board_comment_wrap -->
				                                
				                                
			                        	</div><!-- /board_content_comment_box -->
			                        	
			                        	</c:if>
			                        	
			                        	
			                        	
			                   	</c:forEach>
			                   	</sec:authorize>
			                   </c:if>
                        	</div><!-- //board_content_comment_box_wrap -->
                        </div><!-- /board_main_wrap -->
                        
                        
                        
                           
                        </div><!-- /main_left_div -->
                        
                     
                       <div class="main_right_div">
                        	<div class='main_right_fixed_wrap'>
	                        	<b>참여자</b> <span></span> 
	                        	

	                        	<button type="button"  id="btn-participants" 
	                        			style="float: right; background : none; border: none;">
	                        		전체보기
	                        	</button>
	                        	
	                        	 <!-- The Modal -->
  								  <div id="participantsModal" class="participants-overlay">
    								<div class="participants-window">
      
        							   <!-- Modal Header -->
        								<div class="participants-title">
          								 <h6 style="font-weight: bold;">참여자 관리</h6>
        								</div>
        								
        								<div class="participants-close">X</div>
        								
        								<div class="input-group chatsearch" style="width:95% !important">
        								 <div class="input-group-append">
										   <button class="btn btn-light" type="button" style="border-right:none;">
										    <i class="fas fa-search fa-sm"></i>
										   </button>
										  </div>
            							 <input type="text" class="form-control bg-light small" placeholder="이름 검색" 
            												aria-label="Search" aria-describedby="basic-addon2"
            												 style="border-left:none;">
										</div>
										
        
    								 </div>
  									</div>
  								  
	                        	
	                        	<div class='board_content_box' id="participantslist" style='border:1px solid #aaa;border-radius:5px'>
	                        	   <p id="title-manager">관리자</p>
	                        	   <p id="title-member">임직원</p>
	                                 <button  class="btn btn-light" style="width:100%;">
	                                    <img src="../resources/image/chat.png" style="width:50px; height:45px;">채팅 
	                                  </button>
	                               
	                        	</div><!-- /board_content_box -->
                        	</div><!-- /main_right_fixed_wrap -->
                        </div><!-- /main_right_div -->
                        
                        
                        
                    </div><!-- /row -->
				</div><!-- /container-fluid -->


           
        </div>
        	<!-- /content -->
      
		<jsp:include page="../include/footer.jsp"/>

    </div>
	  <!-- End of Content Wrapper -->
	

   
</div>   
	    <!-- End of Page Wrapper -->


		
	
	      	

</body>

</html>



   <!-- Page level plugins -->
   
   
  
   
   <script src="${pageContext.request.contextPath}/js/chart/Chart.min.js"></script>
<!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/js/board_main_chart.js"></script>
   
   
   
    <script src="${pageContext.request.contextPath}/js/participants.js"></script>
   <script>
   
   var chartDataA = 0;
   var chartDataB = 0;
   var chartDataC = 0;
   var chartDataD = 0;
   

   
 
   
   
   	$(window).on('load',function(){
   	  <c:forEach var="item" items="${groupByStatus}">
	      <c:if test="${item.STATUS eq '요청'}">
	      		chartDataA	=	${item.COUNT}
	      </c:if>
	      <c:if test="${item.STATUS eq '진행'}">
	   		chartDataB	=	${item.COUNT}
	   	</c:if>
	      <c:if test="${item.STATUS eq '보류'}">
	       	chartDataC	=	${item.COUNT}
	      </c:if>
	      
	   	<c:if test="${item.STATUS eq '완료'}">
	   		chartDataD	=	${item.COUNT}
	   	</c:if>
	      
	    </c:forEach>
   		
	    if(chartDataA ==0 && chartDataB ==0 && chartDataC ==0 && chartDataD ==0 ){
	    	chartDataA = 1;
	    	chartDataB = 1;
	    	chartDataC = 1;
	    	chartDataD = 1;
	    }
	    
	    
   		myPieChart.data.datasets[0].data[0] =  [chartDataA];
   		myPieChart.data.datasets[0].data[1] =  [chartDataB];
   		myPieChart.data.datasets[0].data[2] =  [chartDataC];
   		myPieChart.data.datasets[0].data[3] =  [chartDataD];
   		
   		myPieChart.options.elements.center.text = "${listcountAll}";
   		
   		myPieChart.update();
   		
   	})
   </script>
