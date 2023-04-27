package com.passtoss.myhome.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.passtoss.myhome.domain.Project;

@Mapper
public interface ProjectMapper {

	public int newProject(Project p);

	public Project isProject(String id, int companyId);

	public void newProjectMember(String id, int projectId);

}
