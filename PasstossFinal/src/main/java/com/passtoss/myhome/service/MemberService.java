package com.passtoss.myhome.service;

import java.util.List;
import java.util.Map;

import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.Member;

public interface MemberService {

	public int isId(String id);
	
	public Member memberInfo(String id);

	public int mailAuth(Map<String, String> map);

	public int mailCertify(Map<String, String> map);	

	public int checkURL(String url);

	public Company isCompany(String url);

	public int createCompany(Company c, Member m);
	
	public Map<String, Integer> joinCompany(Member m);

	public int resetPassword(String id, String password);

	public List<Map<String, Object>> getSearchMemberList(String searchword, List<String> persons);

	public int updateProfile(Member m);

	
	public List<Object> getCompanyAllID(int companyId);
	
}
