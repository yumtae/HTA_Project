package com.passtoss.myhome.service;

import java.util.List;

import com.passtoss.myhome.domain.Project;

public interface ProjectService {

	public void newProject(Project p, String id);

	public List<Project> getProjectList(String id);

}
