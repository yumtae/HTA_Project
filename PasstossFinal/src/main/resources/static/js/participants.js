$(function(){

	//홈 화면 프로젝트 리스트 가져오는 AJAX
	$.ajax({
		url: '../myChat/participantsList',
		type: 'get',
		success: function(rdata) {
			let output = '';
			$(rdata).each(function(index, item) {
			     
				if (item.AUTH == "MANAGER") {
					output += '<div class="board_comment_box" id="managerList" style="width: 100%;">'
					output += '<div>'
					if (!item.PROFILE_IMG || item.PROFILE_IMG.length == 0){
						output += '<img class="board_comment_profile" src="../upload/profile-default.png">'
					} else {
						output += '<img class="board_comment_profile" src="../upload/' + item.PROFILE_IMG + '">'
					}
					output += '</div>'
					output += '<div style="vertical-align: middle;">'
					output += '<div class="right_box_name">' + item.USERNAME + '</div>'
					output += '<div style="display: inline">' + item.COMPANY_NAME + '</div>'
					output += '</div>'
					output += '<a href="#"  style="float: right;"'
					let href = "../myChat/chatview?ID="+item.ID+"&?PROFILE_IMG="+item.PROFILE_IMG+"&USERNAME="+item.USERNAME
    				let option ="window.open('"+href+"','_blank','width=800,height=600');"
					output += 'onclick=' + option + '>'
					output += '<img src="../resources/image/chat.png" style="width: 50px; height: 50px;"></a>'
					output += '</div>'
				} 
			})	
			  $('#title-manager').after(output)
			  
			output = '';
			
			$(rdata).each(function(index, item) {	
				if(item.AUTH == "MEMBER"){
					
					output += '<div class="board_comment_box" style="width: 100%;">'
					output += '<div>'
					if (!item.PROFILE_IMG || item.PROFILE_IMG.length == 0) {
						output += '<img class="board_comment_profile" src="../upload/profile-default.png">'
					} else {
						output += '<img class="board_comment_profile" src="../upload/' + item.PROFILE_IMG + '">'
					}
					output += '</div>'
					output += '<div style="vertical-align: middle;">'
					output += '<div class="right_box_name">' + item.USERNAME + '</div>'
					output += '<div style="display: inline">' + item.COMPANY_NAME + '</div>'
					output += '</div>'
					output += '<a href="#"  style="float: right;"'
					let href = "../myChat/chatview?ID="+item.ID+"&PROFILE_IMG="+item.PROFILE_IMG+"&USERNAME="+item.USERNAME
    				let option ="window.open('"+href+"','_blank','width=800,height=600');"
					output += 'onclick=' + option + '>'
					output += '<img src="../resources/image/chat.png" style="width: 50px; height: 50px;"></a>'
					output += '</div>'
				}
			})// $(rdata).each(function(index, item)
		   $('#title-member').after(output)
		}, //success: function()
		error: function(error){
              console.log("error");
            } // error: function(error)
	}) //ajax
	
	//전체보기 클릭했을때 모달 창 생성 
	$("#btn-participants").click(function(){
		$("#participantsModal").css("display", "flex");
		
		//전체보기 모달창에서 리스트 가져오기 
		$.ajax({
			url: '../myChat/participantsList',
			type: 'get',
			success: function(rdata) {
				let output = '';
				$(rdata).each(function(index, item) {
					$("#modallist").remove(); 
					
					output += '<div class="board_comment_box" id="modallist" style="width:100%; border-bottom:1px solid #efefef;">'
					output += '<div>'
					if (!item.PROFILE_IMG || item.PROFILE_IMG.length == 0) {
							output += '<img class="board_comment_profile" src="../upload/profile-default.png">'
						} else {
							output += '<img class="board_comment_profile" src="../upload/' + item.PROFILE_IMG + '">'
						}
					output += '</div>'
					output += '<div style="vertical-align: middle;">'
					output += '<div class="right_box_name" style="color:#484242">'+ item.USERNAME +'</div>'
					output += '<div style="display: inline; color:#f9f9f9">' + item.COMPANY_NAME + '</div>'
					output += '</div>'
					output += '<div class="participants-tool" style="float:right;">'
					output += '<div title="더보기" class="participants-tool-button">'
					output += '<div>&#46;&#46;&#46;</div>'
					output += '</div>'
					output += '<div id="participants-list-item-layer" class="LayeMore">'
					output += '<ul class="layer-list" style="display:none;">'
					output += '<li class="layer-item">'
					//로그인한 아이디가 선택한 아이디하고 같은경우 
					output += '<a href="#나가기"></a>'
					//로그인한 아이디가 관리자인 경우 
					output += '<a href="#내보내기"></a>'
					output += '<a href="#관리자로 지정"></a>'
					//로그인한 아이디가 관리자이고 선택한 아이디도 관리자인 경우
					output += '<a href="#관리자 해제"></a>'
					output += '</li></ul></div>' //class="participants-tool-button"
					output += '</div>' // class="participants-tool"
					output += '</div>' //class="board_comment_box"
				})// $(rdata).each(function(index, item)
			
			  $(".participants-window").append(output);
				
			},error: function(error){
           	   console.log("error");
          	}
		})// ajax
	});
	
	//닫기 클릭하면 모달 창 제거 
	$(".participants-close").click(function(){
		$("#participantsModal.participants-overlay").css("display", "none");
	});
	
	//더보기 클릭한 경우
	$("#participantsModal").on('click', '#modallist > div.participants-tool > div.participants-tool-button > div', function() {        		
		//더보기를 클릭하면 수정과 삭제 영역이 나타나고 다시 클릭하면 사라져요 
		$(this).next().toggle();
		
		//클릭 한 곳만 수정 삭제 영역이 나타나도록 합니다.
		$(".comment-tool-button").not(this).next().hide();
	})
	
	//추가로 모달 떠 있을때 다른거 클릭 안되게 만들기  
	
	//모달 제외하고 다른곳 클릭했을 경우 모달창 꺼짐 (선택자 이상해서 안먹음) 
	//$("#participantsModal").click(function(e){
	//	console.log("그 외 영역");
	//	const evTarget = e.target
	//	if(evTarget == $(".participants-overlay")){
	//		$("#participantsModal").css("display", "none");
	//	}
	//});
	
})