
function go(page){
	const limit = $('#lineviewcount').val();
	//const data = `limit=${limit}&state=ajax&page=${page}`;
	const data = {limit:limit, state:"ajax", page:page}
	ajax(data); // ajax라는 이름으로 만든 함수 
}

function setPaging(href, digit){
	let active="";
	let gray="";
	if(href==""){ // href가 빈문자열인 경우
		if(isNaN(digit)){ //이전&nbsp; 또는 다음&nbsp;
			gray = "gray";
		}else {
			active = "active";
		}
	}
	let output = `<li class="page-item ${active}">`;
	//let anchor = "<a class='page-link" + gray + "'" + href + ">" + digit + "</a></li>";
	let anchor = `<a class='page-link ${gray}' ${href}>${digit}</a></li>`;
	output += anchor;
	return output;
}

function ajax(sdata){
	
	console.log(sdata)
	
	$.ajax({
		url : "DeptList.bod",
		data : sdata,
		type : "post",
		dataType : "json",
		cache : false,
		success: function(rdata){ // 테이블 다시 만들어서 출력해야함 
			$("#lineviewcount").val(rdata.limit);
			$("thead").find("span").text("글 갯수 : " + rdata.listcount);
			
			if(rdata.listcount > 0){
				$("tbody").remove();
				let num = rdata.listcount - (rdata.page - 1) * rdata.limit;
				console.log(num);
				let output = "<tbody>";
				$(rdata.noticelist).each(
					function(index, item){
						
						output += '<tr><td><img src="image/notice.png" width="25"></td>';
						output += '<td> [공지사항] </td>';
						const blank_count = item.board_re_lev * 2 + 1;
						let blank = '&nbsp';
						for(let i = 0; i < blank_count; i++){
							blank += '&nbsp;&nbsp;';
						}
						
						let img ="";
						if(item.board_re_lev > 0){
							img="<img src='image/reply.png' width='25px'>";
						}
						
						let subject=item.board_subject;
						if(subject.length>=20){
							subject = subject.substr(0,20) + "..."; //0부터 20개 부분 문자열 추출
						}
						
						output += "<td><div>" + blank + img
						output += ' <a href="DeptDetailAction.bod?num=' + item.board_num + '">'
						output += subject.replace(/</g,'&lt;').replace(/>/g,'&gt;')
									+'</a>[' + item.cnt + ']</div></td>'
						output += "<td><div>" + item.board_name + '</div></td>'
						output += "<td><div>" + item.board_date + '</div></td>'
						output += "<td><div>" + item.board_readcount
								+ '</div></td></tr>'
						
					})
				
				$(rdata.boardlist).each(
					function(index, item){
						output += '<tr><td>' +(num--) + '</td>';
						output += '<td> [게시글] </td>';
						const blank_count = item.board_re_lev * 2 + 1;
						let blank = '&nbsp';
						for(let i = 0; i < blank_count; i++){
							blank += '&nbsp;&nbsp;';
						}
						
						let img ="";
						if(item.board_re_lev > 0){
							img="<img src='image/reply.png' width='25px'>";
						}
						
						let subject=item.board_subject;
						if(subject.length>=20){
							subject = subject.substr(0,20) + "..."; //0부터 20개 부분 문자열 추출
						}
						
						output += "<td><div>" + blank + img
						output += ' <a href="DeptDetailAction.bod?num=' + item.board_num + '">'
						output += subject.replace(/</g,'&lt;').replace(/>/g,'&gt;')
									+'</a>[' + item.cnt + ']</div></td>'
						output += "<td><div>" + item.board_name + '</div></td>'
						output += "<td><div>" + item.board_date + '</div></td>'
						output += "<td><div>" + item.board_readcount
								+ '</div></td></tr>'
						
					})
				output += "</tbody>"
				$('table').append(output)
				
				$(".pagination").empty(); //페이징 처리 영역 내용 제거
				output = "";
				
				let digit = '이전&nbsp;'
				let href = "";
				if(rdata.page > 1){
					href = 'href=javascript:go(' +(rdata.page - 1) + ')';
				}
				output += setPaging(href, digit);
				
				for(let i = rdata.startpage; i <= rdata.endpage; i ++){
					digit = i;
					href="";
					if (i != rdata.page){
						href = 'href=javascript:go(' + i + ')';
					}
					output += setPaging(href, digit); //아래랑 처리하는 내용이 반복이라 setPaging() 메소드 만들어서 사용함
				}
				
				digit='&nbsp;다음&nbsp;';
				href="";
				if(rdata.page<rdata.maxpage){
					href='href=javascript:go('+(rdata.page+1)+')';
				}
				output+=setPaging(href,digit);
				$('.pagination').append(output)
			}
		}
	})
}

$(function(){
	console.log("처음");
	let selectedValue = $("#search_field").val()
		if(selectedValue != '-1')
			$("#viewcount").val(selectedValue);
		else
			selectedValue = 0; // 선택된게 없으면 자동으로 검색어선택 하도록 
			
	const message = ["검색어", "제목", "작성자"]
	$("input").attr("placeholder", message[selectedValue] + " 입력하세요.")
	
	$("#search").click(function(){
		
		if($("#word").val() == ''){
			alert("검색어를 입력하세요");
			$("input").focus();
			return false;
		}
	}); //button click end
	
	$("#viewcount").change(function(){
		selectedValue = $(this).val();
		$("input").val('');
		$("input").attr("placeholder", message[selectedValue] + " 입력하세요");
		
	}); //viewcount change end 
		
	$("#lineviewcount").change(function(){
		go(1);
		console.log("안녕");
	});
})