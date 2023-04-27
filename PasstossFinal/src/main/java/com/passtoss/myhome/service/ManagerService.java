package com.passtoss.myhome.service;

import com.passtoss.myhome.domain.Company;

public interface ManagerService {

	public int updateCompany(Company c);

	public int deleteLogo(int companyId);

}
