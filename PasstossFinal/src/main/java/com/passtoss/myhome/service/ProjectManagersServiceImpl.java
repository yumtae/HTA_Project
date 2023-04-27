package com.passtoss.myhome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passtoss.myhome.domain.ProjectManagers;
import com.passtoss.myhome.mybatis.mapper.ProjectManagersMapper;

@Service
public class ProjectManagersServiceImpl implements ProjectManagersService{

	@Autowired
	private ProjectManagersMapper dao;
	
	
	
	@Override
	public void insertProjectManager(ProjectManagers projectManagers ) {
		dao.insertProjectManager(projectManagers);
		
	}

	@Override
	public void deleteProjectManager(ProjectManagers projectManagers ) {
		dao.deleteProjectManager(projectManagers);
		
	}
	
	@Override
	public void deleteProjectManager(int BOARD_NUM) {
		dao.deleteProjectManagerNum(BOARD_NUM);
		
	}


	@Override
	public void insertProjectManager(int BOARD_NUM, String ID) {
		dao.insertProjectManager(BOARD_NUM , ID);
		
	}

	@Override
	public List<ProjectManagersService> getProjectManager(int BOARD_NUM) {
		return dao.getProjectManager(BOARD_NUM);
		
	}
	
	
}
