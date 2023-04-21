<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>사내게시판 답글 달기</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>
<script src="js/jquery-3.6.3.js"></script>
<style>
	.subject{
	    border-color : White;
		border-bottom : solid 1px silver; 
	}
	
	#board_content{
		border-color : White; 
	}
	
	tr.sharing {
		text-align: right;
	}
	
	td#sharing{
		border-color : White;
		border-bottom : solid 1px Silver;
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
   <form action="FreeReplyAction.bof" method="post" name="replyform">
      <input type="hidden" name="board_re_ref"  value="${board.board_re_ref}"> 
      <input type="hidden" name="board_re_lev"  value="${board.board_re_lev}"> 
      <input type="hidden" name="board_re_seq"  value="${board.board_re_seq}">
      <input type="hidden" name="board_name"  value="${board.board_name}">
      
      <h3>사내게시판 답글 작성</h3>
      <div class="write-box">
      <div class="form-group">
    	<label for="board_subject"></label>
    	<textarea name="board_subject" id="board_subject" rows="1"
    			   class="form-control subject" maxlength="100">Re:${board.board_subject}</textarea>
      </div>
      
      <div class="form-group">
    	<label for="board_content"></label>
    	<textarea name="board_content" id="board_content" rows="10"
    			  class="form-control" placeholder="내용을 입력하세요"></textarea>
      </div>
     </div>
      <div class="form-group">
    	<input type="submit" class="btn btn-dark submit" style="float: right" value="등록">
    	<input type ="button" class="btn btn-light cancel" value="취소"	onClick="history.go(-1)">
      </div>
   </form>
  </div>
</body>
</html>