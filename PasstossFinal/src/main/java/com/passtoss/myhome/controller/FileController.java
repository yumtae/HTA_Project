package com.passtoss.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/file")
public class FileController {
	
	@GetMapping("/filebox")
	public String filebox() {
		return "file/fileBox";
	}

}
