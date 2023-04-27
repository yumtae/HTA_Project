$(function() {
	// 엔터시 submit 방지
	$('input').keydown(function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
		}
	})

	// 계정@도메인.최상위도메인
	const emailReg = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/
	// 최소 8자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자
	const passReg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,20}$/

	let checkPass = false;

	// 에러메세지 유지 방지 1
	$(".join-input").on("keyup", function() {
		$(this).next().text("");
	})

	$("#authNumSend").click(function() {
		const id = $("#InputEmail").val();
		if (id == '') {
			$("#InputEmail").next().text("이메일을 입력하세요");
			return;
		}
		if (!emailReg.test(id)) {
			$("#InputEmail").next().text("이메일 형식이 올바르지 않습니다.");
			return;
		}
		result = isId(id);
		console.log(result)
		if (result == 1) {
			$("#emailAuth").modal('show');
		}

	})

	$("#emailAuth").on('shown.bs.modal', function() {
		const id = $("#InputEmail").val();
		$(".authInfo").find("span").text(id);
		authNumSend(id);
	})

	$("#authNumCheck").click(function() {
		let result = authNumCheck();
		console.log('result = ' + result);
		if (result == 1) {
			const id = $("#InputEmail").val();
			console.log("이메일 인증 성공")
			$("#forgotPassword").fadeOut(0).addClass("d-none");
			$("#emailAuth").modal("hide");
			$("#resetPw").fadeIn(200).removeClass("d-none");
			$("#id").val(id);
		}

	})

	// 비밀번호 유효성 검사
	$("input[type=password]").on('keyup', function() {
		let inputName = $(this).attr('name');
		let password = $('input[name=password]').val();
		let passwordRe = $('input[name=passwordRe]').val();

		if (!$(this).val().length == 0) {
			if (inputName == 'password') {
				$(this).next().text("8~20자 영문,숫자,특수문자를 입력하세요.");
				if (passReg.test(password)) {
					$(this).next().text("");
				}
			} else if (inputName == 'passwordRe') {
				$(this).next().text("비밀번호가 일치하지 않습니다.");
				if (password == passwordRe) {
					$(this).next().text("");
					checkPass = true;
				}
			}
		} else {
			$(this).next().text("");
			checkPass = false;
		}
	})

	$("#NewPassword").click(function() {
		$("body").css('height','');
		let password = $('input[name=password]').val();
		let passwordRe = $('input[name=passwordRe]').val();
		const id = $("#InputEmail").val();

		$("input[type=password]").each(function(index) {
			if ($(this).val().length == 0) {
				let names = ['비밀번호를', '비밀번호 확인을'];
				$(this).next().text(names[index] + " 입력하세요.");
				checkPass = false;
			}			
		})

		if (password != passwordRe) {
			checkPass = false;
			$("input[name=passwordRe]").next().text("비밀번호가 일치하지 않습니다.");
		}
		
		if(!checkPass){
			return;
		}

		$.ajax({
			type: "post",
			url: "resetPassword",
			data: { "id": id, "password": password },
			success: function(result) {
				if (result == 1) {
					$("#resetComplete").modal('show');
				}
			}
		})


	})

})

// 아이디 확인
function isId(id) {
	let result;

	$.ajax({
		url: "idcheck",
		data: { "id": id },
		async: false,
		success: function(rdata) {
			console.log(rdata);
			result = rdata;
			if (rdata != 1) {
				$("#InputEmail").next().text("아이디가 존재하지 않습니다.");
			}
		}
	})
	return result;
}

// 인증번호 확인
function authNumCheck() {
	const authNum = $("#authNumber").val();
	const id = $("#InputEmail").val();
	let result;

	console.log($("#authTime-count").text())
	console.log(seconds);

	if (seconds != 0) {
		if (authNum != '') {
			$.ajax({
				type: "post",
				url: "emailCertify",
				data: { "id": id, "authNum": authNum },
				async: false,
				success: function(rdata) {
					if (rdata == 0) {
						$("#authMessage").text("인증번호가 일치하지 않습니다.");
					}
					result = rdata;
				}
			})
			return result;
		} else {
			$("#authMessage").text("인증번호를 입력하세요.");
			return false;
		}
	}
}

// 이메일 인증번호 발송 및 타이머 설정
let seconds = 180;
function authNumSend(id) {
	$.ajax({
		type: "post",
		url: "emailSend",
		data: { "id": id },
		success: function(result) {
			if (result == 1) {
				console.log("인증번호 발송");
				$("#authTime-count").text("3:00")
				seconds = 180;
				timer = setInterval(count_down_timer, 1000);
			} else {
				console.log("인증번호 전송 실패");
			}
		}
	})
}

// 타이머
function count_down_timer() {

	seconds--;

	let min = parseInt((seconds) / 60);
	let sec = seconds % 60;

	if (sec < 10) {
		sec = '0' + sec;
	}
	if (seconds == 0) {
		console.log("정지");
		clearInterval(timer);
		$("#authMessage").text("인증시간이 지났습니다.");
	}

	$("#authTime-count").text(min + ":" + sec);

}