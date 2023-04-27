package com.passtoss.myhome.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.Member;

@Mapper
public interface AdminMapper {


	// 글 등록하기
	public int getListCount();
	
	public int getListCount2();
	
	public List<Company> getCompanyList();
	
	public List<Member> getMemberList();

	public List<Company> getSearchList(Map<String, Object> map);

	public int getSearchListCount(Map<String, String> map);

	public List<Member> getSearchList2(Map<String, Object> map);

	public int getSearchListCount2(Map<String, String> map);



	public int beUpdate(String id);

	public void delete(String id);

//	public int calDelete(int num);
//
//	public int calUpdate(Board boarddata);
	
}


