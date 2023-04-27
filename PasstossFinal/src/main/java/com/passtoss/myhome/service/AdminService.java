package com.passtoss.myhome.service;

import java.util.List;
import java.util.Map;


import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.Member;

public interface AdminService {
	

	public int getListCount();
	

	
	public List<Company> getCompanyList();
	
	public List<Member> getMemberList();


	public int getSearchListCount(int index, String search_word);


	public List<Company> getSearchList(int index, String search_word, int page, int limit);
	
	public List<Member> getSearchList2(int index, String search_word, int page, int limit);


	public int getSearchListCount2(int index, String search_word);


	int getListCount2();



	void delete(String id);



	int update(Member m);



	void update(String id);
	
	
	//public List<Board> getSubBoardList(int num);
	
	
//	public Board getDetail(int num);
//	
//	
//	public int boardReply(Board board);
//	
//
//	public int boardReplyUpdate(Board board);
//	
//
//	public int boardModify(Board modifyboard);
//
//	public int boardDelete(int num);
//	
//	
//	
//	public int setReadCountUpdate(int num);
//	
//	
//	public boolean isBoardWriter(int num, String pass);
//	
//
//	public int insertBoard(Board board);
//
//	
//	public List<String> getDeleteFileList();
//	
//	
//	public void deleteFileList(String filename);
//
//
//	int insertSubWork(Board board);
//
//
//	public List<Map<String, Integer>> groupByStatus();
//
//
//	public int getListCountAll();
//
//
//	public List<Board> getDetailOne(int bOARD_NUM);
//
//
//	public int boardDeleteOne(int bOARD_NUM);
//
//
//	public int boardUpdate(Board boarddata);
//
//
//	public void deleteSubWork(int board_RE_REF);




}
