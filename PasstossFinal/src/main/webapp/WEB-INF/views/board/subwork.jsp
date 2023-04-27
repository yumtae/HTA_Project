<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/head.jsp"/>


<style>
		#subwork_table_wrap{background:#fff;font-size:.8em;}
		#subwork_table_wrap .board_projectmanagers_people{margin:3px 0}
            	#subwork_table th{}
            	#subwork_table th,#subwork_table td{    border: 1px solid #ddd;
								    padding: 3px 5px;
								    text-align: center;
								    vertical-align: middle;}
				#subwork_table td:nth-child(1){text-align:left}
				
				#subwork_table_wrap {
				    width: auto;
				    max-width: 100%;
				}
				#subwork_table .com_status_span_style{width:100%;display:inline-block;padding:7px 15px}
				#subwork_table .priority_add i{vertical-align:middle}
				
				.mCustomScrollBox{height:auto !important}
				
				.mCSB_scrollTools.mCSB_scrollTools_horizontal{background:#eee;}
				#subwork_table .progressbar_wrap{width:70%}
				#subwork_table .progressbar {width:10%}
	
</style>


<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
      	<jsp:include page="../include/sidebar.jsp"/>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column " >

            <!-- Main Content -->
            <div id="content">

			 <!-- Topbar -->
               <jsp:include page="../include/topbar.jsp"/>
				<!-- End of Topbar -->
				
				
                <!-- Begin Page Content -->
                <div  class="container-fluid rel " >

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800"><b>"PassToss"</b> 업무 공유방</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                    <!-- Content Row -->

					<div>
                   	 	<div  id="subwork_table_wrap" class="center">
						
	                  		<table id="subwork_table" class='table'>
	                  				<colgroup>
	                  					<col width=450px><!-- 업무명 -->
	                  					<col width=85px> <!-- 상태 -->
	                  					<col width=85px> <!-- 우선순위 -->
	                  					<col width=100px> <!-- 담당자 -->
	                  					<col width=120px>  <!-- 시작일 -->
	                  					<col width=120px> <!-- 마감일 -->
	                  					<col width=150px> <!-- 등록일 -->
	                  					<col width=100px> <!-- 업무번호 -->
	                  					<col width=90px> <!-- 작성자 -->
	                  					<col width=120px> <!-- 수정일 -->
	                  					<col width=200px> <!-- 진행도 -->
	                  				</colgroup>
	                  		
	                  				<tr>
	                  					<th>업무명</th>
	                  					<th>상태</th>
	                  					<th>우선순위</th>
	                  					<th>담당자</th>
	                  					<th>시작일</th>
	                  					<th>마감일</th>
	                  					<th>등록일</th>
	                  					<th>업무번호</th>
	                  					<th>작성자</th>
	                  					<th>수정일</th>
	                  					<th>진행도</th>
	                  				</tr>
	                  		
	                  				<c:if test="${listcount > 0 }">
	                  					<c:forEach var="b" items="${boardlist }">
	                  						<c:if test="${b.BOARD_DIVISION == 2 || b.BOARD_DIVISION == 0}" >
	                  					
		                  						
			                  					<tr onclick="updateBoardModal(${b.BOARD_NUM})" data-toggle="modal" 
			                                        		data-target="#BoardUpdateModal">
			                  						<td>
			                  						
				                  						<c:if test="${b.BOARD_RE_LEV !=0 }"> <%-- 답글인 경우--%>
															<c:forEach var="a" begin="0" end="${b.BOARD_RE_LEV*2 }" step="1">
																&nbsp;
															</c:forEach>
															<img src="../resources/image/line.gif">
														</c:if>
			                  							${b.SUBJECT }
				                  					
			                  						
			                  						
			                  						</td>
				                  					<td class='board_status_box'>
				                  						<script>$('.board_status_box').last().html(returnStatus('${b.STATUS }'))</script>
									               </td>
				                  					
				                  					<td>
				                  						<span class="priority_text"></span>
				                  						<script>$('.priority_text').last().html(returnPriority('${b.PRIORITY }'))</script>
				                  					</td>
				                  					
				                  					<td>
				                  						
				                  						  <c:if test="${not empty b.projectManagersList}">
																   
																   <c:forEach var="manager" items="${b.projectManagersList}">
															       		<div class='board_projectmanagers_people'>
															       			<span class="projectmanagers_id hide"> ${manager.ID} </span>
									                                		<b>${manager.USERNAME}</b>
									                                	</div>
																   </c:forEach>
																   
															 </c:if>
				                  					
				                  					
				                  					</td>
													
													<td>-</td>
													<td>-</td>
													<td>${b.WRITE_DATE}</td>
													<td>${b.BOARD_NUM}</td>
													<td>${b.USERNAME}</td>
													<td>${b.MODIFY_DATE}</td>
													<td>
													
														<div class='progressbar_wrap'>
			                                		
				                                			<script>
				                                			document.write(writeProgressBar(${b.PROGRESS}));
				                                			</script>
				                                			
				                                			
				                                		</div>
				                                		<b class='progressbar_percent'>${b.PROGRESS}</b>%
													
													</td>
		
		
				                  				</tr>
				                  				
			                  				
			                  				
			                  				</c:if>
			                  			</c:forEach>
	                  				</c:if>
	                  				
	                  		
	                  		</table>
               
                  
                        
                        
                    </div><!-- /row -->
				</div><!-- /subwork_table_wrap -->
			</div>

           
        </div>
        	<!-- /content -->
      
		<jsp:include page="../include/footer.jsp"/>

    </div>
	  <!-- End of Content Wrapper -->
	

   
</div>   
	    <!-- End of Page Wrapper -->


		
	
	      	

</body>


<script>
	$(function(){
			$(window).on('load',function(){
				$("#subwork_table_wrap").mCustomScrollbar({
					axis:"x",
					advanced:{
						autoExpandHorizontalScroll:true
					},
				});
				
				
			})
		
	})
</script>

<script src="../resources/js/board/jquery.mCustomScrollbar.concat.min.js"></script>
 <link href="../resources/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">