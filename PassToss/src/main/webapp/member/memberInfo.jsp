<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>


<!DOCTYPE html>

<head>
<meta charset="UTF-8">
<title>회원정보 변경</title>

<style>
	.form-group img {width:20px}
	input:read-only {background-color: #eee;}  
</style>
 <link rel='stylesheet' href="${pageContext.request.contextPath}/css/join.css">
   <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <script src="${pageContext.request.contextPath}/js/joinUpdate.js"></script>
</head>




	<div class='container box_radius15 board_container'>
		  <form name="joinform" method="post" action="updateProcess.net" id="joinform"  enctype="multipart/form-data">
		     <h1>정보수정</h1>
		      <hr/>
		      
		      <b><label for="id">아이디</label></b>
		      <input type="text" name="id" placeholder ="enter id" maxlength="12" value="${memberinfo.id }" readonly>
	
	
		      <b><label for="name">이름</label></b>
		      <input type="text" name="name" placeholder ="enter name" value="${memberinfo.name}" required>
		      
		      
		      
		       <b><label for="phone">휴대폰번호</label></b>
		       <input type="text" placeholder="(-)하이픈을 추가하세요" value="${memberinfo.phone }" name="phone" id="phone">


		       <b><label for='deptno'>부서번호</label></b>
				<input id='email' type='text' name='deptno'  placeholder =deptno value="${memberinfo.deptno }"  required readonly>
		     	<span id ="email_message"></span>
		       
		        <b><label for="email">이메일주소</label></b>
		      	<input type='text' name='email'  placeholder ="enter email" value="${memberinfo.email }"  maxlength="30" required>
		     	<span id ="email_message"></span>
		     	
		     	
		     	
		     
		     
		     		<div style='margin-bottom:30px'>
						<label for="post1">우편번호</label>
				      <input type="text"  name="post1" value="${memberinfo.post }" id="post1" readonly> 
				      <input type="button" value="우편검색" id='postcode' onclick="Postcode()">
			      </div>
			      
			      <label for="address">주소</label>
			      <input type="text"  name="address" id="address" value="${memberinfo.address }">
		     
		     		<div class='form-group'>
						<label for="member_file">
							<img src="image/attach.png" alt="파일첨부">
							프로필 사진
							<span id="filename">${memberinfo.profileImg }</span>
				     		
				     		<span id="showImage">
				     		
				     			<c:if test="${empty memberinfo.profileImg }">
				     				<c:set var ='src' value='image/delete.png'/>
				     			</c:if>
				     			
				     			<c:if test="${!empty memberinfo.profileImg }">
				     				<c:set var ='src' value='memberupload/${memberinfo.profileImg}'/>
				     				<input type="hidden" name="check" value="${memberinfo.profileImg}">
				     			</c:if>
				     			<img src="${src}" width="20px" alt="profile">
				     			
				     		</span>
							
							<input type="file" id="member_file" name="member_file" style='display:none'>
						</label>
						
						
						
				
					
					</div>
		     
		     
		     	<div class='clearfix'>
		     		<button type="submit" class="submitbtn" style='width:100%;margin-top:50px'>수정</button>
		     	</div>
		     	
		     	
		  </form>
  	</div>
  	
  	<script>

  			$('#member_file').change(function(e){

  				const inputfile  = $(this).val().split('\\');
  				const filename = inputfile[inputfile.length - 1];
  				
  				const pattern = /(gif|jpg|jpeg|png)$/i ; 
  				
  	
  				const check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  				console.log(filename);
  				if(check.test(filename)){
  					alert('한글파일명 금지')
  					$("#filename").text("");
  					$(this).val('');
  					$('input[name=check]').val("");
  					
  				}else if(pattern.test(filename)){
  					$("#filename").text(filename);
  					const reader = new FileReader(); 
  					
  					reader.readAsDataURL(e.target.files[0]);
  					reader.onload=function(){ 
  						$("#showImage > img ").attr('src',this.result);
  					}
  				}else{
  					alert('이미지 파일이 아닌 경우 무시됩니다.')
  					$("#filename").text("");
  					$("#showImage > img ").attr('src',"image/delete.png");
  					$(this).val('');
  					$('input[name=check]').val("");
  					
  				}
  		
  		})
  
  	</script>
