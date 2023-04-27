$(function() {
	// 엔터시 submit 방지
	$('input').keydown(function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
		}
	})

	// 새 회사 만들기
	$("#NewCompany").off("click").on("click", function() {
		$("#join-select").fadeOut(0).addClass("d-none");
		$("#join-info").fadeIn(200).removeClass("d-none");
		$("input[name=belong]").val("1");
		$("input[name=memberType]").val("newCompany");
		console.log($("input[name=memberType]").val());
	})

	// 기존 회사 참여
	$("#joinCompany").off("click").on("click", function() {
		$("#join-select").fadeOut(0).addClass("d-none");
		$("#join-companyURL").fadeIn(200).removeClass("d-none");
		$("input[name=memberType]").val("joinCompany");
		console.log($("input[name=memberType]").val());
	})

	// 기존 회사참여 url 확인 후 -> 아이디 입력창
	$("#goJoinInfo").off("click").on("click", function() {
		$("#join-companyURL").fadeOut(0).addClass("d-none");
		$("#companyInfo").modal("hide");
		$("#join-info").fadeIn(200).removeClass("d-none");

		const title = $("#join-info").find(".join-title");
		const sub = $("#join-info").find(".join-subtitle");

		title.text("회사 계정 만들기");
		sub.text("관리자의 승인 후 서비스 이용 가능합니다.");

		$("#goCompanyInfo").addClass("d-none");
		$("#goMain").removeClass("d-none");
	})

	/*$("#goMain").off("click").on("click", function() {
		//if(option==0){} 직원참여 옵션 비교 후 이동 페이지 나뉨
		console.log("승인대기화면 넘어가기")
		$("#join-info").fadeOut(0).addClass("d-none");
		$("#emailAuth").modal("hide");
		$("#pending").fadeIn(200).removeClass("d-none");
	})*/

	let checkId = false;
	let checkName = false;
	let checkPass = false;

	// 계정@도메인.최상위도메인
	const emailReg = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/
	// 최소 8자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자
	const passReg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,20}$/
	// 20자 이하 한글,영문 사용. 특수문자,공백x
	const nameReg = /^[a-zA-Z가-힣]{1,20}$/

	// 에러메세지 유지 방지 1
	$(".join-input").on("keyup", function() {
		if ($(this).next().attr('class') != 'message') {
			$(this).next().next().text("");
			return;
		}
		$(this).next().text("");
	})

	// 에러메세지 유지 방지 2
	$("select[name=type]").change(function() {
		$(this).next().text("");
	})

	// 아이디, 이름 유효성 검사
	$("input[type=text]").focusout(function() {
		let inputName = $(this).attr('name');

		if (inputName == 'id') { // id 입력창 중복확인 및 유효성 검사
			const id = $(this).val();
			$("#id-message").css("color", "red");

			if (!id.length == 0) {
				if (id.length > 50) {
					$("#id-message").text("50자 이내로 작성하세요.");
					$(this).val('').focus();
					checkId = false;
					return; // ajax 넘어감 방지
				} else if (!emailReg.test(id)) {
					$("#id-message").text("이메일 형식이 올바르지 않습니다.");
					checkId = false;
					return;
				}

				// id 중복검사
				$.ajax({
					url: "idcheck",
					data: { "id": id },
					success: function(result) {
						if (result == 1) {
							$("#id-message").text("사용중인 이메일입니다. 다른 이메일을 입력해주세요.");
							checkId = false;
						} else {
							$("#id-message").css("color", "green").text("사용가능한 이메일 입니다.")
							checkId = true;
						}
					}
				})
			} else {
				$("#id-message").text("");
				checkId = false;
			}

		} else if (inputName == 'username') { // 이름 입력창 유효성 검사
			const name = $(this).val();
			$("#name-message").css("color", "red");

			if (!name.length == 0) {
				if (name.length > 20) {
					$("#name-message").text("20자 이내로 작성하세요.");
					$(this).val("").focus();
					checkName = false;
				} else if (!nameReg.test(name)) {
					$("#name-message").text("한글 및 영문 대 소문자로 입력하세요.(특수기호, 공백 사용 불가)");
					checkName = false;
				} else {
					checkName = true;
				}
			} else {
				$("#name-message").text("");
				checkName = false;
			}
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

	// 이메일 인증 전 유효성 검사
	$("#emailAuth").on('show.bs.modal', function(e) {
		const password = $('input[name=password]').val();
		const passwordRe = $('input[name=passwordRe]').val();
		console.log('이메일 인증전송 전')
		$(".info-input").each(function(index) {
			if ($(this).val().length == 0) {
				let names = ['이메일을', '이름을', '비밀번호를', '비밀번호 확인을'];
				$(this).next().text(names[index] + " 입력하세요.");
			}
		})

		if (password != passwordRe) {
			checkPass = false;
			$("input[name=passwordRe]").next().text("비밀번호가 일치하지 않습니다.");
		}

		if (!checkId || !checkName || !checkPass) {
			e.preventDefault();
			console.log("모달창 x")
		}
	})

	// 개인정보 입력 후 이메일 모달창
	$('#emailAuth').on('shown.bs.modal', function(e) {
		const id = $("input[name=id]").val();
		const joinType = $("input[name=memberType]").val();

		if (joinType == 'joinCompany') {
			$("#goCompanyInfo").addClass('d-none');
			$("#companyJoin").removeClass('d-none');
		}

		$("#authMessage").text("");
		$("#authNumber").val("");
		$(".authInfo").find("span").text(id);

		console.log(id + "이 입력됨");

		authNumSend(id);
	})

	// 인증번호 확인
	$("#goCompanyInfo").off("click").on("click", function() {
		let result = authNumCheck();
		if (result == 1) {
			$("#join-info").fadeOut(0).addClass("d-none");
			$("#emailAuth").modal("hide");
			$("#join-companyInfo").fadeIn(200).removeClass("d-none");
		}
	})

	$("#companyJoin").click(function() {
		let result = authNumCheck();
		if (result == 0) {
			return false;
		}
	})

	// 가입타입 나누기
	/*$(".memberType").each(function(index) {
		let memberType = ["joinCompany", "newCompany"]
		$(this).click(function() {
			$("input[name=memberType]").val(memberType[index]);
			console.log($("input[name=memberType]").val());
		})
	})*/

	let URLCheck = false;
	// 회사 url 중복검사
	$("#companyURL").focusout(function() {

		const url = $(this).val();
		if (url == '') {
			URLCheck = false;
			return;
		}
		$.ajax({
			type: "post",
			url: "checkURL",
			data: { "url": url },
			success: function(result) {
				if (result == 1) {
					$("#urlMessage").text("이미 사용중인 url입니다.");
					URLCheck = false;
				} else {
					console.log("url중복검사 결과 : " + result);
					URLCheck = true;
				}
			}
		})
	})

	// 회사정보입력 유효성 검사
	$("#goJoinPro").click(function() {
		let returnVal = false;
		let check1 = false;
		let check2 = false;
		let check3 = false;

		$(".company-input").each(function(index) {
			let id = $(this).attr("id");
			console.log("id:" + id);
			if ($(this).val() == '') {
				console.log("값 없음");
				let message = ["회사 이름을 입력하세요.", "회사 url을", "회사 업종을 선택하세요."]
				if ($(this).attr("id") == "companyURL") {
					$(this).next().next().text(message[index] + " 입력하세요.");
				} else {
					$(this).next().text(message[index]);
				}
			} else {
				if (id == "companyName") {
					console.log("회사이름 입력됨")
					check1 = true;
				} else if (id == "companyURL") {
					console.log("회사url 입력됨")
					check2 = true;
				} else if (id == "joinType") {
					console.log("회사업종 선택됨")
					check3 = true;
				}
			}
		})

		if (check1 && check2 && check3) {
			$("#ceo_id").attr('name','ceo_id').val($("input[name=id]").val());
			returnVal = true;
		}

		console.log("returnVal:" + returnVal + ", URLCheck:" + URLCheck);
		return (returnVal && URLCheck);
	})

	// 기존회사 url확인
	const url = $("#joinCompanyURL");
	let companyName = '';
	let companyId = $("#companyId");
	$("#joinURL").click(function() {
		console.log("companyInfo 모달 전")
		if (url.val() == '') {
			url.next().next().text('회사 URL을 입력하세요.');
		} else {
			$.ajax({
				type: "post",
				url: "isCompany",
				data: { "url": url.val() },
				success: function(rdata) {
					console.log(rdata);
					if (rdata == '') {
						url.next().next().text('존재하지 않는 url입니다.');
					} else {
						companyName = rdata.company_name;
						companyId.attr("name", "company_id").val(rdata.company_id);
						const logo = rdata.logo;
						console.log("결과확인:" + rdata.logo);
						$("#companyLogo").attr('src','../logo/'+logo);
						$("#companyInfo").modal('show');
					}
				}
			})
		}
	})

	// 기존회사 url 정보 모달
	$("#companyInfo").on('shown.bs.modal', function() {
		$("#comName").text(companyName);
		$("#comUrl").text(url.val());
	})


})

// 인증번호 확인
function authNumCheck() {
	const authNum = $("#authNumber").val();
	let id = $("input[name=id]").val();
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
					if (rdata == 1) {
						result = rdata;
						$("input[name=auth_status]").val("1");
					} else if (rdata == 0) {
						result = rdata;
						$("#authMessage").text("인증번호가 일치하지 않습니다.");
					}
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