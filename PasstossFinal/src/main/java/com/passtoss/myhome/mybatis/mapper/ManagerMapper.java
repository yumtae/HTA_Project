package com.passtoss.myhome.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.passtoss.myhome.domain.Company;

@Mapper
public interface ManagerMapper {

	public int updateCompany(Company c);

	public int deleteLogo(int companyId);

	
}
