$(function() {
	let selectedValue = $("#search_field").val();

	if (selectedValue != '-1')
		$("#select_value").val(selectedValue);
	else
		selectedValue = 0;

	console.log(selectedValue)

	if ($("#category_val").val() == 0) {
		$(".category li").eq(0).addClass('active');
	} else if ($("#category_val").val() == 1) {
		$(".category li").eq(1).addClass('active');
	}

	const message = ["아이디", "이름", "부서번호"];
	$("input").attr("placeholder", message[selectedValue] + " 입력하세요");

	$("button[name=searchbutton]").click(function() {
		//검색어 공백 유효성 검사합니다.
		if ($("input[name=search_word]").val() == '') {
			alert("검색어를 입력하세요");
			$("input[name=search_word]").focus();
			return false;
		}

		const word = $(".input-group input").val();
		console.log(word);
		if (selectedValue == 2) {
			const pattern = /^[1-4]{1}0$/;
			if (!pattern.test(word)) {
				alert("알맞는 부서번호를 입력하세요(10~40)");
				$(".input-group input").val('');
				return false;
			}
		}
	})//button click

	$("#select_value").change(function() {
		selectedValue = $(this).val();
		$("input[name=search_word]").val('');
		$("input[name=search_word]").attr("placeholder", message[selectedValue] + " 입력하세요");
	}) //$("#viewcount").change

	$("#selectAll").click(function() {//전체 체크
		if ($(this).is(":checked")) {
			$(".select").prop("checked", true);
		} else {
			$(".select").prop("checked", false);
		}
	})

	$(".authorize").on("click", function() {//액션-권한수정 유효성검사
		if (!$(".select").is(":checked")) {
			alert('권한변경 할 회원을 선택하세요.');
		} else {
			$("#modal2").modal("show");
		}
	})

	$(".delete").on("click", function() {//액션-삭제 유효성검사
		if (!$(".select").is(":checked")) {
			alert('삭제할 회원을 선택하세요.');
			return false;
		} else {
			const authorize = confirm('선택한 회원정보를 삭제하시겠습니까?');
			if (!authorize) {
				alert('취소하였습니다.');
				return false;
			} else {
				console.log($(".select:checked").length);
				let hidden = '';
				$(".select:checked").each(function(index, item) {
					hidden += "<input type='hidden' name='select' value='" + $(this).val() + "'>"
				})
				$("#delete").append(hidden);
			}
		}
	})

	$("#modal1").on('show.bs.modal', function(e) {
		let id = $(e.relatedTarget).data('id');
		$.ajax({
			url: 'AdminMemberInfo.net',
			data: {
				id: id
			},
			type: 'post',
			datatype: 'json',
			success: function(rdata) {
				let profileImg="";
				if(rdata.profileImg == null){
					profileImg = "image/profile.png";
				}else{
					profileImg = "memberupload/"+rdata.profileImg;
				}					
				
				$("#idInfoLabel").html('<img src="'+profileImg+'" id="profileImg">&nbsp;'+rdata.id + ' 회원정보');

				let output = "";
				output += "<table class='table table-bordered'>"
					+ "<tr><th>아이디</th><td>" + rdata.id + "</td></tr>"
					+ "<tr><th>비밀번호</th><td>" + rdata.password + "</td></tr>"
					+ "<tr><th>이름</th><td>" + rdata.name + "</td></tr>"
					+ "<tr><th>주민번호</th><td>" + rdata.jumin + "</td></tr>"
					+ "<tr><th>부서번호</th><td>" + rdata.deptno + "</td></tr>"
					+ "<tr><th>이메일</th><td>" + rdata.email + "</td></tr>"
					+ "<tr><th>연락처</th><td>" + rdata.phone + "</td></tr>"
					+ "<tr><th>주소</th><td>" + rdata.address + "</td></tr>"
					+ "<tr><th>회원등급</th><td> 준회원 </td></tr>"
					+ "<tr><th>가입일자</th><td>" + rdata.joindate + "</td></tr>";
				$(".modal-body").html(output);
			}
		})
	})

	$("#modal2").on('show.bs.modal', function() {
		console.log($(".select:checked").length);
		let hidden = '';
		$(".select:checked").each(function(index, item) {
			hidden += "<input type='hidden' name='select' value='" + $(this).val() + "'>"
		})
		console.log(hidden);
		output = "";
		output += "<form action='AdminAccess.net' method='post' id='authorize'>"
			+ "<ul>"
			+ "<li><label><input type='radio' name='authority' value='0'>&nbsp;준회원</label></li>"
			+ "<li><label><input type='radio' name='authority' value='1'>&nbsp;정회원</label></li>"
			+ "</ul>"
			+ hidden
			+ "<button type='submit' class='btn btn-primary' id='authorizebtn'>권한설정</button>"
			+ '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>'

			+ "</form>"
		$(".modal-body").html(output);
	})

	$("#authorizebtn").on("click", function() {
		if (!$("input:radio[name=authority]").is(":checked")) {
			alert("설정할 권한을 선택하세요");
			return false;
		}
	})

})



