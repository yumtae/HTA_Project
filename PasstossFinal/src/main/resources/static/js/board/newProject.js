$(function(){
	$("#create").click(function(){
		const projectName = $("input[name=project_name]");
		
		if(projectName.val() == ''){
			projectName.next().text("프로젝트 명을 입력하세요.");
			return false;
		}
	})
})