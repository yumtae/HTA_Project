<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../include/head.jsp" />
<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>calendar</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>

 <meta name="_csrf" content="${_csrf.token}">  <!-- js 파일에서 post방식으로 사용할때 토큰 보내는 용도 -->
  <meta name="_csrf_header" content="${_csrf.headerName}"> <!-- js 파일에서 post방식으로 사용할때 토큰 보내는 용도 -->
  
<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- bootstrap 4 -->
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
<script class="cssdesk" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.0/moment.min.js" type="text/javascript"></script>	

<!-- fullcalendar -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.7.0/main.min.css">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/fullcalendar@5.7.0/main.min.js"></script>
 <script src="../resources/js/calendar/calendar.js" ></script>	

<script type="text/javascript">

var calendar = null;
var list = []; //DB에서 오는 데이터가 배열이라 배열 생성
getlist(); //세미콜론 없어도 됨
function getlist() {
	$.ajax({
		type : 'get',
		url : 'http://localhost:9600/passtoss/cal/view', //데이터베이스
		dataType : 'json', ////DB로부터 받을 데이터라서 데이터 부분 비어 있음.
		async : false
	//동기식
	}).done(function(result) {
		list = result; //배열에 DB로부터 받은 값 집어넣기
		console.log(list)
	}) //성공시
	.fail(function(request, status, error) {
		alert("에러발생:" + error);
	}) //실패시
}
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
		timeZone: 'UTC',
			//eventTimeFormat: { // like '14:30:00'
			  //  hour: '2-digit',
			  //  minute: '2-digit',
			   // second: '2-digit',
			  //  hour12: false
			//  },

			initialView : 'dayGridMonth', // 홈페이지에서 다른 형태의 view를 확인할  수 있다.
			headerToolbar : {
				left: 'title',
				center : 'addEventButton', // headerToolbar에 버튼을 추가
				right: 'prev,next,today,dayGridMonth,timeGridWeek,timeGridDay,listWeek'
					
			},
			
			customButtons : {
				addEventButton : { // 추가한 버튼 설정
					text : "일정 추가", // 버튼 내용
					click : function() { // 버튼 클릭 시 이벤트 추가
						$("#calendarModal").modal("show"); // modal 나타내기
						
					}
				}
			},
			eventClick : function(arg) {
			   console.log(arg)   
			    console.log(arg.event._instance.range.start)
			  <%-- var calUser = arg.event._def.extendedProps.user;
			    var loginUser = $("#loginUser").val();
			    console.log("------------------------------------");
			    console.log(calUser, loginUser);
			    
			    if (loginUser!=calUser){
			    	alert("수정 불가(아이디 오류)");
			    	return;
			    } --%> 
				
				 if (confirm("일정을 수정하시겠습니까 ?")) {
						$("#calUpdateModal").modal("show"); // modal 나타내기
						$("input[name=BOARD_NUM]").val(arg.event._def.extendedProps.num);
					    $("input[name='calendar_title']").val(arg.event._def.title);
					    $("input[name='calendar_start_date']").val(moment(arg.event._instance.range.start).format('YYYY-MM-DD HH:mm'));
					    $("input[name='calendar_end_date']").val(moment(arg.event._instance.range.end).format('YYYY-MM-DD HH:mm'));
					    $("textarea[name='calendar_content']").val(arg.event._def.extendedProps.content);
					   
					    $("#deleteCalendar").on("click", function() {
					    	if(!confirm('정말 삭제하시겠습니까?')){
								return;
							}
					    	arg.event.remove();
							console.log(arg.event); 
							var events = new Array(); // Json 데이터를 받기 위한 배열 선언
							var obj = new Object();
							obj.num = arg.event._def.extendedProps.num;
							/*obj.num = arg.event._def.defId;*/
							
							console.log(obj.num)

							var settings = {
								  "url": "http://localhost:9600/passtoss/cal/calDel",
								  "method": "POST",
								  "headers": {
								    "Content-Type": "application/x-www-form-urlencoded"
								  },
								  "data": {
								    "BOARD_NUM": obj.num
								  },
								};
								$.ajax(settings).done(function (response) {
									location.reload(); 
								console.log(response);
								  getlist();
								  calendar.render();
								})
								});
					    
					    
			}
			},
			events : list,
			editable : true, // false로 변경 시 draggable 작동 x 
			displayEventTime : false, //시간 표시
			
		// 시간 표시 x
		});
		
		
		calendar.render();
	});
</script>
<style>
#calendarBox {
	width: 65%;

	padding-top: 2em; padding-bottom: 2em;
	 margin: 0 auto;
}
</style>
</head>
<!-- isAnonymous() : 익명 사용자인 경우 로그인 페이지로 이동하도록 합니다. -->
<sec:authorize access="isAnonymous()"> 
	<script>
		location.href = "${pageContext.request.contextPath}/member/login";
	</script>
</sec:authorize>


<body id="page-top">

<sec:authorize access="isAuthenticated()"> <!-- 로그인해서 아이디 값이 있으면  -->
		<!-- 로그인하면 로그인한 사람의 정보가 principal에 담김 principal는 시큐리티가 가지고 있는애  -->
		<!-- Principal은 자바의 표준 시큐리티 기술로, 로그인이 된 상태라면 계정 정보를 담고 있습니다. -->
		  <sec:authentication property="principal" var="pinfo"/>
		  <input type="hidden" id="loginUser" value="${pinfo.username} }"> 
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<jsp:include page="../include/sidebar.jsp" />
		<!-- End of Sidebar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<jsp:include page="../include/topbar.jsp" />
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">
					 <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h3 class="m-0 font-weight-bold text-primary"><b>Calendar</b></h3>
                        </div>
						<div id="calendarBox">
							<div id="calendar"></div>
						</div>
				
					</div>
					<!-- /row -->
				</div>
				<!-- /container-fluid -->
			
			</div>
			<!-- /content -->
			<jsp:include page="../include/footer.jsp" />
		</div>
		<!-- End of Content Wrapper -->
		</div>
			</sec:authorize>
</body>