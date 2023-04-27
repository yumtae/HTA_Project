package com.passtoss.myhome.mybatis.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.passtoss.myhome.domain.Board;

@Mapper
public interface BoardMapper {


	// 글의 개수 구하기
	public int getListCount(int companyId);

	// 글 목록 보기
	public List<Board> getBoardList(int companyid);
	
	// 하위업무 출력
	//public List<Board> getSubBoardList(int num);

	// 글 내용 보기
	public Board getDetail(int num);

	// 글 답변
	public int boardReply(Board board);

	// 글 수정
	public int boardModify(Board modifyboard);

	// 글 삭제
	public int boardDelete(Board board);

	// 조회수 업데이트
	public int setReadCountUpdate(int num);

	// 글쓴이 확인
	public Board isBoardWriter(HashMap<String, Object> map);

	// 글 등록하기
	public int insertBoard(Board board);

	// BOARD_RE_SEQ값 수정
	public int boardReplyUpdate(Board board);
	
	
	public List<String> getDeleteFileList();
	
	public void deleteFileList(String filename);

	public int insertSubWork(Board board);

	public List<Map<String, Integer>> groupByStatus();

	public int getListCountAll(int companyId);

	public List<Board> getDetailOne(int bOARD_NUM);

	public int boardDeleteOne(int bOARD_NUM);

	public int boardUpdate(Board boarddata);

	public void deleteSubWork(int board_RE_REF);

	
}


