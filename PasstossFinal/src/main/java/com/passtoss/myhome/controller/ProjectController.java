package com.passtoss.myhome.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.passtoss.myhome.domain.Project;
import com.passtoss.myhome.service.ProjectService;

@Controller
@RequestMapping("/main")
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	private ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping("/myPro")
	public ModelAndView goProject(ModelAndView mv, HttpSession session, Principal principal) {
		//String id = principal.getName();
		/*int companyId = (int) session.getAttribute("companyId");
		session.removeAttribute("companyId");
		logger.info("회사번호 : " + companyId);

		Project p = projectService.IsProject(id, companyId);*/
		
		//List<Project> project = projectService.getProjectList(id);

		//mv.addObject("project", project);
		mv.setViewName("board/Project");
		logger.info("프로젝트 페이지 이동");
		return mv;
	}

	@GetMapping("/newProject")
	public String newProject() {
		return "board/newProject";
	}

	@PostMapping("/createProject")
	public String createProject(Project p, Principal principal) {
		String id = principal.getName();
		projectService.newProject(p, id);

		return "redirect:myPro";
	}

}
