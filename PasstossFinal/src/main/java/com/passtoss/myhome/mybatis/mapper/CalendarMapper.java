package com.passtoss.myhome.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.passtoss.myhome.domain.Board;

@Mapper
public interface CalendarMapper {


	// 글 등록하기
	public int insertBoard(Board board);

	public int calDelete(int num);

	public int calUpdate(Board boarddata);
	
}


