package com.passtoss.myhome.service;

import java.util.List;

import com.passtoss.myhome.domain.Comment;

public interface CommentService {
	
	
	
	public int getListCount(int board_num);
	

	public List<Comment> getCommentList(int board_num);
	
	

	public int commentInsert(Comment c);
	

	public int commentDelete(int num);
	
	
	public int commentUpdate(Comment co);


	public List<Comment> getComment(int num);
	
	
	
}
