
function resize(obj) {
	  obj.style.height = "1px";
	  obj.style.height = (12+obj.scrollHeight)+"px";
	}
	
	





$(function(){
	
	let filecheck = 1;
	
	
	

	//댓글
	$(document).on("submit",".comment_form",function(){
		var BOARD_NUM = $(this).find('.forcommentnum').val();
		var CONTENT = $(this).find('.forcommenttext').val();
		var ID = $(this).find('.forcommentid').val();
		
		if(CONTENT == "" ){
			alert('내용을 입력해주세요');
			return false;
		}
		var that = this;
		$.ajax({
			type:"post",
			url:"../comment/add",
			data: {'BOARD_NUM':BOARD_NUM,'CONTENT':CONTENT,'ID':ID},
			success:function(rdata){
				if(rdata.result > 0){
					$(rdata.list).each(function(){
						html="";
						html += "<div class='board_comment_box'>"
						html +=	"<div><img class='board_comment_profile' src='../image/profile-default.png'>"
	                    html +=	"            	</div>"
	                    html +=	"            	<div>"
	                    html +=	"            		<div>"+this.id+" <span>"+this.reg_DATE+"</span>  <span><a href='#"+this.num+"' onclick='commentRemove("+this.num+",event.target)'>삭제</a></span> </div>"
	                    html +=	"            		<div>"+this.content+"</div>"
	                    html +=	"            	</div>"
	                    html +=	"            </div>"
						
						$(that).parent().parent().parent('.board_comment_wrap').prepend(html);
						$('.forcommenttext').val('');
					})
						
				
					
				}else{
					alert('에러발생');
				}
			}
		})
	})

	$(document).on('change',".boardfile",function(){
		filecheck = 0;
		fileTextWrite(this);
	})


	$("#dropdownMenuLink .fa-angle-double-up").click(function(){
		$('.card-body').slideUp('fast');
	})
	$("#dropdownMenuLink .fa-angle-double-down").click(function(){
		$('.card-body').slideDown('fast');
	})

	
	
	$(document).on('click',".file_close_btn",function(){
		$(".boardfile").val('');
		$(".boardfilevalue").text('');
		$(this).closest("form").find('input[name="filename"]').val('');
		filecheck = 0;
		$(this).hide();
	})
	
	
	$(document).on("click",".head_board_status_li  span",function(){
		$(this).siblings('span').removeClass('on');
		$(this).addClass("on");
	})
	
	$(document).on("click",".board_projectmanagers_people .fa-window-close",function(){
		$(this).parents('.board_projectmanagers_people').remove();
	})
	

	
	$(document).on("mouseup",  function(e) {
	 	 var container = $(".projectmanager-search-results");
		  var input = $(".board_add_projectmanager");
		  if (!container.is(e.target) && container.has(e.target).length === 0 &&
		      !input.is(e.target)) {
		    container.hide();
		  }
	  
	});
	
	
	
	//담당자 search
	$(document).on("keyup",".board_add_projectmanager",function(event){
		
		var data = $(this).val();
		var resultsContainer = $(this).siblings('.projectmanager-search-results');
		$(resultsContainer).show();
		
	
		    
		    
		var persons = [];
		
		$(this).siblings('.board_projectmanagers_people_wrap').children('.board_projectmanagers_people').each(function() {
		    var id = $(this).find('.projectmanagers_id').text();
		    persons.push({id});
		});
		
		  
		
		$.ajax({
			type:"post",
			url :"../member/getSearchMemberList",
			data: {searchword:data,  persons: JSON.stringify(persons)},
			success:function(rdata){
				
			      resultsContainer.empty(); // 이전 검색 결과 삭제
				
			      for (const obj of rdata) {
			        var username = obj['USERNAME'];
			        
			        var id = obj['ID'];
			        //var companyId = obj['COMPANY_ID'];
			
						
			        var html = '<div class="projectmanager-search-result pointer">';
				      	html += '<div class="board_projectmanagers_people">'
				      	html += '		 <span class="projectmanagers_id hide">' + id + '</span>'
		                html += '		<img src="../image/profile-default.png">'
		                html += '		<b>'+ username +'</b>'
		                html += '		<a href="#"><i class="fa fa-window-close" aria-hidden="true"></i></a>'
		                html += '	</div>'
				    html += '</div>';
			
			        resultsContainer.append(html);
					}


			}
			
		
		
		})

			

	
	});
	
	//담당자 추가 
	$(document).on("click",".projectmanager-search-results .projectmanager-search-result",function(event){
			var html = $(this).html();
			$(this).parent().siblings('.board_projectmanagers_people_wrap').append(html)
			$(this).parent().siblings('.board_add_projectmanager').val('');
			$(this).hide();
	
	})
	
	

		
	//작성의 하위업무 엔터 눌렀을때
	$(document).on("keyup","#head_sub_work_input",function(event){
	
		var $input = $(this);
		
  		if (event.which === 13) {
  			//console.log($input.val());
  				if( $.trim($input.val()) == "" ){
		    		alert('업무를 입력해주세요');
		    		return false;
		    	}
		    	
		    	//하위업무 count 후 입력
		    	writeCountSubwork($input,+1);
		    	
		    	
		        var headSpanBtn =$input.siblings('.com_status_span_style.on').prop('outerHTML');
				var headSubWork =$input.val();
	      		$input.parent('.board_status_write ').siblings(".board_ststus_li_form_list").prepend('<div class="board_status_li">'+ headSpanBtn +headSubWork + '<i class="fa fa-window-close business_list_remove_btn right" aria-hidden="true"></i></div>');
	      		$input.val('');
	      		
	      		 return false;
  			
		  }
		    
		   
		 
	});
	
	//form enter막기
   const elements1 = document.getElementsByClassName('head_more_business_list');
	for (let i = 0; i < elements1.length; i++) {
	  elements1[i].addEventListener("keydown", evt => {
	    if ((evt.keyCode || evt.which) === 13) {
	      evt.preventDefault();
	    }
	  });
	}
	
	const elements2 = document.getElementsByClassName('board_projectmanagers_box');
	for (let i = 0; i < elements2.length; i++) {
	  elements2[i].addEventListener("keydown", evt => {
	    if ((evt.keyCode || evt.which) === 13) {
	      evt.preventDefault();
	    }
	  });
	}
		

	$(document).on("click",".board_add_status_button",function(){
		$(".board_status_write").show();
		$("#head_sub_work_input").val("");
		$(this).hide();
	
	})
	
	
	
	$(document).on("click",".board_status_write .com_status_span_style",function(){
			$(this).siblings('.board_status_toggle').toggle();
	})
	

	$(document).on("click",".board_status_write .board_status_toggle span",function(){
		var clickSpan = $(this);
		var clickHtml = clickSpan.html();
		clickSpan.parent('.board_status_toggle').hide();
		$(this).parent().siblings('.com_status_span_style').remove();
		$(this).parent().parent('.board_status_write').prepend(clickSpan.clone());

	
		
	})
	
	
	$("#head_sub_work_input_close,.update_sub_work_input_close").on("click",function(e){
		$(".board_status_write").hide();
		$("#head_sub_work_input").val("");
		$(".board_add_status_button").show();
	})
	
	
	$(document).on("click",".business_list_remove_btn",function(){

		$(this).parent('.board_status_li').remove();
		//var countSubwork = $(this).parent().parent().find('.board_ststus_li_form_list .board_status_li').length;
		//console.log(countSubwork)

		
		
	
	})
	
	
	
	
	//head 업무 submit  &&  수정 전송 버튼
	
	$(document).on("click",".head_board_submit_btn , .update_board_submit_btn",function(e){
		e.preventDefault();
		

		//작성자
		var writer = $(this.form).find('input[name="WRITER"]').val();
		//제목
		var subject = $(this.form).find('input[name="SUBJECT"]').val();
	
		//내용
		var content = $(this.form).find('textarea[name="CONTENT"]:visible').val();
	
		//프로젝트
		var company_id = $(this.form).find('input[name="COMPANY_ID"]').val();
		
		
		
		
		//진행도
		var progress = $(this).closest("form").find(".progressbar_percent").text();
		if (progress == 0 ){
			progress = 0;
		}
		
		//우선순위
		var priority = $.trim($(this).closest("form").find(".priority_add").text());
		console.log('우선순위 = '+priority)
		if(priority =="우선순위 추가"){
			priority ="";
		}
		
		
	
		//상태
		var status = $(this).closest("form").find(".head_board_status_li span.on").text();
		
		
		//게시판 종류 분기
		var board_division = $(this.form).find('input[name="BOARD_DIVISION"]').val();


		//담당자 
		var people_array = [];
		
		$(this).closest("form").find('.board_projectmanagers_people_wrap .board_projectmanagers_people').each(function(){
		    var id = $(this).find('.projectmanagers_id').text();
		    var username = $(this).find('b').text();
		    people_array.push({id: id, username: username});
		});
		




		if($.trim($(this).closest("form").find(".head_subject").val()) == ""){
			alert("제목을 입력하세요");
			$(this).closest("form").find(".head_subject").focus();
			return false;
			
		}
		
		if($.trim($(this).closest("form").find(".head_content").val()) == ""){
			alert("내용을 입력하세요");
			$(this).closest("form").find(".head_content").focus();
			return false;
			
		}
		
		
		
		
		
		
		 var subboardList = [];
		$(this).parent().find('.board_ststus_li_form_list .board_status_li').each(function() {
		
		  var status = $(this).find("span").text();
	
		  var subworklistdata = $(this).text().replace(status, "").trim();
			  
	
		  if (subworklistdata != ""){
		 	 subboardList.push({status: status, data: subworklistdata});
	 		 }
	 		
		});
			
		var subworklist = JSON.stringify(subboardList);



		// 변수 초기화
		subboardList = [];
			

		
		
		
		var UPLOADFILE = $(this).closest("form").find('input[name="UPLOADFILE"]')[0].files[0];
	

					let  data = new FormData();
					data.append('WRITER',writer);
					data.append('COMPANY_ID',company_id);
					data.append('BOARD_DIVISION',board_division);
					data.append('SUBJECT',subject);
					data.append('CONTENT',content);
					data.append('PROGRESS',progress);
					data.append('STATUS',status);
					data.append('PRIORITY',priority);
					data.append('persons', JSON.stringify(people_array));
					data.append('filecheck',filecheck);
					
					if (UPLOADFILE !== undefined) {
   						data.append('UPLOADFILE',UPLOADFILE);
					}
					
					
					data.append('SUBWORKLIST',subworklist);
					subworklist = ''; //초기화
		
		
		//글 추가일때
		if( $(this).hasClass('head_board_submit_btn') ){
			url = "boardAdd";
		//글 업데이트 일 때
		}else if (  $(this).hasClass('update_board_submit_btn') ) {
			url = "boardUpdate";
			//업데이트용 BOARD_NUM
			var BOARD_NUM = $(this.form).find('input[name="BOARD_NUM"]').val();
			data.append('BOARD_NUM',BOARD_NUM);
		}else{
			alert('추가버튼과 삭제버튼이 아닙니다.')
			return false;
		}
		
		//값확인
		for (let key of data.keys()) {
			console.log(key, ":", data.get(key));
		}

		
		 
		$.ajax({
			type:"post",
			url:url,
			enctype:"multipart/form-data",
			data: data,
			contentType: false,
  			processData: false,
			success:function(rdata){
				
				//작성 완료 시 modal창 치우고 초기화
				console.log(rdata);
				
				 //새글 작성 시 메인에 출력   업데이트와 구분을 줘야할 필요가 있어보임
				 if(url == 'boardAdd' ){
				 	//작성일때는 삽입
				 	$('.headBoardForm').parents('.BoardModal').modal('hide'); 
				 }else{
				 	
				 	 $('.UpdateBoardForm').parents('.BoardModal').modal('hide'); 
				 	 //업데이트때는 게시글 삭제 후 재생성
				 	 console.log('.board_content_comment_box'+rdata.list[0].board_NUM +" 삭제");
				 	 $('.board_content_comment_box'+rdata.list[0].board_NUM).remove();
				 	
				 }
				 
				 getBoardInfo('board_content_comment_box_wrap',rdata.list[0].board_NUM);
				 initBoardWriterAfter();
				 
			}
			
		
		
		})
		
		  
		
	})//head_board_submit_btn클릭시

	
	
	

	
	
	
	$(document).on('click', '.priority_add', function() {
			$(this).siblings(".priority_wrap").toggle();
		})
		
		$(document).on('click', '.priority_wrap div', function() {
			var text = $(this).html();
			var index = $(this).index();
			$target = $(this).parent().siblings('.priority_add');
			$(this).parent().hide();
			$target.html(text);
			$target.removeClass("priority_add_color0 priority_add_color1 priority_add_color2 priority_add_color3");
			$target.addClass("priority_add_color"+index);
			$target.parents('span').css('border-bottom', '0');
		})
		
		
		
		
		
		
		
		$(document).on('click', '.progressbar_wrap .progressbar', function() {
		  var $target_wrap = $(this).closest(".progressbar_wrap");
		  var percent = $(this).children("em").html();
		  var index = $(this).index() + 1;
		  change_progressbar(percent, index ,$target_wrap);
		});
		
		$(document).on('mouseenter', '.progressbar_wrap .progressbar', function() {
		  $(this).children("em").show();
		});
		
		$(document).on('mouseleave', '.progressbar_wrap .progressbar', function() {
		  $(".progressbar_wrap .progressbar").children("em").hide();
		});
						
				
		
		$(document).on('click', '.board_each_dot', function() {
			$(this).siblings('.board_each_menu').toggle();
		})
		
		
	
})//$function


//진행도 숫자출력
function get_progressbar_value(percent){
		var regex = /[^0-9]/g;				
		var PROGRESS = percent.replace(regex, "");
		
		return PROGRESS;
}


//진행도 변경
function change_progressbar(percent,index , $target){
		var PROGRESS = get_progressbar_value(percent);
		$target.siblings(".progressbar_percent").html(PROGRESS);
		$target.find(".progressbar").css("background","#eee")
		
		
		
		for( var i = 0 ; i < index ; i++){
			$target.find(".progressbar").eq(i).css("background","orange");
		}
	
		/* $.ajax({
			url : "progressbarUpdate",
			data:{"PROGRESS" : PROGRESS},
			success : function(rdata){
				
				
				alert("상태가 변경되었습니다");
				
			}
			
		}) */
	
		
	}


//진행도 출력
function writeProgressBar(progress_num){
	
	var output=""

	var color_progress_num =  progress_num / 10;  //3
	
	 
	for(var i = 1 ; i<= color_progress_num ; i++){
			
		output +="<div  class='progressbar rel' style='background:orange'><em>"+i*10+"%</em></div>";
	
	} 
	for(var i = color_progress_num+1 ; i <= 10 ; i++){
			
		output +="<div  class='progressbar rel'><em>"+i*10+"%</em></div>";
	
	}
	
	
	return output;

}



function fileTextWrite(elements) {

		const inputfile = $(elements).val().split('\\');
		$(elements).parent().siblings('.boardfilevalue').text(inputfile[inputfile.length -1 ] );
		$(".file_close_btn").show();
		
}


	
//하위업무 갯수 출력
function writeCountSubwork(elements,indexPlus){
	
	var countSubwork = $(elements).closest('.board_content_box').find('.board_ststus_li_form_list .board_status_li, .more_business_list .board_status_li').length;
	$(elements).closest('.board_content_box').find('.sub_work_count').html(countSubwork+ indexPlus);
}
	
	



	
	
//댓글 삭제	
function commentRemove(num, element){

	if (confirm("정말 삭제하시겠습니까?")){
			
			$.ajax({
				type : "post",
				url :"../comment/delete",
				data : {
					"num" : num
				},
				/*beforeSend: function (jqXHR, settings) {
		           var header = $("meta[name='_csrf_header']").attr("content");
		           var token = $("meta[name='_csrf']").attr("content");
		           jqXHR.setRequestHeader(header, token);
				},*/
				success : function(result){
					if(result == 1){
						alert("삭제되었습니다");
						$(element).parents('.board_comment_box').remove();
					}else{
						alert("삭제실패");
					}
				
				}
			})
			
		}
	
}
	
//게시판삭제
function delete_board(board_num,element){

	if (confirm("정말 삭제하시겠습니까?")){
		$.ajax({
					type : "post",
					url :"../board/boardDelete",
					data : {
						"num":board_num
					},
					success : function(result){
					//console.log(result)
						if(result > 0){
							alert("삭제되었습니다");
							$(element).parents('.board_content_comment_box').remove();
					
						}else{
							alert("삭제실패");
						}
					
					}
				})
		
		}

}



//리스트를 가져와서 출력함수 실행  작성시메인 & 수정창
function getBoardInfo(element_class,board_num){
	output ="";
	target = $("."+element_class)
	$.ajax({
			type : "post",
			url :"../board/boardDetailOne",
			data : {
				"BOARD_NUM":board_num
			},
			success : function(result){
				//console.log(result);
				
				if(result =='' || result == null || result.list.length==0){
					 $('.UpdateBoardForm').parents('.BoardModal').modal('hide'); 
					 alert('삭제된 글입니다.');
				}else{
				
					//중복으로 생성되는 keyup이벤트 제거
					$(document).off("keyup", ".update_sub_work_input");
					  handleResult(target, result);// 후 가져오기
				}
			}
		})
		

}


//리스트를 target에 html으로 출력  
function handleResult(target, result) {
    if (result != null) {
    
    	//작성시 
    	if(target.attr('class') == 'board_content_comment_box_wrap'){
	       output +='<div class="board_content_comment_box board_content_comment_box'+result.list[0].board_NUM+'">'
	       }
      		 output +='<div class="board_content_box">'
		
			output += '<div class="hidden_parent">'
			output += '<input type="hidden" name="WRITER" value="'+ result.list[0].writer +'"  >'
			output += '<input type="hidden" name="BOARD_NUM" value="'+ result.list[0].board_NUM +'"  >'
			output += '<input type="hidden" name="COMPANY_ID" value="'+ result.list[0].company_ID +'"  >'
			output += '<input type="hidden" name="BOARD_DIVISION"  value="'+ result.list[0].board_DIVISION +'"  >'
			output += '</div>'
			output += '<dl class="post-author-info">'
			
			
			
			
            
            
            //작성시
            if(target.attr('class') == 'board_content_comment_box_wrap'){
		     	output += '<dt>'
                output += '    <strong class=" "><span class="board_profile_img"><img src="../image/profile-default.png"> </span> '+result.list[0].writer+'</strong>'
                output += '   <span class="board_date">'+result.list[0].write_DATE+'</span>'
                output += '    <div class="rel right">'
                output += '    	<i class="fa fa-dot-circle board_each_dot pointer "></i>'
                output += '    	<ul class="board_each_menu hide">'
                output += '    		<li><a href="#1" onclick="updateBoardModal('+result.list[0].board_NUM+')" data-toggle="modal" '
                output += '    		data-target="#BoardUpdateModal">'
                output += '    			<i class="fa fa-newspaper"></i> 수정</a>'
                output += '    		</li>'
                output += '    		<li><a href="#1" onclick="delete_board('+result.list[0].board_NUM+',event.target)"><i class="fa fa-trash"></i> 삭제</a></li>'
                output += '    	</ul>'
                output += '    </div>'
                output += '</dt>'
                output += '<dd class="board_title_text_wrap">'
                output += '    <strong>'+result.list[0].subject+'</strong>'
                output += '    <div class="board_title_num">업무번호 '+result.list[0].board_NUM+'</div>'
	          
		    }else{//수정시
	            output += '   <b>게시물 수정</b>'
            	output += '    <dd class="board_title_text_wrap">'
            	output += '       <input type="text" class="board_input_common_style head_subject" name="SUBJECT" placeholder="제목을 입력하세요" value="'+result.list[0].subject+'">'
	         }
	            
            output += '    </dd>'
            output += ' </dl>'
			
			 output += '<div class="board_status_box board_division2_hide">'
             output += '	<i class="fa fa-clock"></i>'
             output += '	<div  class="head_board_status_li board_status_li">'
             output += returnStatusAll(result.list[0].status)
             output += '	</div>'
             output += '</div>'
			
			
			
			//작성시
			if(target.attr('class') == 'board_content_comment_box_wrap'){
			
				 output += '<div id="board_projectmanagers_box" class="board_projectmanagers_box board_division2_hide">'
	             output += '	<i class="fa fa-people-arrows"></i>'
	             output += '	<div class="board_projectmanagers_people_wrap inline"></div>'
	             output += '</div>'
             
			}else{
				 output += '		<div class="board_projectmanagers_box rel board_division2_hide">'
                 output += '      		<i class="fa fa-people-arrows"></i><input type="text" class="board_add_projectmanager"'
                 output += '      		placeholder="담당자 추가" style="display: inline-block;">'
                 output += '      		<div class="projectmanager-search-results abo"></div><div class="board_projectmanagers_people_wrap inline"></div>'
                 output += '       </div><!-- board_projectmanagers_box -->'
			
			}
		
		
		
		
			 output += 	'					<div class="board_attr_more_add board_division2_hide">'
	         output += '                       	<div>'
	         output +=  '                      		<i class="fa fa-calendar"></i>'
	         output +=  '                      		<span>시작일 추가</span>'
	         output +=  '                      	</div>'
	         output +=  '                      	<div>'
	         output +=  '                      		<i class="fa fa-calendar"></i>'
	         output +=  '                      		<span>종료일 추가</span>'
	         output +=  '                      	</div>'
	         output +=  '                      	<div>'
	         output += 	'                       		<i class="fa fa-flag"></i>'
	         output += 	'                       		<div class="rel priority_text">'
	         output += 	'                       			<span class="priority_add">'+returnPriority(result.list[0].priority)+'</span>'
	         output += 	'                       			<div class="priority_wrap">'
	         output += 	'                       				<div><i class="fa fa-arrow-down"></i><span class="margin0">낮음</span></div>'
	         output += 	'                       				<div><i class="fa fa-stop"></i><span class="margin0">보통</span></div>'
	         output += 	'                       				<div><i class="fa fa-arrow-up"></i><span class="margin0">높음</span></div>'
	         output += 	'                       				<div> <i class="fa fa-exclamation"></i> <span class="margin0">긴급</span></div>'
	         output += 	'                       			</div>'
	         output += 	'                       		</div>'
	         output += 	'                       	</div>'
	         output += 	'                       	<div>'
	         output += 	'                       		<i class="fa fa-paragraph"></i>'
	         output += 	'                       		<div class="progressbar_wrap">'
	         output += 	                       	         writeProgressBar(result.list[0].progress )
	         output += 	'                      		</div>'
	         output += 	'                      		<b class="progressbar_percent">'+result.list[0].progress+'</b>%      '		
	         output += 	'                      	</div>'
	         output += 	'                         </div><!-- /board_attr_more_add -->'
			output += 	'					 <hr/>'
	         output += 	'                       <div class="board_content"> '
			if(target.attr('class') == 'board_content_comment_box_wrap'){
				  output += 	'     <div class="board_content">'+result.list[0].content+'</div>'
			}else{
				  output += 	'     <textarea class="board_input_common_style head_content" onkeydown="resize(this)" onkeyup="resize(this)"  name="CONTENT" placeholder="내용을 입력하세요">'+result.list[0].content+'</textarea>'
			}
	         
	         output += 	'                       </div>'
	         output += 	'                       <div class="board_division2_hide">'
	         output += 	'                       	<i class="fa fa-th-list"></i>'
	         output += 	'                       	<span>하위업무 <span class="sub_work_count">'+result.list[0].subBoardList.length+'</span></span> '
	         output += 	'                       </div>'
	         
	         output += 	'                      <div  class="head_more_business_list more_business_list board_division2_hide">'
		          
		          
	         //수정 시 하위업무 추가가능
	         if(target.attr('class') != 'board_content_comment_box_wrap'){
		          output += 	'                       <div class="board_ststus_li_form_list"></div>'
		          output += 	'                      	<div class="board_add_status_button">+ 하위업무 추가</div>'
		          output += 	'                      	<div class="board_status_li board_status_write hide" >'
		          output += 	'                      		<div  class="board_status_toggle abo " >'
			      output += 	'                          		<span class="com_status_span_style board-primary on" >요청</span>'
			      output += 	'                          		<span class="com_status_span_style board-Success on" >진행</span>'
			      output += 	'                          		<span class="com_status_span_style board-Info on" >완료</span>'
			      output += 	'                          		<span class="com_status_span_style board-warning on" >보류</span>'
			      output += 	'                          	</div>'
		          output += 	'                      		<span class="com_status_span_style board-primary on">요청</span>'
		          output += 	'                      		<input class="update_sub_work_input" type="text" placeholder="업무명 입력" value="">'
		          output += 	'                      		<i  class="fa fa-close update_sub_work_input_close"></i>'
		          output += 	'                      	</div>'
		      
	          }else{
	          	 output += 	'                       <div class="board_ststus_li_form_list"></div>'
	          }
	          
	          output += 	'                      </div><!-- /more_business_list -->'
	          output += 	'                   </div><!-- /board_content_box -->'
	          
	          
	          
	          
	          //업데이트시에만 필요한 전송 버튼 & form
	          if(target.attr('class') != 'board_content_comment_box_wrap'){
		            output += 	'              	<label >'
					output += 	'					파일 첨부'
					output += 	'					<img src="../resources/image/attach.png" style="width:30px" alt="파일첨부">'
					output += 	'					<input type="hidden" value="" name="filename">'
				    output += 	'               		<input type="hidden" value="" name="original">'
					output += 	'					<input type="file" name="UPLOADFILE" class="boardfile" style="display:none">'
					output += 	'				</label>'
					output += 	'				<span class="boardfilevalue"></span>'
					output += 	'		      	<i  class="fa fa-window-close file_close_btn" style="display:none" aria-hidden="true"></i>'
		      		output += 	'			 <button type="submit" class="btn btn-primary update_board_submit_btn">전송</button>'
		      		output += 	'			 <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>'
				}else{
				
					

			
					output += '						<div class="board_comment_wrap">'
					output += '								<div class="board_comment_write_wrap">'
				    output += '                            		<div>'
				    output += '                           			<img class="board_comment_profile" src="../image/profile-default.png">'
				    output += '                           		</div>'
				    output += '                           		<div class="board_comment_input_wrap">'
				    output += '                           			<form  class="inline comment_form" onsubmit="return false">'
				    output += '                          				<input type="hidden" class ="forcommentnum" value="'+result.list[0].board_NUM+'">'
				    output += '                          				<input type="hidden" class ="forcommentid"  value="testid3">'
					output += '                              			<input type="text" class ="forcommenttext" placeholder="Enter클릭시 입력" >'
				    output += '                          			</form>'
				    output += '                          		</div>'
					output += '                         	</div>'
				    output += '                         </div>'
				}
			
				//작성시 
		    	if(target.attr('class') == 'board_content_comment_box_wrap'){
			       output +='</div>'
			       }
			
		//삽입
        $(target).prepend(output);
      
	      var test1 = 0 ;
	      	$(document).on("keydown", ".update_sub_work_input", function(event) {
			  if (event.which === 13) {
			    event.preventDefault();
			  }
			});
			      	 var test1 = 0 ;
	      	$(document).on("keyup", ".update_sub_work_input", function(event) {
	      		test1++
	      		
	      		if (event.which === 13) {
	      				var $input = $(this);
	      				//console.log($input.val())
	      				if( $input.val() == "" ){
				    		alert('업무를 입력해주세요');
				    		return false;
				    	}
				    	
				    	//하위업무 count 후 입력
				    	writeCountSubwork($input,0);
				    	
				    	
				        var headSpanBtn =$input.siblings('.com_status_span_style.on').prop('outerHTML');
						var headSubWork =$input.val();
			      		$input.parent('.board_status_write ').siblings(".board_ststus_li_form_list").prepend('<div class="board_status_li">'+ headSpanBtn +headSubWork + '<i class="fa fa-window-close business_list_remove_btn right" aria-hidden="true"></i></div>');
			      		$input.val('');
			      		
			      		 return false;
	      			
				  }
		  	});
		 
		result.list[0].projectManagersList.forEach(function(magers) {
	     $(target).find('.board_projectmanagers_people_wrap').append('<div class="board_projectmanagers_people">'
					+				                                		'<span class="projectmanagers_id hide">'+magers.id+'</span>'
					+				                                		'<img src="/passtoss/image/profile-default.png">'
					+				                                		'<b>'+magers.username+'</b>'
					+				                                	'<a href="#"><i class="fa fa-window-close hide" aria-hidden="true"></i></a></div>');
        });

        result.list[0].subBoardList.forEach(function(subBoard) {
            $(target).find('.board_ststus_li_form_list').append("<div class='board_status_li'>"+ returnStatus(subBoard.status) 
                +  subBoard.subject +"<a href='#1' onclick='subWorkRemove("+ subBoard.board_NUM 
                + ",event.target) '><i class='fa fa-window-close business_list_remove_btn right' aria-hidden='true'></i> </div>");
        });

        if(result.list[0].file_NAME != null ){
            $(target).find('input[name="filename"]').val(result.list[0].file_NAME);
            $(target).find('input[name="original"]').val(result.list[0].original_FILE_NAME);
            $(target).find('.boardfilevalue').text(result.list[0].original_FILE_NAME );
            $(target).find(".file_close_btn").show();
           
        }else{
        	 $(target).find(".board_file_form").hide();
        }

        if(result.list[0].board_DIVISION != 2 ){
            $('.board_division2_hide').hide();
        }else{
        	$('.board_division2_hide').show();
        }
        
     
			      
			      
        
        
    } else {
        alert("상세보기 실패");
    }
}//값 한개 가져오기 html 


//업데이트 불러올때 append를 사용하는데 초기화 하기
function initRemoveBoardWriteAfter(target){
		$(target).children("*").remove();
        
}

//글 작성완료 시 게시글 전부다 빈칸으로 만들기
function initBoardWriterAfter(){
	$target = $('.headBoardForm');
	$target.find('.head_subject').val('');
	$target.find('.head_content').val('');

}




//수정창 띄우기
function updateBoardModal(board_num){
	initTarget = 'UpdateBoardForm';
	$('.'+initTarget).find('.board_projectmanagers_people .fa-window-close').show();
	initRemoveBoardWriteAfter($('.'+initTarget));//초기화
	getBoardInfo('UpdateBoardForm',board_num);
}



//진행상태 - 수정창 & 메인페이지 출력
function returnStatusAll(STATUS){
	var output='';
	output += '<span class="com_status_span_style board-primary '+ (STATUS == '요청' ? 'on' : '' )+' ">요청</span>'
	output += '<span class="com_status_span_style board-Success '+ (STATUS == '진행' ? 'on' : '') +'">진행</span>'
	output += '<span class="com_status_span_style board-Info '+ (STATUS == '완료' ? 'on' : '') +'">완료</span>'
	output += '<span class="com_status_span_style board-warning '+ (STATUS == '보류' ? 'on' : '') +'">보류</span>'

	return output ;
}









//메인 & 하위업무페이지 출력
function returnPriority(PRIORITY){
	var text = "-"
	if( PRIORITY =="낮음"){
		text = '<span class="priority_add_color0"><i class="fa fa-arrow-down" aria-hidden="true"></i>낮음</span>';
	}else if(PRIORITY =="보통"){
		text = '<span class="priority_add_color1"><i class="fa fa-stop" aria-hidden="true"></i>보통</span>';
	}else if(PRIORITY =="높음"){
		text = '<span class="priority_add_color2"><i class="fa fa-arrow-up" aria-hidden="true"></i>높음</span>';
	}else if(PRIORITY =="긴급"){
		text = '<span class="priority_add_color3"><i class="fa fa-exclamation" aria-hidden="true"></i>긴급</span>';
	}

	return text;
	
}	

function returnStatus(status){
	var text = "-"
	if( status =="요청"){
		text = '<span class="com_status_span_style board-primary on">요청</span>';
	}else if(status =="진행"){
		text = '<span class="com_status_span_style board-success on">진행</span>';
	}else if(status =="완료"){
		text = '<span class="com_status_span_style board-Info on">완료</span>';
	}else if(status =="보류"){
		text = '<span class="com_status_span_style board-warning on">보류</span>';
	}

	return text;

	
}	


//하위업무 삭제
function subWorkRemove(num, element){

	if (confirm("정말 삭제하시겠습니까?")){
			
			$.ajax({
				type : "post",
				url :"../board/boardDeleteOne",
				data : {
					"BOARD_NUM" : num
				},
				/*beforeSend: function (jqXHR, settings) {
		           var header = $("meta[name='_csrf_header']").attr("content");
		           var token = $("meta[name='_csrf']").attr("content");
		           jqXHR.setRequestHeader(header, token);
				},*/
				success : function(result){
					if(result == 1){
						alert("삭제되었습니다");
						writeCountSubwork(element,-1);
						$(element).parents('.board_status_li').remove();
					}else{
						alert("삭제실패");
					}
				
				}
			})
			
		}
	
}


function readalarmlog(ID){
	$('#alarmbadge').remove();
	getalarmlog(ID);
	$.ajax({
		type:"post",
		url:"../board/readalarmlog",
		data: {'ID':ID},
		success:function(rdata){
		}
		
	})
}


function getalarmlog(ID){

	$.ajax({
		type:"post",
		url:"../board/getalarmlog",
		data: {'ID':ID},
		success:function(rdata){
		//console.log(rdata)
			$("#getalarmpos").html('')
			var output = '';
			var noreadalarmcnt = 0 ;
			if(rdata.list.length > 0){
			
				$('#alarmbadge').show();
				$('#alarmcount').html(rdata.list.length);
				
			
			
				$(rdata.list).each(function(){
					if(this.READ_YN ==0){
						background_style ="style='background-color: #edf2ff'";
						noreadalarmcnt ++;
					}else{
						background_style="style='background-color: #fff'";
					}
				
					output += '<a href="#1" '+background_style+' onclick="updateBoardModal('+this.BOARD_NUM+')" data-toggle="modal" data-target="#BoardUpdateModal" class="dropdown-item d-flex align-items-center">'
					output += '		<div class="mr-3">'
					output += '			<div class="icon-circle style="background:#eee">'
					output += '				<img src="../image/profile-default.png" style="width:30px">'
					output += '			</div>'
					output += '		</div>'
					output += '		<div>'
					output += '			<div class="small text-gray-500">'+this.WRITE_DATE+'</div>'
					output += '			<span class="font-weight-bold">'+this.USERNAME+'님의 글 등록</span><br/>'
					output += '			<span>제목 : '+this.SUBJECT+'</span>'
					output += '		</div>'
					output += '	</a>'
				})
				$("#getalarmpos").html(output)			
				if(noreadalarmcnt > 0 ){
					$('#alarmbadge').remove();
					$('#alertsDropdown').append('<span id="alarmbadge" class="badge badge-danger badge-counter" ><span id="alarmcount">'+noreadalarmcnt+'</span>+</span>');
				}else{
					$('#alarmbadge').remove();
				};
				
			}	
			
		}
		
	})
}		


	


