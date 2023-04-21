<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사내게시판</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>
<script src = "js/list.js"></script>
<script src="js/jquery-3.6.3.js"></script>
<script>
	$(function(){
		$("#button").click(function(){
			location.href="FreeWrite.bof";
		})
	});
</script>
<style>
	/*table-striped 글 목록에 회색색깔 나오게 하는거 */
	
	form {
	margin: 0 auto;
	width: 60%;
	text-align: center
	}
	
	 select {
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	/*border-radius: .25rem;*/
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	outline: none;
	}
	
	select.form-control {
    width: auto;
    margin-bottom: 2em;
    display: inline-block;
	}
	
	.container {
	width: 80%
	}
	
	a{
		color : black;
		text-decoration: none;
	}
	
	a:hover{
		color : black;
	}
	
	.write {
	  border-radius: 0.25rem;
	  text-align: left;
	  /*dispaly : right;*/
	  color: #fff;
      background-color: #212529;
      border-color: #212529;
      border: 1px solid transparent;
      border-radius: 0rem;
      padding: 0.375rem 0.75rem;
      float: right;
	}
	
	.gray{
 		color: gray;
 	}
 	
 	h1{
 		text-align:center;
 		padding : 30;
 	}
</style>
</head>
<body>

	<div class='container box_radius15 board_container'>
	<input type="hidden" id="loginid" value="${id}" name="loginid"> <%-- leftMenu에서 저장된 id값 --%>
	<input type="hidden" id="search_field" value="${search_field}" name="search_field">
	  <form action="FreeList.bof" method="post">
			<div class="input-group">
				<select id="viewcount" name="search_field">
					<option value="0" selected>전체</option>
					<option value="1">제목</option>
					<option value="2">작성자</option>
				</select>
					<input id="word" name="search_word" type="text" class="form-control"
						   placeholder="검색어를 입력하세요" value="${search_word}">
					<button type="submit" id="search"  class="btn btn-dark">검색</button>
			</div>
		</form>
		
		<c:if test="${noticelist.size() > 0  || listcount > 0}">
		
		  <table class="table ">
		  	<thead>
		  		<tr>
		  			 <th colspan="5">사내게시판 list</th>
		  			 <th><span>게시글 개수 : ${listcount}</span></th>
		  		</tr>
		  		<tr>
		  			<td>번호</td>
		  			<td>말머리</td>
		  			<td>제목</td>
		  			<td>작성자</td>
		  			<td>등록일</td>
		  			<td>조회수</td>
		  		</tr>
		    </thead>
		    <tbody>
		     
		     <c:forEach var="b" items="${noticelist}">
	   		 	<tr>
	   		 	  <td>
	   		 	    <img src="image/notice.png" width="25">
	   		 	  </td>
	   		 	  <td> 
	   		 	    <c:out value="[공지사항]"/>
	   		 	  </td>
	   		 	  <td> <%-- 제목 --%>
	   		 	    <div>
	   		      	<c:if test="${b.board_re_lev != 0}"> <%-- 답글인 경우 --%>
	   		      	<c:forEach var="a" begin="0" end="${b.board_re_lev*2}" step="1">
	   		      	 &nbsp;
	   		      	</c:forEach>
	   		      	<img src='image/reply.png' width='25px' >
	   		      	</c:if>
	   		      
	   		      	<c:if test="${b.board_re_lev == 0}"> <%-- 원문인 경우 --%>
	   		          &nbsp;
	   		      	</c:if>
	   		        
	   		      	<a href="FreeDetailAction.bof?num=${b.board_num}">
	   		      	<c:if test="${b.board_subject.length() >= 20}">
	   		      	  <c:out value="${b.board_subject.substring(0,20)}..."/>
	   		      	</c:if>
	   		      	<c:if test="${b.board_subject.length() < 20}">
	   		      	   <c:out value="${b.board_subject}"/>
	   		      	</c:if>
	   		        </a>[${b.cnt}]
	   		    	</div>
	   		   	 </td>
	   		   	 <td><div>${b.board_name}</div></td>
	   		   	 <td><div>${b.board_date}</div></td>
	   		   	 <td><div>${b.board_readcount}</div></td>
	   		 	</tr>
	   		 </c:forEach>
	   		 <c:set var="num" value="${listcount - (page-1)*limit }"/>
	   		 <c:forEach var="b" items="${boardlist}">
	   		 	<tr>
	   		 	  <td> <%-- 번호 --%>
	   		 	    <c:out value="${num}"/>
	   		 	    <c:set var="num" value="${num-1}"/>
	   		 	  </td>
	   		 	  <td> 
	   		 	    <c:out value="[게시글]"/>
	   		 	  </td>
	   		 	  <td> <%-- 제목 --%>
	   		 	    <div>
	   		      	<c:if test="${b.board_re_lev != 0}"> <%-- 답글인 경우 --%>
	   		      	<c:forEach var="a" begin="0" end="${b.board_re_lev*2}" step="1">
	   		      	 &nbsp;
	   		      	</c:forEach>
	   		      	<img src='image/reply.png ' width="25px">
	   		      	</c:if>
	   		      
	   		      	<c:if test="${b.board_re_lev == 0}"> <%-- 원문인 경우 --%>
	   		          &nbsp;
	   		      	</c:if>
	   		        
	   		      	<a href="FreeDetailAction.bof?num=${b.board_num}">
	   		      	<c:if test="${b.board_subject.length() >= 20}">
	   		      	  <c:out value="${b.board_subject.substring(0,20)}..."/>
	   		      	</c:if>
	   		      	<c:if test="${b.board_subject.length() < 20}">
	   		      	   <c:out value="${b.board_subject}"/>
	   		      	</c:if>
	   		        </a>[${b.cnt}]
	   		    	</div>
	   		   	 </td>
	   		   	 <td><div>${b.board_name}</div></td>
	   		   	 <td><div>${b.board_date}</div></td>
	   		   	 <td><div>${b.board_readcount}</div></td>
	   		 	</tr>
	   		 </c:forEach>
		    </tbody>
		  </table>
		  
		 <div class="rows">
	  		<span>줄보기</span>
	  		<select class="form-control" id="lineviewcount">
	  			<option value="5">5</option>
	  			<option value="10" selected>10</option>
	  			<option value="15">15</option>
	  			<option value="20">20</option>
	  		
	  		</select>
	  	 </div>
		  
		  <c:if test="${listcount > 0}">
		  <div class="center-block">
	  	 	<ul class="pagination justify-content-center">
	  	 		<c:if test="${page <= 1}">
	  	 			<li class="page-item">
	  	 			  <a class="page-link gray">이전 &nbsp;</a>
	  	 			</li>
	  	 		</c:if>
	  	 		<c:if test="${page > 1}">
	  	 			<c:url var="prev" value="FreeList.bof">
	  	 	  		<c:param name="search_field" value="${search_field}" />
	  	 	  		<c:param name="search_word" value="${search_word}" />
	  	 	  		<c:param name="page" 	value="${page-1}" />
	  	 	  	</c:url>
	  	 		<li class="page-item">
	  	 		  <a href="${prev}"
	  	 		     class="page-link">&nbsp;이전</a>
	  	 		</li>
	  	 		</c:if>
	  	 	
	  	 		<c:forEach var="a" begin="${startpage}" end="${endpage}">
	  	 	 	 <c:if test="${a == page}">
	  	 	  		<li class="page-item active">
	  	 	  			<a class="page-link">${a}</a>
	  	 	  		</li>
	  	 	 	 </c:if>
	  	 	 		 <c:if test="${a != page}">
	  	 	  			<c:url var="go" value="FreeList.bof">
	  	 	  			<c:param name="search_field" value="${search_field}" />
	  	 	  			<c:param name="search_word" value="${search_word}" />
	  	 	  			<c:param name="page" 	value="${a}" />
	  	 	  		</c:url>
	  	 	  		<li class="page-item">
	  	 	  			<a href="${go}"
	  	 	  		  	 class="page-link">${a}</a>
	  	 	  		</li>
	  	 	  		</c:if>
	  	 		</c:forEach>
	  	 	
	  	 		<c:if test="${page >= maxpage}">
	  	 			<li class="page-item">
	  	 			  <a class="page-link gray">&nbsp;다음</a>
	  	 			</li>
	  	 		</c:if>
	  	 		<c:if test="${page < maxpage}">
	  	 			<c:url var="next" value="FreeList.bof">
	  	 	  		<c:param name="search_field" value="${search_field}" />
	  	 	  		<c:param name="search_word" value="${search_word}" />
	  	 	  		<c:param name="page" 	value="${page+1}" />
	  	 	  	</c:url>
	  	 		<li class="page-item">
	  	 		  <a href="${next}"
	  	 		     class="page-link">&nbsp;다음</a>
	  	 		</li>
	  	 		</c:if>
	  		 </ul>
	 	 </div>
	 	 </c:if>
		</c:if>
		
		<button id="button" type="button"  class="write">글 쓰 기</button>
		
		<c:if test="${noticelist.size() ==0 && listcount == 0 && empty search_word}" >
			<div><h1>등록된 글이 없습니다.</h1></div>
		</c:if>
  		<c:if test="${noticelist.size() ==0 && listcount == 0 && !empty search_word}">
  			<div><h1>검색 결과가 없습니다.</h1></div>
  		</c:if>
		
	</div>
</body>
</html>