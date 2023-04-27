package com.passtoss.myhome.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.passtoss.myhome.domain.Project;
import com.passtoss.myhome.mybatis.mapper.ProjectMapper;

@Service
public class ProjectServiceImpl implements ProjectService {
	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	private ProjectMapper dao;

	@Autowired
	public ProjectServiceImpl(ProjectMapper dao) {
		this.dao = dao;
	}

	@Transactional
	@Override
	public void newProject(Project p, String id) {
		dao.newProject(p);
		logger.info("생성한 project_id = "+p.getProject_id());
		dao.newProjectMember(id, p.getProject_id());
	}

	@Override
	public List<Project> getProjectList(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
