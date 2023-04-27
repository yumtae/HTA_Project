package com.passtoss.myhome.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.MailAuth;
import com.passtoss.myhome.domain.Member;

@Mapper
public interface MemberMapper {

	public Member isId(String id);
	
	public MailAuth isMailAuth(Map<String, String> map);
	
	public int insertMailAuth(Map<String, String> map);	

	public int updateMailAuth(Map<String, String> map);

	public int createCompany(Company c);

	public int insertUser(Member m);

	public Company checkURL(String url);

	public int resetPassword(String id, String password);

	public List<Map<String, Object>> getSearchMemberList(String searchword, List<String> persons);

	public int updateProfileImg(String id, String newName);

	public int updateProfile(Member m);

	public Company getCompanyInfo(int company_id);

	public List<Object> getCompanyAllID(int companyId);

}
