function go(page) {
	const limit = $("#viewcount").val();
	const category = $("#category_val").val();
	//const data = `limit=${limit}&state=ajax&page=${page}&category=${category}`;
	const data = { limit: limit, state: "ajax", page: page, category: category }
	ajax(data, category);
}

function setPaging(href, digit) {
	let active = "";
	let gray = "";
	if (href == "") {
		if (isNaN(digit)) {
			gray = "gray";
		} else {
			active = "active";
		}
	}

	let output = `<li class="page-item ${active}">`;
	let anchor = `<a class='page-link ${gray}' ${href}>${digit}</a></li>`;
	output += anchor;
	return output;

}


function ajax(sdata, category) {
	console.log("ajax(sdata)");
	console.log(sdata, category);

	$.ajax({
		type: "POST",
		url: "memberBoardList.net",
		data: sdata,
		dataType: "json",
		cache: false,
		success: function(data) {
			$("#viewcount").val(data.limit);
			$("thead").find("span").text("글 개수 : " + data.listcount)

			if (data.listcount > 0) {
				$("tbody").remove();
				let num = data.listcount - (data.page - 1) * data.limit;
				console.log(num);
				let output = "<tbody>";
				$(data.boardlist).each(
					function(index, item) {

						output += '<tr><td><input type="checkbox" class="select" value="' + item.board_num + '"></td>'
						output += '<td>' + (num--) + '</td>'
						output += '<td>'
						console.log('notice = ' + item.board_notice + ' re_lev = ' + item.board_re_lev);
						if (item.board_notice == 0) {
							output += '[공지사항]'
						} else if (item.board_re_lev == 0) {
							output += '[게시물]'
						} else if (item.board_re_lev == 1) {
							output += '[답글]'
						} else if (item.board_re_lev == 2) {
							output += '[답글]'
						}
						output += '</td>'

						let subject = item.board_subject;
						if (subject.length >= 20) {
							subject = subject.substr(0, 20) + "...";
						}
						if (category == 0) {
							output += '<td><div><a class="board_view" href="FreeDetailAction.bof?num=' + item.board_num + '">'
						} else if (category == 1) {
							output += '<td><div><a class="board_view" href="DeptDetailAction.bof?num=' + item.board_num + '">'
						}

						output += subject.replace(/</g, '&lt;').replace(/>/g, '&gt;')
							+ '</a>[' + item.cnt + ']</div></td>'
						output += '<td><div>' + item.board_name + '</td></div>'
						if(category==1){
							output += '<td><div>'+item.board_deptno+'</td></div>'
						}
						output += '<td><div>' + item.board_date + '</td></div>'
						output += '<td><div>' + item.board_readcount + '</td></div>'

					}//function(index,item)
				); //each

				output += "</tbody>"
				$('table').append(output); //테이블 완성

				$(".pagination").empty();
				output = "";

				let digit = '이전&nbsp;'
				let href = "";
				if (data.page > 1) {
					href = 'href=javascript:go(' + (data.page - 1) + ')';
				}
				output += setPaging(href, digit);

				for (let i = data.startpage; i <= data.endpage; i++) {
					digit = i;
					href = "";
					if (i != data.page) {
						href = 'href=javascript:go(' + i + ')';
					}
					output += setPaging(href, digit);
				}

				digit = '&nbsp;다음&nbsp;';
				href = "";
				if (data.page < data.maxpage) {
					href = 'href=javascript:go(' + (data.page + 1) + ')';
				}
				output += setPaging(href, digit);
				$('.pagination').append(output);

               console.log("3");

			} //if(data.listcount > 0)

		},//success:function 
		error: function() {
			console.log("에러");
		}

	}) //ajax end


}//function end


$(function() {
	if ($("#category_val").val() == 0) {
		$(".category li").eq(0).addClass('active');
	} else if ($("#category_val").val() == 1) {
		$(".category li").eq(1).addClass('active');
	}

	$("#viewcount").change(function() {
		console.log("시작");
		go(1);		
	})

	$("button[name=searchbutton]").click(function() {
		//검색어 공백 유효성 검사합니다.
		if ($("input[name=search_word]").val() == '') {
			alert("검색어를 입력하세요");
			$("input[name=search_word]").focus();
			return false;
		}
	})//button click

	$(".selectAll").click(function() {//전체 체크
		if ($(this).is(":checked"))
			$(".select").prop("checked", true);
		else
			$(".select").prop("checked", false);
	})

	$(".delete").on("click", function() {//액션-삭제 유효성검사
		if (!$(".select").is(":checked")) {
			alert('삭제할 게시물을 선택하세요.');
			return false;
		} else {
			const authorize = confirm('선택한 게시물을 삭제하시겠습니까?');
			if (!authorize) {
				alert('취소하였습니다.');
				return false;
			} else {
				console.log($(".select:checked").length);
				let hidden = '';
				$(".select:checked").each(function(index, item) {
					hidden += "<input type='hidden' name='select' value='" + $(this).val() + "'>"
				})
				$(".delete").append(hidden);
				console.log(hidden)
			}
		}
	})
})