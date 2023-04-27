$(function(){

	$("#messagesDropdown").click(function(){
		//채팅 topbar 리스트
		$.ajax({
			url : '../myChat/getChatList',
			type : 'get',
			success : function(rdata){
			  let output = '';
			  
			  $(rdata).each(function(index, item) {
			   $("#chatList").remove(); 
			   var status_low = item.STATUS.toLowerCase();
			   
			    output += '<a class="dropdown-item d-flex align-items-center chatlist" id="chatList"'
			    let href = "../myChat/chatview?ID="+item.ID+"&PROFILE_IMG="+item.PROFILE_IMG+"&USERNAME="+item.USERNAME
    			let option ="window.open('"+href+"','_blank','width=800,height=600');"
				output += 'onclick=' + option + '>'
				output +=  '<div class="dropdown-list-image mr-3">'
				output +=   '<span class="contact-status '+ status_low +'"></span>'
				output +=   '<img class="rounded-circle" src="../upload/'+ item.PROFILE_IMG +'" alt="...">'
				output +=  '</div>'
				output +=  '<div class="font-weight-bold" style="width: 100%;">'
				output +=   '<div class="text-truncate oneline" style="vertical-align: middle;">'+ item.USERNAME +'</div>'
				let sub = item.CHAT_DATE; 
				let date = sub.substring(0,10);
				output +=   '<div class="small text-gray-500 oneline" style="vertical-align: middle; float: right;">'+ date +'</div>'
				if(item.CHAT_CONTENT.indexOf(";img;") > -1){
				  item.CHAT_CONTENT = item.CHAT_CONTENT.substring(0,item.CHAT_CONTENT.length-5);
				  console.log(item.CHAT_CONTENT);
				}else if(item.CHAT_CONTENT.indexOf(";file;") > -1) {
				  item.CHAT_CONTENT = item.CHAT_CONTENT.substring(0,item.CHAT_CONTENT.length-6);
				  console.log(item.CHAT_CONTENT);
				}
				output +=   '<div class="small text-gray-500">'+ item.CHAT_CONTENT +'</div>'
				output +=  '</div>'
				output += '</a>'
			  })
			  $("#showdrop2").append(output);
			}
		})//$.ajax
	})//$("#messagesDropdown").click(function()
	
	$('#chat_Search').on('input',function(){
	
		var searchword = $(this).val();
		console.log(searchword);
		//채팅 리스트 검색
		$.ajax({
			url : '../myChat/chatSearch',
			type : 'get',
			data : {searchword : searchword},
			success: function(rdata){
			  $("#chatList").remove();
			  
			  let output = '';
			  $(rdata).each(function(index, item) {
				var status_low = item.STATUS.toLowerCase();
				
				output += '<a class="dropdown-item d-flex align-items-center chatlist" id="chatList"'
				let href = "../myChat/chatview?ID="+item.ID+"&PROFILE_IMG="+item.PROFILE_IMG+"&USERNAME="+item.USERNAME
	    		let option ="window.open('"+href+"','_blank','width=800,height=600');"
				output += 'onclick=' + option + '>'
				output +=  '<div class="dropdown-list-image mr-3">'
				output +=   '<span class="contact-status'+ status_low +'"></span>'
				output +=   '<img class="rounded-circle" src="../upload/'+ item.PROFILE_IMG +'" alt="...">'
				output +=  '</div>'
				output +=  '<div class="font-weight-bold" style="width: 100%;">'
				output +=   '<div class="text-truncate oneline" style="vertical-align: middle;">'+ item.USERNAME +'</div>'
				let sub = item.CHAT_DATE; 
				let date = sub.substring(0,10);
				output +=   '<div class="small text-gray-500 oneline" style="vertical-align: middle; float: right;">'+ date +'</div>'
				output +=   '<div class="small text-gray-500">'+ item.CHAT_CONTENT +'</div>'
				output +=  '</div>'
				output += '</a>'
			  })//$(rdata).each(function(index, item)
			  $("#showdrop2").append(output);
			}
		})// $.ajax
	})//$('#contactsearch').on('input',function()
	
})//$(function()