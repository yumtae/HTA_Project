package com.passtoss.myhome.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.MySaveFolder;
import com.passtoss.myhome.service.ManagerService;
import com.passtoss.security.CustomUserDetails;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

	private ManagerService managerService;
	private MySaveFolder mySaveFolder;

	@Autowired
	public ManagerController(ManagerService managerService, MySaveFolder mySaveFolder) {
		this.managerService = managerService;
		this.mySaveFolder = mySaveFolder;
	}

	@GetMapping("/companyInfo")
	public String companyInfo() {
		return "manager/companyInfo";
	}
	
	@GetMapping("/memberInfo")
	public String memberInfo(@AuthenticationPrincipal CustomUserDetails user) {
		
		
		
		return "manager/memberInfo";
	}

	@PostMapping("/updateCompany")
	public String updateCompany(Company c, Model model, RedirectAttributes rattr,
			@AuthenticationPrincipal CustomUserDetails user) throws Exception {

		int companyId = c.getCompany_id();
		MultipartFile logoImg = c.getLogoFile();
		String original = logoImg.getOriginalFilename();
		String uploadFolder = mySaveFolder.getLogoFolder();
		String newName = "";

		if (!original.equals("")) {
			newName = fileReName(companyId, original);

			File files = new File(uploadFolder);
			File file[] = files.listFiles();

			int index = newName.lastIndexOf(".");
			String noExNewName = newName.substring(0, index);

			for (int i = 0; i < file.length; i++) {
				index = file[i].getName().lastIndexOf(".");
				String name = file[i].getName().substring(0, index);// 확장자 제거한 파일이름

				if (name.equals(noExNewName)) {
					file[i].delete();
					break;
				}
			}

			logoImg.transferTo(new File(uploadFolder, newName));
			c.setLogo(newName);

		}

		int result = managerService.updateCompany(c);

		if (result == 1) {
			logger.info("업데이트 성공");
			user.setCompanyName(c.getCompany_name());
			user.setLogo(newName);
			user.setUrl(c.getUrl());
			user.setAccessOption(c.getAccess_option());
			rattr.addFlashAttribute("result", "companyUpdate");
		}

		return "redirect:/manager/companyInfo";
	}

	private String fileReName(int companyId, String original) {
		int index = original.lastIndexOf(".");
		String fileExtension = original.substring(index + 1);
		String newName = companyId + "." + fileExtension;

		return newName;
	}

	@ResponseBody
	@PostMapping("/deleteLogo")
	public int deleteLogo(@RequestParam("companyId") int companyId, @AuthenticationPrincipal CustomUserDetails user) {
		int result = managerService.deleteLogo(companyId);
		String uploadFolder = mySaveFolder.getLogoFolder();
		String logo = String.valueOf(companyId);
		
		File files = new File(uploadFolder);
		File file[] = files.listFiles();
		
		for (int i = 0; i < file.length; i++) {
			int index = file[i].getName().lastIndexOf(".");
			String name = file[i].getName().substring(0, index);

			if (name.equals(logo)) {
				logger.info("로고 제거");
				file[i].delete();
				break;
			}
		}
		
		if(result == 1) {
			user.setLogo(null);
		}

		return result;
	}
}
