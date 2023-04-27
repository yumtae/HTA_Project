$(function(){

	let roomNumber = 0;
	let sessionId = "";
	var msg = "";
	let inputdate = "";
	let first = {};
	let systemname;
	let status = "";
	getChatList();
	getStatus();
	getRoom();
	
	//채팅방 있는지 확인하기 
	function getRoom(){
	 
		$.ajax({
			url: '../myChat/getRoom',
			type: 'get',
			data: {userid : $("#userid").val()},
			async: false, //동기화 
			success: function(result){ // 0이면 채팅방 없음, 1이면 채팅방 있음
				if(result == 0){
					createRoom();
				}else{
					roomNumber = result;
					console.log("getroomNumber = " + roomNumber);
					wsOpen();
				}
			},  error: function(error) {
				console.log("error");
				}
		});//$.ajax
	} //getRoom()
	
	//채팅방 만들기 
	function createRoom(){
		console.log("createRoom");
		$.ajax({
			url : '../myChat/createRoom',
			type: 'post',
			data: {userid : $("#userid").val()},
			//시큐리티 적용하면 토큰 넣어주기 beforeSend: function 
			success: function(result){
				if(result == 0){ // 채팅방 생성 실패 
					alert("채팅방 생성에 실패하셨습니다");
				}else{ //채팅방 생성 성공
					getRoom();
				}
			}, error: function(error) {
				console.log("error");
			}
		})//$.ajax
	}//createRoom()
	
	var ws;

	function wsOpen(){
		//웹소켓 전송시 현재 방의 번호, 필요한 정보를 담아서 보낸다.
		ws = new WebSocket("ws://" + location.host + "/passtoss/chating?roomNumber="+ roomNumber);
		chatLog();
		wsEvt();
	} // function wsOpen()
	
	//본인 접속상태 정보 가져오기
	function getStatus(){
	   output = "";
		$.ajax({
		  url : '../myChat/getStatus',
		  type: 'get',
		  success : function(rdata){
			  console.log("가져오기 성공");
			 
			  var low_status = rdata.STATUS.toLowerCase();
			  //$("#profile-img").removeClass();
			  //$("#profile-img").addClass(low_status);
			  //output += '<img id="profile-img" src="../upload/'+ rdata.PROFILE_IMG +'" class="'+ low_status +'" alt=""/>'
			  //$("#chatMyId").before(output);
			  $("#profile-img").attr("src", "../upload/"+rdata.PROFILE_IMG);
			  $("#profile-img").attr("class", low_status);
			  
		  },error : function(){
			 console.log("getStatus");
		  }
		})//$.ajax
	}
	
	//채팅로그 가져오기 
	function chatLog(){
		console.log("log roomNumber =" + roomNumber);
		$.ajax({
			url : '../myChat/chatLog',
			type : 'get',
			data : {roomNumber : roomNumber},
			success : function(rdata){
				
				$(rdata).each(function(index, item) {
				     console.log("log_item =" + JSON.stringify(item));
					 console.log($("#userid").val());
					if(item.chat_content.indexOf(";img;") > -1){ //이미지가 들어오면 이미지 출력하는 부분
						  //systemname = encodeURIComponent(item.chat_file); 
						  console.log(item.chat_content);
						  if(item.chat_id == $("#userid").val()){ // 상대방 
						     item.chat_content = "<div class='img'><img class='msgImg send_img' src=../chat/upload"+item.chat_file+"></div><div class='clearBoth'></div>";
						    $('<li class="sent"><img src="../upload/'+ item.chat_profile +'" style="width:30px; height: 30px; alt=""  />' 
						      +'<a href="../myChat/down">'
							  + item.chat_content  + '</a><span class="divimg" id="msg1">'+ item.chat_date +'</span></li>').appendTo($('.messages ul'));
							$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
							$('.contact.active .preview').html('<span> </span>' + item.chat_content); // 옆에 대화 리스트에서 채팅 변경 
							//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기
							$('.messages').scrollTop($('.messages')[0].scrollHeight);
						}else{ // 본인 
							item.chat_content = "<img class='msgImg send_img' src=../chat/upload"+item.chat_file+"></div><div class='clearBoth'>";
							$('<li class="replies"><img src="../upload/'+ item.chat_profile +'" style="width:30px; height: 30px; alt="" />'
							  +'<a href="../myChat/down">'
							  + item.chat_content + '</a><div id="msg1">'+ item.chat_date +'</div></li>').appendTo($('.messages ul'));
							$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
							$('.contact.active .preview').html('<span> </span>' + item.chat_content); // 옆에 대화 리스트에서 채팅 변경 
							//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기
							$('.messages').scrollTop($('.messages')[0].scrollHeight);
						}
					}else if(item.chat_content.indexOf(";file;") > -1){
						systemname = encodeURIComponent(item.chat_file);
					    item.chat_content = item.chat_content.substring(0,item.chat_content.length-6);
						console.log("file;");
						if(item.chat_id == $("#userid").val()){ // 상대방 
							$('<li class="sent"><img src="../upload/' + item.chat_profile + '" style="width:30px; height: 30px; alt=""/>' 
							  +'<p><a href="../myChat/down?filename='+ systemname +'&original='+ item.chat_content +'" style="color: white; ">' + item.chat_content + 
							  '</a></p><span id="msg1">' + item.chat_date + '</span></li>').appendTo($('.messages ul'));
							$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
							$('.contact.active .preview').html('<span> </span>' + item.chat_content); // 옆에 대화 리스트에서 채팅 변경 
							//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기
							$('.messages').scrollTop($('.messages')[0].scrollHeight);
						}else{ // 본인 
							$('<li class="replies"><img src="../upload/' + item.chat_profile + '" style="width:30px; height: 30px; alt=""/>' 
							  +'<p><a href="../myChat/down?filename='+ systemname +'&original='+ item.chat_content +'" style="color: black;">' + item.chat_content + 
							  '</p></a><span class="spandate" id="msg1">' + item.chat_date + '</span></li>').appendTo($('.messages ul'));
							$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
							$('.contact.active .preview').html('<span> </span>' + item.chat_content); // 옆에 대화 리스트에서 채팅 변경 
							//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기
							$('.messages').scrollTop($('.messages')[0].scrollHeight);
						}
					}
					else if(item.chat_content.indexOf(";img;") === -1 || item.chat_content.indexOf(";file;") === -1){
						if(item.chat_id == $("#userid").val()){ // 상대방 
						  console.log("log_item =" + item);
					 	  console.log($("#userid").val());
						    $('<li class="sent"><img src="../upload/'+ item.chat_profile +'" style="width:30px; height: 30px; alt=""  /><p>' 
							  + item.chat_content  + '</p><span id="msg1">'+ item.chat_date +'</span></li>').appendTo($('.messages ul'));
							$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
							$('.contact.active .preview').html('<span> </span>' + item.chat_content); // 옆에 대화 리스트에서 채팅 변경 
							//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기
							$('.messages').scrollTop($('.messages')[0].scrollHeight);
						}else{ // 본인 
							$('<li class="replies"><img src="../upload/'+ item.chat_profile +'" style="width:30px; height: 30px; alt="" /><p>'
							  + item.chat_content + '</p><div id="msg1">'+ item.chat_date +'</div></li>').appendTo($('.messages ul'));
							$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
							$('.contact.active .preview').html('<span> </span>' + item.chat_content); // 옆에 대화 리스트에서 채팅 변경 
							//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기
							$('.messages').scrollTop($('.messages')[0].scrollHeight);
						}
					}
					$('.messages').scrollTop('200px');
				})//$(rdata).each(function(index, item)
			}, error: function(error) {
				console.log("error");
			}
		})//$.ajax
	}// function chatLog()
	
	function wsEvt() {
		ws.onopen = function(data){
			//소켓이 열리면 동작
		}//ws.onopen
		
		ws.onmessage = function(data){
			console.log("onmessage");
			//메시지를 받으면 동작
			msg = data.data;
			var d = ""; //서버에서도 JSON으로 전달해서 받는데이터도 JSON으로 
		    console.log(msg);

		    //else if(d.type == "fileUpload" && d.filetype.indexOf("image") > -1 ){ // 이미지 
			if(msg != null && msg.type != ''){
			  d = JSON.parse(msg); //서버에서도 JSON으로 전달해서 받는데이터도 JSON으로 
			  console.log("if msg.type =" + d.type);
			  console.log("if msg.filetype =" + d.filetype);
			  console.log("onmessage msg");
				if(d.type == "getId"){ //type값을 확인해서 값이 같으면 초기설정된 값이므로
				  console.log("onmessage getid"); 
				  console.log("id 체크 =" + d.sessionId);
					var si = d.sessionId != null ? d.sessionId : ""; 
					if(si != ''){
						//세션아이디에 값을 세팅(id값은 소켓이 종료되기 전까지 자기자신을 구분하는 session값으로 사용)
						//$("#sessionId").val(si); 
						sessionId = si
						console.log("si ="+ si);
						console.log("sessionid 저장 =" + sessionId); 
					}
				}else if(d.type == "message"){//type값이 message인 경우 채팅 발생
				  console.log("onmessage message");
				  if(d.msg.indexOf(";file;") > -1){
				   systemname = encodeURIComponent(systemname);
				    d.msg = d.msg.substring(0,d.msg.length-6);
				   if(d.sessionId == sessionId){ // 상대방과 자신을 구분하기위해서 sessionId사용 
					  console.log("onmessage 본인");
					  console.log("input" + inputdate);
					  console.log("d.profile =" + d.profile);
						$('<li class="replies"><img src="../upload/'+ d.profile +'" style="width:30px; height: 30px; alt="" />'
						    +'<p><a href="../myChat/down?filename='+ systemname +'&original='+ d.msg +'" style="color: black;">' + d.msg + 
						    '</p></a><span class="spandate" id="msg1">' + inputdate + '</span></li>').appendTo($('.messages ul'));
						$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
						$('.contact.active .preview').html('<span></span>' + d.msg); // 옆에 대화 리스트에서 채팅 변경 
						//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기  
						$('.messages').scrollTop($('.messages')[0].scrollHeight);
					}else{//타인이 보낸건 왼쪽 정렬 
					  console.log("onmessage 타인");
					  console.log("input" + inputdate);
					  console.log("d.profile =" + d.PROFILE);
						$('<li class="sent"><img src="../upload/'+ d.profile +'" style="width:30px; height: 30px; alt="" />' 
						    +'<p><a href="../myChat/down?filename='+ systemname +'&original='+ d.msg +'" style="color: white;">' + d.msg + 
						    '</a></p><span id="msg1">' + d.time + '</span></li>').appendTo($('.messages ul'));
						$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
						$('.contact.active .preview').html('<span> </span>' + d.msg); // 옆에 대화 리스트에서 채팅 변경 
						//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기 
						$('.messages').scrollTop($('.messages')[0].scrollHeight);
					}
				  }else{
					if(d.sessionId == sessionId){ // 상대방과 자신을 구분하기위해서 sessionId사용 
					  console.log("onmessage 본인");
					  console.log("input" + inputdate);
					  console.log("d.profile =" + d.profile);
						$('<li class="replies"><img src="../upload/'+ d.profile +'" style="width:30px; height: 30px; alt="" /><p>'
							+ d.msg + '</p><div id="msg1">'+ inputdate +'</div></li>').appendTo($('.messages ul'));
						$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
						$('.contact.active .preview').html('<span></span>' + d.msg); // 옆에 대화 리스트에서 채팅 변경 
						//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기  
						$('.messages').scrollTop($('.messages')[0].scrollHeight);
					}else{//타인이 보낸건 왼쪽 정렬 
					  console.log("onmessage 타인");
					  console.log("input" + inputdate);
					  console.log("d.profile =" + d.PROFILE);
						$('<li class="sent"><img src="../upload/'+ d.profile +'" style="width:30px; height: 30px; alt="" /><p>' 
							+ d.msg  + '</p><span id="msg1">'+ d.time +'</span></li>').appendTo($('.messages ul'));
						$('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
						$('.contact.active .preview').html('<span> </span>' + d.msg); // 옆에 대화 리스트에서 채팅 변경 
						//$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기 
						$('.messages').scrollTop($('.messages')[0].scrollHeight);
					}
				  }
				}else if(d.type == "fileUpload"){
				   console.log("first");
					first = d;
					
				}
				 console.log("if문 통과"); 
				 console.log("first =" + JSON.stringify(first));
			} //if(msg != null && msg.trim() != '')
			//else if(d.type == "fileUpload" && d.filetype.indexOf("image") > -1 ){ // 이미지 
			else { // 이미지 
				 console.log("else if d.type =" + first.type);
				 console.log("else if d.filetype =" + first.filetype);
				 systemname = first.filesystemname;
				 console.log("file systemname =" + systemname);
					var url = URL.createObjectURL(new Blob([msg]));
					
					if(first.sessionId == sessionId){ // 상대방과 자신을 구분하기위해서 sessionId사용 
					 console.log("else if onmessage 본인");
					 console.log("input" + inputdate);
					 if(first.filetype.indexOf("image") > -1){
					  image = "<img class='msgImg send_img' style='padding-right: 15px;' src="+url+"><div class='clearBoth'></div>";
					  $('<li class="replies"><a href="../myChat/down">' + image + '</a></li>').appendTo($('.messages ul'));
					 }
					  $('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
					  $('.contact.active .preview').html('<span></span>'); // 옆에 대화 리스트에서 채팅 변경 
					  //$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기  
					  $('.messages').scrollTop($('.messages')[0].scrollHeight);
					  chatSave(first);
					 }else{//타인이 보낸건 왼쪽 정렬 
					  console.log("else if onmessage 타인");
					  if(first.filetype.indexOf("image") > -1){
						image = "<div class='img' style='padding-left:20px;'><img class='msgImg send_img' src="+url+"></div><div class='clearBoth'></div>"
						$('<li class="sent"><a href="../myChat/down">' + image + '</a></li>').appendTo($('.messages ul'));
					  }
					  $('.message-input input').val(null); // 채팅창 붙이고 나면 입력 내용지우기 
					  $('.contact.active .preview').html('<span> </span>' + msg.msg); // 옆에 대화 리스트에서 채팅 변경 
					  //$(".messages").animate({ scrollTop: $(document).height() }, "fast"); //스크롤 맨 밑으로 내리기 
					  $('.messages').scrollTop($('.messages')[0].scrollHeight);
					}
					first = {};
				}
		}//ws.onmessage
		
		console.log("sessionid 저장 후 =" + sessionId);
			
		$('.submit').click(function() { 
		  if($("#chatWrite").val() != null && $("#chatWrite").val().trim() != ''){
			console.log("onmessage 전송");
  			send();
  		   }
		}); //$('.submit').click(function()
			
		document.addEventListener("keypress", function(e){
		if(e.keyCode == 13){ //엔터키 누르면 전송 
		   if($("#chatWrite").val() != null && $("#chatWrite").val().trim() != ''){
			 console.log("onmessage 엔터");
			 send();
		   }
		}
		}); //document.addEventListener("keypress", function(e)
	} //function wsEvt()	
  
 	//채팅 보내기
	function send(){
		chatSave();
		
	}// function send()
		  
	//채팅내용 DB에 저장하기 
	function chatSave(first){
		console.log("save roomNumber =" + roomNumber);
		console.log("save chat_sender =" + $("#chatMyId"). text());
		console.log("save chat_content =" + $("#chatWrite").val());
		console.log("myprofile =" + $("#myProfile").val());
		 	
		let chat_sender = $("#chatMyId"). text();
		let chat_content = $("#chatWrite").val();
		let chat_profile = $("#myProfile").val();
		
		var data = {
		  		roomNumber : roomNumber,
		  		chat_sender : chat_sender,
		  		chat_content : chat_content,
		  		chat_profile : chat_profile
		  	}
		
		if(first != undefined){ // 속성 추가 
			data.chat_file = first.filesystemname;
			data.chat_original = first.msg;
			data.chat_content = first.msg;
			
			if(first.filetype.indexOf("image") > -1){
				data.chat_content = first.msg + ";img;";
			}else{
				data.chat_content = first.msg + ";file;";
			}
		}
		
		
		 	
		$.ajax({
		  	url : '../myChat/chatSave',
		  	type : 'post',
		  	data : data,
		  	async: false,
		  	//시큐리티 적용하면 토큰 넣어주기 beforeSend: function 
		  	success: function(result){
				if(result == 1){
					console.log("채팅저장 성공")
					$.ajax({
						url : '../myChat/getChat',
						type : 'get',
						data : {roomNumber : roomNumber},
						async: false,
						success : function(rdata){
						  if(rdata.chat_content.indexOf(";img;") > -1){
						  	rdata.chat_content = rdata.chat_content.substring(0,rdata.chat_content.length-5);
						  	console.log(rdata.chat_content);
						  }//else if(rdata.chat_content.indexOf(";file;") > -1) {
						  	//rdata.chat_content = rdata.chat_content.substring(0,rdata.chat_content.length-6);
						  	//console.log(rdata.chat_content);
						  //}
						  
							var option ={
								type : "message",
								roomNumber : roomNumber.toString(),
								sessionId : sessionId,
								userName : rdata.chat_sender,
								msg : rdata.chat_content,
								time : rdata.chat_date,
								profile : rdata.chat_profile
							}
							
							inputdate = rdata.chat_date;
							
							console.log("send()");
				 			console.log("roomNumber =" + roomNumber);
				 			console.log("sessionId =" + sessionId);
				 			console.log("chatMyId =" + rdata.chat_sender);
				 			console.log("chatWrite=" + rdata.chat_content);
				 			console.log("Time =" + inputdate);
				 			console.log("profile =" + rdata.chat_profile);
				 			
				 			
				 			ws.send(JSON.stringify(option))
				 			$("#chatWrite").val("");
				 			
						},error: function(error) {
							console.log("error");
						}
					})// $.ajax
				}
			},error: function(error) {
				console.log("error");
			}
		}) //$.ajax
	}//function chatSave()
	
	//채팅 직원 접속상태 변경하기 
	$("#status-options ul li").click(function(){
	
		$("#profile-img").removeClass(); 			//프로필 이미지(#profile-img)에서 기존에 있는 모든 클래스를 제거합니다.
		$("#status-online").removeClass("active");  //모든 상태 옵션(#status-options ul li)에서 active 클래스를 제거합니다.
		$("#status-away").removeClass("active");
		$("#status-busy").removeClass("active");
		$("#status-offline").removeClass("active");
		$(this).addClass("active");					 //클릭된 상태 옵션(li)에 active 클래스를 추가합니다.
	
		if($("#status-online").hasClass("active")) { //현재 선택된 상태 옵션에 따라 프로필 이미지에 다른 클래스를 추가합니다.
			$("#profile-img").addClass("online");
		} else if ($("#status-away").hasClass("active")) {
			$("#profile-img").addClass("away");
		} else if ($("#status-busy").hasClass("active")) {
			$("#profile-img").addClass("busy");
		} else if ($("#status-offline").hasClass("active")) {
			$("#profile-img").addClass("offline");
		} else {
			$("#profile-img").removeClass();
		};
	
		$("#status-options").removeClass("active");	

		status = $("li.active .status-p").text();
		console.log("status =" + status);
		
		$.ajax({
			url : '../myChat/saveStatus',
			type: 'get',
			data : {status : status},
			success : function(){
				console.log("status 변경성공");
			},
			error: function(error) {
			  console.log("saveStatus error");
			}
		})//$.ajax
	})//$("#status-options ul li").click(function()
	
	//채팅방 리스트 가져오기 
	function getChatList(){
		$.ajax({
			url : '../myChat/getChatList',
			type : 'get',
			success : function(rdata){
				let output = '';
				$(rdata).each(function(index, item) {
				 var status_low = item.STATUS.toLowerCase();
				   output += '<a href="" style="text-decoration: none !important; color: white;"'
				   let href = "../myChat/chatview?ID="+item.ID+"&PROFILE_IMG="+item.PROFILE_IMG+"&USERNAME="+item.USERNAME
    			   let option ="window.open('"+href+"','_blank','width=800,height=600');"
				   output += 'onclick=' + option + '>'
				  if(item.ROOMNUMBER == roomNumber){
				  	output += '<li class="contact active">'
				  }else{
					output += '<li class="contact">'
				  }
					output += 	'<div class="wrap">'
					output +=    '<span class="contact-status '+ status_low +'"></span>'
					output += 	  '<img src="../upload/'+ item.PROFILE_IMG +'" alt="" />'
					output += 	  '<div class="meta">'
					output += 		'<p class="name">'+ item.USERNAME +'</p>'
				  if(item.CHAT_CONTENT.indexOf(";img;") > -1){
					item.CHAT_CONTENT = item.CHAT_CONTENT.substring(0,item.CHAT_CONTENT.length-5);
					console.log(item.CHAT_CONTENT);
				  }else if(item.CHAT_CONTENT.indexOf(";file;") > -1) {
					item.CHAT_CONTENT = item.CHAT_CONTENT.substring(0,item.CHAT_CONTENT.length-6);
					console.log(item.CHAT_CONTENT);
				  }
					output += 		'<p class="preview">'+ item.CHAT_CONTENT +'</p>'
					output += 	  '</div>'
					output += 	'</div>'
					output += '</li>'
					output += '</a>'
				})//$(rdata).each(function(index, item)
			  $("#contacts > ul").append(output)
			},error: function(error) {
				console.log("error");
			  }
		})//$.ajax
	}//function getChatList()
	
	// 파일 업로드 이벤트 처리
	$('#file_input').on('change', function() {
	    var file = $('#file_input')[0].files[0];
	    var blob = new Blob([file]);
	    var reader = new FileReader();
	    console.log("파일업로드 이벤트 =" + JSON.stringify(file));
	    
	    reader.onload = function(event) {
	        var contents = event.target.result;
	        //fileName = $("#file_input").val();
	        //console.log("fileName =" + fileName);
	        var message = {
	            type: "fileUpload", 
	            file : file,
	            roomNumber : roomNumber.toString(), // 방번호 
				sessionId : sessionId, //세션
				userName : $("#chatMyId"). text(), //보낸사람 아이디 
				msg: file.name, // 원본 파일명 
				filetype : file.type // 파일의 타입
	        };
	        console.log("reader.onload 1");
	       ws.send(JSON.stringify(message));
	        console.log("filemessage=" + JSON.stringify(message));
	        console.log("reader.onload 2");
	       
	       
	       ws.send(contents);
	       console.log("filesend함");
	       console.log("filecontents=" + JSON.stringify(contents));
	        console.log("reader.onload 3");
	    };
	     reader.readAsArrayBuffer(blob); 
	     console.log("file =" + JSON.stringify(file));
	}); //$('#file_input').on('change', function()
	     
}) //$(function(){