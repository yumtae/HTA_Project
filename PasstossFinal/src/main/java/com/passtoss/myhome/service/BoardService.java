package com.passtoss.myhome.service;

import java.util.List;
import java.util.Map;

import com.passtoss.myhome.domain.Board;

public interface BoardService {
	

	public int getListCount(int companyid);
	
	
	public List<Board> getBoardList(int companyid);
	
	//public List<Board> getSubBoardList(int num);
	
	
	public Board getDetail(int num);
	
	
	public int boardReply(Board board);
	

	public int boardReplyUpdate(Board board);
	

	public int boardModify(Board modifyboard);

	public int boardDelete(int num);
	
	
	
	public int setReadCountUpdate(int num);
	
	
	public boolean isBoardWriter(int num, String pass);
	

	public int insertBoard(Board board);

	
	public List<String> getDeleteFileList();
	
	
	public void deleteFileList(String filename);


	int insertSubWork(Board board);


	public List<Map<String, Integer>> groupByStatus();


	public int getListCountAll(int companyId);


	public List<Board> getDetailOne(int bOARD_NUM);


	public int boardDeleteOne(int bOARD_NUM);


	public int boardUpdate(Board boarddata);


	public void deleteSubWork(int board_RE_REF);




}
