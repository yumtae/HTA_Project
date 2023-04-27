package com.passtoss.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passtoss.myhome.domain.Comment;
import com.passtoss.myhome.mybatis.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentMapper dao;
	
	@Autowired
	public CommentServiceImpl (CommentMapper dao) {
		this.dao = dao;
	}

	@Override
	public int getListCount(int board_num) {
		return dao.getListCount(board_num);
	}

	@Override
	public List<Comment> getCommentList(int board_num) {

		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("board_num", board_num);
	
		
		return dao.getCommentList(map);
	}

	@Override
	public int commentInsert(Comment c) {
		
		return dao.commentsInsert(c);
	}

	@Override
	public int commentDelete(int num) {
		return dao.commentsDelete(num);
	}

	@Override
	public int commentUpdate(Comment co) {
		return dao.commentsUpdate(co);
	}

	@Override
	public List<Comment> getComment(int num) {
		 return dao.getComment(num);
	}

	
	
	
	
	
}
