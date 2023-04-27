package com.passtoss.myhome.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.mybatis.mapper.ManagerMapper;

@Service
public class ManagerServiceImpl implements ManagerService{
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

	private ManagerMapper dao;
	
	@Autowired
	public ManagerServiceImpl(ManagerMapper dao) {
		this.dao = dao;
	}

	@Override
	public int updateCompany(Company c) {		
		return dao.updateCompany(c);
	}

	@Override
	public int deleteLogo(int companyId) {
		return dao.deleteLogo(companyId);
	}

}
