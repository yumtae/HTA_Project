package com.passtoss.myhome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passtoss.myhome.domain.Comment;
import com.passtoss.myhome.service.CommentService;

@RestController
@RequestMapping(value="/comment")
public class CommentController {
	
	
	@Autowired
	private CommentService commentService;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	
	@PostMapping(value="/list")
	public Map<String,Object> CommentList(int board_num) {
		List<Comment> list = commentService.getCommentList(board_num);

		int listcount = commentService.getListCount(board_num);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("listcount", listcount);
		return map;
		
	}
	
	
	@PostMapping(value="/add")
	public Map<String,Object> CommentAdd(Comment c) {
		
		
		int result = commentService.commentInsert(c);
		
		
		
		List<Comment>  list = commentService.getComment(c.getNUM());
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("result", result);
		map.put("list", list);
	

	
		return map;		
		
		
	}
	
	@PostMapping(value="/update")
	public int CommentUpdate(Comment c) {
		return commentService.commentUpdate(c);		
		
	}
	
	@PostMapping(value="/delete")
	public int CommentDelete(int num) {
		return commentService.commentDelete(num);		
		
	}
	
	
}
