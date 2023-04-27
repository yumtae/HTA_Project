$(function(){
	//연락처 검색 
	$('#contactsearch').on('input',function(){
		var searchword = $(this).val();
		
		$.ajax({
			url: '../myChat/contactSearch',
			type: 'get',
			data: {searchword : searchword},
			success: function(rdata){
				$(".contactlist").remove();
				
				 output='';
                $(rdata).each(
    					function(index,item){
    						output += '<div class="dropdown-item d-flex align-items-center contactlist">'
    						output += '<div class="dropdown-list-image mr-3" >'
    						 if (!item.PROFILE_IMG || item.PROFILE_IMG.length == 0){
    						output += '<img class="rounded-circle" src="../upload/profile-default.png">'
    						 } else{
    							 output += '<img class="rounded-circle" src="../upload/'+ item.PROFILE_IMG +'">'
    						 }
    						output += '</div>'
    						output += '<div class="font-weight-bold" style ="vartical-align : middle; width:100%;">'
    						output += '<div class="text-truncate">' + item.USERNAME + '</div>'
    						output += '<div class="small text-gray-500">' + item.COMPANY_NAME + '</div>'
    						output += '</div>'
    						output += '<div style="float: right;"'
    						let href = "../myChat/chatview?ID="+item.ID+"&PROFILE_IMG="+item.PROFILE_IMG+"&USERNAME="+item.USERNAME 
    						let option ="window.open('"+href+"','_blank','width=800,height=600');"
    						output += 'onclick=' +option+'>'
    						output += '<img src="../resources/image/chat.png" style="width:44px; height:43px;"></div>'
    						output += '</div>'
    					})    					
                $('#contactdrop').append(output);
                
			}, error: function(error) {
				console.log("error");
			}
		}) // ajax
	})// $('#contactsearch').on('input',function(){
	
	//검색어 제거
	/*$("#contactdrop").close(function(){
		$("#contactsearch").val() ='';
	})*/
	
	
	//연락처 목록 가져오는 ajax
	$.ajax({
        url : '../myChat/contactList',
        type :'get',
        success : function(rdata){
                output='';
                $(rdata).each(
    					function(index,item){
    						output += '<div class="dropdown-item d-flex align-items-center contactlist">'
    						output += '<div class="dropdown-list-image mr-3" >'
    						 if (!item.PROFILE_IMG || item.PROFILE_IMG.length == 0){
    						output += '<img class="rounded-circle" src="../upload/profile-default.png">'
    						 } else{
    							 output += '<img class="rounded-circle" src="../upload/'+ item.PROFILE_IMG +'">'
    						 }
    						output += '</div>'
    						output += '<div class="font-weight-bold" style ="vartical-align : middle; width:100%;">'
    						output += '<div class="text-truncate">' + item.USERNAME + '</div>'
    						output += '<div class="small text-gray-500">' + item.COMPANY_NAME + '</div>'
    						output += '</div>'
    						output += '<div style="float: right;"'
    						let href = "../myChat/chatview?ID="+item.ID+"&PROFILE_IMG="+item.PROFILE_IMG+"&USERNAME="+item.USERNAME
    						let option ="window.open('"+href+"','_blank','width=800,height=600');"
    						output += 'onclick=' +option+'>'
    						output += '<img src="../resources/image/chat.png" style="width:44px; height:43px;"></div>'
    						output += '</div>'
    					})    					
                $('#contactdrop').append(output);
                
        }, error: function(error){
                console.log("error");
        }
        })//ajax
})