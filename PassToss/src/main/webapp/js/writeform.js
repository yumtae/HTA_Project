$(document).ready(function(){
	
	$("#upfile").change(function(){
		console.log($(this).val())	//C:\fakepath\fox.png
		const inputfile = $(this).val().split('\\'); //위에 가져온 것중에 fox.png만 뽑아내는 작업 
		$("#filevalue").text(inputfile[inputfile.length - 1]);
	});
	
	// submit 버튼 클릭할 때 이벤트 부분 
	$("form[name=boardform]").submit(function(){
		
		if($.trim($("#board_subject").val()) == ""){
			alert("제목을 입력하세요");
			$("#board_subject").focus();
			return false;
		}
		
		if($.trim($("#board_content").val()) == ""){
			alert("내용을 입력하세요");
			$("#board_content").focus();
			return false;
		}
	}); // submit end
}); // ready() end 