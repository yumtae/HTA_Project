$(function() {
	const originalCompanyName = $("input[name=company_name]").val();
	const originalURL = $("input[name=url]").val();

	let companyNameCheck = false;
	let urlCheck = false;
	let logoCheck = true;

	// 회사명 유효성 검사	
	const companyReg = /^[a-zA-Z가-힣]{1,10}$/;

	$("input[name=company_name]").focusout(function() {
		const companyName = $(this).val();

		if (companyName == '') {
			$("#name-message").text('회사명을 입력하세요.');
			companyNameCheck = false;
			return;
		}

		if (!companyReg.test(companyName)) {
			$("#name-message").text('회사명은 영어 대소문자, 한글로 10자 이내로 작성하세요(특수문자X).');
			companyNameCheck = false;
			return;
		}

		$("#name-message").text('');
		companyNameCheck = true;
	})

	// 로고 유효성 검사
	$("input[name=logoFile]").on("change", function(event) {
		$("#logo-message").text('');
		let file = event.target.files[0];

		let isImage = isImageFile(file);
		if (isImage == false) {
			$("#logo-message").text('로고는 png, jpg, jpeg 확장자만 가능합니다.');
			logoCheck = false;
			return;
		}

		let size = isOverSize(file);
		if (size == true) {
			$("#logo-message").text('로고는 최대 500kb 까지 가능합니다.');
			logoCheck = false;
			return;
		}

		let reader = new FileReader();

		reader.onload = function(e) {
			$("#companyLogo").attr('src', e.target.result)
		}

		reader.readAsDataURL(file);
		logoCheck = true;
	})

	// 로고 삭제
	$("#deleteLogo").click(function() {
		if (confirm('로고를 삭제하시겠습니까?')) {
			const companyId = $("input[name=company_id]").val();
			$.ajax({
				type: "post",
				url: "deleteLogo",
				data: { "companyId": companyId },
				success: function() {
					$("#companyLogo").attr('src', '../logo/blank.jpg');
					$("#deleteLogo").addClass('d-none');
				}
			})
		}

	})

	// url 유효성 검사
	const urlReg = /^[a-zA-Z]{1,10}$/;

	$("input[name=url]").focusout(function() {
		$("#url-message").text('');
		const url = $(this).val();

		if (url == originalURL) {
			urlCheck = true;
			return;
		}

		if (url == '') {
			$("#url-message").text('url을 입력하세요.');
			urlCheck = false;
			return;
		}

		if (!urlReg.test(url)) {
			$("#url-message").text('url은 영어 대소문자로 10자 이내로 작성하세요(특수문자X).');
			urlCheck = false;
			return;
		}

		$.ajax({
			type: "post",
			url: "../member/checkURL",
			data: { "url": url },
			success: function(result) {
				if (result == 1) {
					$("#url-message").text('이미 사용중인 url입니다.');
					urlCheck = false;
				} else {
					urlCheck = true;
				}
			}
		})
	})

	// 회사 정보 저장
	$("#company-info-form").submit(function() {

		const companyName = $("input[name=company_name]").val();
		const url = $("input[name=url]").val();

		if (companyName == originalCompanyName) {
			companyNameCheck = true;
		}

		if (url == originalURL) {
			urlCheck = true;
		}

		console.log('회사명 유효성 검사:' + companyNameCheck);
		console.log('url 유효성 검사:' + urlCheck);
		console.log('로고 유효성 검사:' + logoCheck);

		if (!companyNameCheck || !urlCheck || !logoCheck) {
			return false;
		}

	})

})

function isImageFile(file) {

	let ext = file.name.split(".").pop().toLowerCase(); // 파일명에서 확장자를 가져온다. 

	return ($.inArray(ext, ["jpg", "jpeg", "png"]) === -1) ? false : true;
}

function isOverSize(file) {

	let maxSize = 512000; // 500KB로 제한 

	return (file.size > maxSize) ? true : false;
}