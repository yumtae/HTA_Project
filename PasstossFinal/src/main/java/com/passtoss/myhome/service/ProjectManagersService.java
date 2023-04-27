package com.passtoss.myhome.service;

import java.util.List;

import com.passtoss.myhome.domain.ProjectManagers;

public interface ProjectManagersService {


	void insertProjectManager(ProjectManagers projectManagers );


	void deleteProjectManager(ProjectManagers projectManagers );


	void insertProjectManager(int BOARD_NUM, String ID);
	
	List<ProjectManagersService> getProjectManager(int BOARD_NUM);


	void deleteProjectManager(int BOARD_NUM);
	
	
}
