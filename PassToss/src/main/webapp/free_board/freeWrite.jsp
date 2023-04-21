<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사내게시판 글쓰기</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>
<script src="js/writeform.js"></script>
<script src="js/jquery-3.6.3.js"></script>
<style>
	.subject{
	    border-color : White;
		border-bottom : solid 1px silver; 
	}
	
	select {
	  padding: 1.5;
      margin: 5;
      margin-left: 10;
	}
	
	#upfile{display:none}
	
	img.fileimg{
	  width:20px;
	  margin-left : 10px;	
	}
	
	#board_content{
		border-color : White; 
		border-top : solid 1px silver;
	}
	
	.submit{
	  padding: 0.275rem 0.75rem;
	  /*border-color: black; border테두리*/
      border-radius: 1;
      margin: 5;
	}
	
	.cancel{
	  padding: 0.275rem 0.75rem;
	  border-color:#b6b2b2;
      border-radius: 1;
      margin: 5;
	}
	
	.cancel:hover {
	  padding: 0.275rem 0.75rem;
	  border-color:#585a5c;
      border-radius: 1;
      margin: 5;
	}
	
	.container {
	 width: 70%
	}
	
	textarea{
	 resize: none;
	}
	
	h3{
	  font-weight: bold;
	  padding : 10;
	  text-align : center;
	}
	
	div.form-group{
		padding : 5;
	}
	
	div.write-box{
		border : solid 1px silver;
		margin : 3px;
	}
</style>
</head>
<body>

<div class='container box_radius15 board_container'>
    <form action="BoardFreeAdd.bof" method="post" enctype="multipart/form-data" name="boardform">
    <input type="hidden" id="loginid" value="${id}" name="loginid">
    	<h3>사내게시판 글쓰기</h3>
    	<div class="from-group">
    	   <c:if test="${id !='admin'}">
    		<select id="board_notice" name="board_notice"> 
				<option value="1">게시물</option>
			</select>
		   </c:if>
		   <c:if test="${id=='admin'}">
		     <select id="board_notice" name="board_notice">
				<option value="0">공지사항</option>
				<option value="1" selected>게시물</option>
			 </select>
		   </c:if> 
			
    	</div>
    	<div class="write-box">
    	<div class="form-group">
    		<label for="board_subject"></label>
    		<input name="board_subject" id="board_subject" maxlength="100"
    			   type="text" class="form-control subject"
    			   placeholder="제목을 입력하세요">
    	</div>
    	<div class="form-group">
    		<label>
    		 	<img src = "image/file.png" alt="파일첨부" class="fileimg">
    			<input type="file" id="upfile" name="board_file">
    		</label>
    		<span id="filevalue"></span>
    	</div>
    	<div class="form-group">
    		<label for="board_content"></label>
    		<textarea name="board_content" id="board_content" rows="10"
    			   class="form-control" placeholder="내용을 입력하세요"></textarea>
    	</div>
       </div>
    	<div class="form-group">
    		<button type=submit class="btn btn-dark submit" style="float: right">등록</button>
    		<button type=reset class="btn btn-light cancel" onClick="history.go(-1)">취소</button>
    	</div>
    </form>
</div>
</body>
</html>