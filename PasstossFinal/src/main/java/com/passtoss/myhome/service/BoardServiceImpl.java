package com.passtoss.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passtoss.myhome.domain.Board;
import com.passtoss.myhome.mybatis.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper dao;
	
	@Override
	public int getListCountAll(int companyid) {
		return dao.getListCountAll(companyid);
	}
	
	@Override
	public int getListCount(int companyid) {
		return dao.getListCount(companyid);
	}

	@Override
	public List<Board> getBoardList(int companyid) {
		//HashMap<String, Integer> map = new HashMap<String, Integer>();
		return dao.getBoardList(companyid);
	}
	
	
	/*
	 * @Override public List<Board> getSubBoardList(int num) { return
	 * dao.getSubBoardList(num); }
	 */
	
	
	

	@Override
	public int boardReplyUpdate(Board board) {
		return dao.boardReplyUpdate(board);
	}

	@Override
	public int boardReply(Board board) {
		boardReplyUpdate(board);
		board.setBOARD_RE_LEV(board.getBOARD_RE_LEV()+1);
		board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ()+1);
		return dao.boardReply(board);
	}

	@Override
	public int boardModify(Board modifyboard) {
		return dao.boardModify(modifyboard);
	}

	@Override
	public int boardDelete(int num) {
		int result= 0 ;
		Board board = dao.getDetail(num);
		if(board!= null) {
			result = dao.boardDelete(board);
		}
		return result;
	}

	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}
	@Override
	public Board getDetail(int num) {
		return dao.getDetail(num);
	}
	
	@Override
	public boolean isBoardWriter(int num, String pass) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		Board result = dao.isBoardWriter(map);
		if (result==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public int insertBoard(Board board) {
		return dao.insertBoard(board);
		
	}

	@Override
	public int insertSubWork(Board board) {
		return dao.insertSubWork( board);
		
	}
	
	
	
	
	
	@Override
	public List<String> getDeleteFileList() {
		return dao.getDeleteFileList();
	}

	@Override
	public void deleteFileList(String filename) {
		dao.deleteFileList(filename);
		
	}

	@Override
	public List<Map<String, Integer>> groupByStatus() {
		return dao.groupByStatus();
	}

	@Override
	public List<Board> getDetailOne(int bOARD_NUM) {
		return dao.getDetailOne(bOARD_NUM);
	}

	@Override
	public int boardDeleteOne(int bOARD_NUM) {
		return dao.boardDeleteOne(bOARD_NUM);
	}

	@Override
	public int boardUpdate(Board boarddata) {
		return dao.boardUpdate(boarddata);
	}

	@Override
	public void deleteSubWork(int board_RE_REF) {
		dao.deleteSubWork(board_RE_REF);
		
	}

	


	

	
	
	
	
}
