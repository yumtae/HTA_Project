package com.passtoss.myhome.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.passtoss.myhome.domain.ProjectManagers;
import com.passtoss.myhome.service.ProjectManagersService;

@Mapper
public interface ProjectManagersMapper {

		//담당자 조회
		public List<ProjectManagersService> getProjectManager(int num);
	
		//담당자 추가 삭제
		public void insertProjectManager(ProjectManagers projectManagers);

		public void deleteProjectManager(ProjectManagers projectManagers);

		public void insertProjectManager(int BOARD_NUM, String ID);

		public void deleteProjectManagerNum(int bOARD_NUM);
	
}
