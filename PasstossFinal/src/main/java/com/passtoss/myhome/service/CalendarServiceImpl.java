package com.passtoss.myhome.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passtoss.myhome.domain.Board;
import com.passtoss.myhome.mybatis.mapper.BoardMapper;
import com.passtoss.myhome.mybatis.mapper.CalendarMapper;

@Service
public class CalendarServiceImpl {
	
	@Autowired
	private CalendarMapper dao;
	
	public int calDelete(int num) {
		int result= dao.calDelete(num);
		return result;
	}

	public int calUpdate(Board boarddata) {
	
		return dao.calUpdate(boarddata);
	}

	
}
