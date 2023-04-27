package com.passtoss.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.Member;
import com.passtoss.myhome.mybatis.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper dao;
	
	
	
	
	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	@Override
	public int getListCount2() {
		return dao.getListCount2();
	}

	@Override
	public List<Company> getCompanyList() {
		// TODO Auto-generated method stub
		return null;
	}



public List<Company> getSearchList(int index, String search_word, int page, int limit) {
		
		//http://localhost:8088/myhome5/member/list로 접속하는 경우
		//select를 선택하지 않아 index는 "-1"의 값을 갖습니다.
		//이 경우 아래의 문장을 수행하지 않기 때문에 "search_field" 키에 대한 
		//map.get("search_field")의 값은 null이 됩니다.
		Map<String, Object> map = new HashMap<String, Object>();
		if(index != -1) {
			String[] search_field = new String[] {"company_id", "company_name", "ceo_id"};
			map.put("search_field", search_field[index]);
			map.put("search_word","%" + search_word + "%");
		}
		
		int startrow = (page-1)*limit + 1;
		int endrow = startrow + limit - 1;
		map.put("start", startrow);
		map.put("end", endrow);
		
		return dao.getSearchList(map);
	}

	@Override
	public int getSearchListCount(int index, String search_word) {
		
		Map<String, String> map = new HashMap<String, String>();
		if(index != -1) {
			String[] search_field = new String[] {"company_id", "company_name", "ceo_id"};
			map.put("search_field", search_field[index]);
			map.put("search_word","%" + search_word + "%");
		}
		
		return dao.getSearchListCount(map);
	}



	@Override
	public List<Member> getSearchList2(int index, String search_word, int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(index != -1) {
			String[] search_field = new String[] {"id", "username"};
			map.put("search_field", search_field[index]);
			map.put("search_word","%" + search_word + "%");
		}
		
		int startrow = (page-1)*limit + 1;
		int endrow = startrow + limit - 1;
		map.put("start", startrow);
		map.put("end", endrow);
		
		return dao.getSearchList2(map);
	}



	@Override
	public int getSearchListCount2(int index, String search_word) {
		Map<String, String> map = new HashMap<String, String>();
		if(index != -1) {
			String[] search_field = new String[] {"id", "username"};
			map.put("search_field", search_field[index]);
			map.put("search_word","%" + search_word + "%");
		}
		
		return dao.getSearchListCount2(map);
	}

	@Override
	public List<Member> getMemberList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public void update(String id) {
		dao.beUpdate(id);
	}

	@Override
	public int update(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}

//	@Override
//	public List<Map<String, Integer>> groupByStatus() {
//		return dao.groupByStatus();
//	}
//
//	@Override
//	public List<Board> getDetailOne(int bOARD_NUM) {
//		return dao.getDetailOne(bOARD_NUM);
//	}
//
//	@Override
//	public int boardDeleteOne(int bOARD_NUM) {
//		return dao.boardDeleteOne(bOARD_NUM);
//	}
//
//	@Override
//	public int boardUpdate(Board boarddata) {
//		return dao.boardUpdate(boarddata);
//	}
//
//	@Override
//	public void deleteSubWork(int board_RE_REF) {
//		dao.deleteSubWork(board_RE_REF);
//		
//	}
//
//	@Override
//	public List<Company> getCompanyList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	


	

	
	
	
	

