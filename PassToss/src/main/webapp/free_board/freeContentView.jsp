<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사내게시판 글 내용 보기</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>
<script src="js/view.js"></script>
<script src="js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="css/view.css" type="text/css">
<style>
	.update {
		margin : 5;
	}
	
	.delete{
		margin : 5;
	}
	
	.reply{
	  padding: 0.275rem 0.25rem;
	}
	
	.prev{
	   padding: 0.275rem 0.25rem;
	}
	
	.list{
	   padding: 0.275rem 0.25rem;
	}
	
	.next{
	   padding: 0.275rem 0.25rem;
	}
	
	td.subject {
	   margin : 5;
	   padding : 10;
	   font-size: 25px;
	   font-weight : bold;
	}
	
	tr.sharing {
		text-align: right;
	}
	
	td#sharing{
		border-color : White;
		border-bottom : solid 1px Silver;
		text-align: right;
	}
	
	td.content {
	   margin : 5;
	   padding : 10;
	   border-color : White;
	}
	
	#board_content{
		border-color : White;
		background-color: White;
		outline: none;
	}
	
	#board_content:focus {
  	   outline: none;
	}
	
	body>div>table>tbody tr:last-child {
	  text-align : left;
	}
	
	td.file {
	 border-color : White;
	 border-bottom : solid 1px Silver;
	}
	
	a:hover{
	  color : black;
	}
	
	h3{
	  font-weight: lighter;
	}
</style>
<script>
	$(function(){
		$("#deletebutton").click(function(event){
			var answer = confirm("정말 삭제하시겠습니까?");
			let num = '${param.num}' //주소줄에서 넘어오는거 가져옴 
			
			if(answer){
				location.href="FreeDeleteAction.bof?num="+num;
			}else {
				event.preventDefault();
			}
		})
	})
</script>
</head>
<body>
  
  <div class='container box_radius15 board_container'>  
  <input type="hidden" id="loginid" value="${id}" name="loginid">
  <input type="hidden" name="num" value="${param.num}" id="comment_board_num"> <%-- 폼안에 안넣으면 안넘어가나? --%>
  <input type="hidden" name="board_name" value="${board.board_name}">
   <div class='modifybutton'>
	<c:if test="${board.board_name == id || id == 'admin'}">
	   <%-- href의 주소를 #으로 설정합니다. --%>
	  <a href="#">
		<button class="btn btn-danger delete" id="deletebutton" style="float: right">삭제</button>
	  </a>
	  <a href="FreeModifyView.bof?num=${board.board_num}">
		<button class="btn btn-success update" style="float: right">수정</button>
	  </a>
	</c:if>
   </div>
   
   <table class="table">
   	 <tr>
		<td colspan="4" class="subject"><c:out value="${board.board_subject}"/></td>
	 </tr>
	 <tr>
	 	<td class="name">작성자 ${board.board_name}</td>
	 	<td class="readcount">조회수&nbsp;${board.board_readcount}</td>
	 	<td class="date">작성일자&nbsp;${board.board_date}</td>
	 	<td><a href="#comment-area">댓글 <sup class="count"></sup></a></td> <%-- 클릭하면 댓글로 이동 --%>
	 </tr>
	 <tr>
	 	<td colspan="4" class ="content">
	 	 <textarea name="board_content" id="board_content" rows="10" readOnly
    			   class="form-control" placeholder="내용을 입력하세요">${board.board_content}</textarea>
	 	</td>
	 </tr>
	 <tr class="sharing">
	 	<td colspan="4" id="sharing">
	 	<img src="image/sharing.png" width="20px"> 공유 </td>
	 </tr>
	 
	 <c:if test="${board.board_re_lev==0}">
		<%-- 원문글인 경우에만 첨부파일을 추가 할 수 있습니다. --%>
		<tr>
			<%-- 파일을 첨부한 경우 --%>
			<c:if test="${!empty board.board_file}">
			<td class="file"><div>첨부파일</div></td>
				<td colspan="3" class="file"><label ><img src="image/down.png" width="10px">
					<a href="FreeFileDown.bof?filename=${board.board_file}">${board.board_file}
					</a></label></td>
			</c:if>
					
			<%-- 파일을 첨부하지 않은 경우 --%>
			<c:if test="${empty boarddata.board_file}">
				
			</c:if>
		</tr>
	 </c:if>
	 
   </table>
   
   <div class="comment-area" id="comment-area">
  		<div class="comment-head">
  			<h3 class="comment-count">
  				댓글 <sup class="count"></sup>
  			</h3>
  			<div class="comment-order">
  				<ul class="comment-order-list">
  				</ul>
  			</div>
  		</div>
  		<ul class="comment-list">
  		</ul>
  		<div class="comment-write">
  			<div class="comment-write-area">
  				<b class="comment-write-area-name">${id}</b> <span
  					class="comment-write-area-count">0/200</span>
  				<textarea placeholder="댓글을 남겨보세요" rows="1"
  					class="comment-write-area-text" maxLength="200"></textarea>
  			</div>
  			<div class="register-box">
  				<div class="button btn-cancel">취소</div><%-- 댓글의 취소는 display:none, 등록만 보이도록 합니다. --%>
  				<div class="button btn-register">등록</div>
  			</div>
  		</div>
  	</div>
  	
  	<div>
  	   <c:if test="${board.board_re_lev < 2}">
  		<a href="FreeReplyView.bof?num=${board.board_num}">
  		<button class="btn reply">답글쓰기</button></a>
  		</c:if>
  		
  	    <c:forEach var = 'bnum' items="${board_num_next_prev}">
	  	 <c:if test="${bnum.board_prev_num != -1}"> 
	  		<a href="FreeDetailAction.bof?num=${bnum.board_prev_num}">
	  		<button class="btn prev">이전글</button></a>
	  	   </c:if> 
	  	</c:forEach>
  	 
  		<a href="FreeList.bof"><button class="btn list">목록</button></a>
  		
  	   <c:forEach var = 'bnum' items="${board_num_next_prev}">
  	    <c:if test="${bnum.board_next_num != -1}"> 
  		 <a href="FreeDetailAction.bof?num=${bnum.board_next_num}">
  		 <button class="btn next">다음글</button></a>
  	    </c:if>
  	   </c:forEach>
  	</div>
  </div>
</body>
</html>