$(function() {
	let id = $("input[name=id]").val();
	console.log(id);

	// 프로필사진 변경
	$("#profileImg").on('change', function(e) {
		const file = $('input[name=profile_img]').val();
		const reg = /\.(jpg|jpeg|jpe|png|gif|bmp|dib|webp)$/i;

		if (!reg.test(file)) {
			//alert('프로필 이미지 파일은 jpg, jpeg, jpe, png, gif, bmp, dib, webp 확장자만 허용됩니다.');
			$("#resultMsg").css('width','250px').find('p').text('파일 형식을 확인하세요.');
			$("#resultMsg").fadeIn(0).fadeOut(1500);
			return;
		}

		console.log("넘어오는지 확인")

		let formData = new FormData();
		let profileImg = e.target.files;

		formData.append('profile_img', profileImg[0]);
		$.ajax({
			type: "post",
			url: "../member/modifyProfileImg",
			data: formData,
			processData: false,
			contentType: false,
			success: function(rdata) {
				$("#resultMsg").css('width','250px').find('p').text('프로필사진이 변경되었습니다.');
				$("#resultMsg").fadeIn(0).fadeOut(1500);
				$("#myImg").attr('src', '../upload/' + rdata);
				$("#myProfileImg").attr('src', '../upload/' + rdata);
			}

		})
	});

	// 내 정보 수정 view 보이기
	$("#myInfo").click(function() {
		$(".modify-menus").addClass('d-none');
		$("#myInfoSetting").removeClass('d-none');
	})

	let originalVal = $("#phoneNum").val();
	// 내 정보 수정버튼 클릭
	$("#myInfoModify").click(function() {
		$(this).addClass('d-none');
		$("#phoneNum").attr('disabled', false);
		$(".edit").removeClass('d-none');
		$("#myInfoCancel").removeClass('d-none');
		$("#myInfoSubmit").removeClass('d-none');
	})

	// 내 정보 취소버튼 클릭
	$("#myInfoCancel").click(function() {
		$("#myInfoModify").removeClass('d-none');
		$("#phoneNum").attr('disabled', true);
		$(".edit").addClass('d-none');
		$(this).addClass('d-none');
		$("#myInfoSubmit").addClass('d-none');
		$('.input').val(originalVal);
		$("#validateMsg").text('');
	})

	// 핸드폰번호 정규식
	let phoneReg = /^(010)[0-9]{3,4}[0-9]{4}$/;

	$("#phoneNum").keyup(function() {
		$("#validateMsg").text("");
	})

	// 내 정보 확인버튼 클릭(정보 변경)
	$("#myInfoSubmit").click(function() {
		if (!phoneReg.test($("#phoneNum").val())) {
			$("#validateMsg").text('휴대폰번호 양식이 맞지않습니다.');
			return;
		}

		let member = new FormData();
		member.append('id', id);
		member.append('phone', $("input[name=phone]").val());
		member.append('updateType', $("input[name=updateType]").val());

		$.ajax({
			type: "post",
			url: "../member/updateProfile",
			data: member,
			processData: false,
			contentType: false,
			success: function(result) {
				if (result == 1) {
					$("#resultMsg").find('p').text('내 정보를 수정하였습니다.');
					$("#resultMsg").fadeIn(0).fadeOut(1500);
					$("#myInfoModify").removeClass('d-none');
					$("#phoneNum").attr('disabled', true);
					$(".edit").addClass('d-none');
					$("#myInfoCancel").addClass('d-none');
					$("#myInfoSubmit").addClass('d-none');
					$("#resultMsg").fadeIn(0).fadeOut(1500);
				}
			}

		})
	})

	// 비밀번호 수정 view 보이기
	$("#myPassword").click(function() {
		$(".modify-menus").addClass('d-none');
		$("#myPasswordSetting").removeClass('d-none');
	})

	$("#myPassModify").click(function() {
		$("input[name=password]").attr('disabled', false);
		$("input[name=passwordRe]").attr('disabled', false);
		$(this).addClass('d-none');
		$("#myPassCancel").removeClass('d-none');
		$("#myPassSubmit").removeClass('d-none');
		$(".edit").removeClass('d-none');
	})

	$("#myPassCancel").click(function() {
		$("input[name=password]").attr('disabled', true);
		$("input[name=passwordRe]").attr('disabled', true);
		$(this).addClass('d-none');
		$("#myPassSubmit").addClass('d-none');
		$("#myPassModify").removeClass('d-none');
		$(".edit").addClass('d-none');
		$(".message").text('');
		$("input[type=password]").val('');
	})

	// 최소 8자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자
	const passReg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,20}$/

	$("#myPassSubmit").click(function() {
		const password = $("input[name=password]").val();
		const passwordRe = $("input[name=passwordRe]").val();

		$(".message").text('');

		if (password == '' && passwordRe == '') {
			$("#message").text('비밀번호 및 비밀번호 확인을 입력하세요.');
			return;
		}

		if (!passReg.test(password)) {
			$("#passwordMsg").text("8~20자 영문,숫자,특수문자를 입력하세요.");
			return;
		}

		if (password != passwordRe) {
			$("#passwordReMsg").text("비밀번호가 일치하지 않습니다.");
			return;
		}

		$.ajax({
			type: "post",
			url: "../member/resetPassword",
			data: { "id": id, "password": password },
			success: function(result) {
				if (result == 1) {
					$("#resultMsg").find('p').text('비밀번호가 변경되었습니다.');
					$("#resultMsg").fadeIn(0).fadeOut(1500);
					$("input[name=password]").val('').attr('disabled', true);
					$("input[name=passwordRe]").val('').attr('disabled', true);
					$("#myPassCancel").addClass('d-none');
					$("#myPassSubmit").addClass('d-none');
					$("#myPassModify").removeClass('d-none');
					$(".edit").addClass('d-none');
					$("input[type=password]").val('');
				}
			}
		})

	})

})