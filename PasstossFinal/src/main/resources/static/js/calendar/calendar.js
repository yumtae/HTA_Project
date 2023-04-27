$("#updateCalendarbtn").on("click", function() {
							var title = $("#calendar_title").val();
							var content = $("#calendar_content").val();
							var start_date = $("#calendar_start_date").val();
							start_date = moment(start_date).format('YYYY-MM-DD HH:mm');
							var end_date = $("#calendar_end_date").val();
							end_date = moment(end_date).format('YYYY-MM-DD HH:mm');
							var num=$("arg.event._def.extendedProps.num").val();
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
								
					
									
					var settings = {
						  "url": "http://localhost:9600/passtoss/cal/calUpdate",
						  "method": "POST",
						  "headers": {
						    "Content-Type": "application/x-www-form-urlencoded"
						  },
						  "data": {
							  "BOARD_NUM": num,
							  "SUBJECT": title,
							    "CONTENT": content,
							    "START_DATE": start_date,
							    "END_DATE": end_date
						    
						  },
						};
						$.ajax(settings).done(function (response) {
							//location.reload(); 
						console.log(response);
						 // getlist();
						 // calendar.render();
						  $("#calUpdateModal").modal("hide"); // modal 나타내기
						});
													
					
					   
					      }
							    
						
						
	


		
			})