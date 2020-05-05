package com.techleads.app.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.techleads.app.model.Employee;

@Controller
public class FileUploadController {
	private String LOCATION = "C:\\Users\\DELL\\Desktop\\uploadFiles\\";

	@RequestMapping(value = "/uploadfile", method = RequestMethod.GET)
	public String showUploadForm(@ModelAttribute("empCmd") Employee employee) {
		return "file_upload";
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("empCmd") Employee employee, Model model)
			throws Exception {

		
		  MultipartFile multipartFile; multipartFile=employee.getFile1(); 
		  if(!multipartFile.isEmpty()) {
		String fileName;
		 fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		File pathFile =new File(LOCATION); // check if directory exist, if not, create directory if
		  if(!pathFile.exists()) { 
			  pathFile.mkdir();
			  } 
		  // create the actual file 
		  pathFile = new File(LOCATION + multipartFile.getOriginalFilename()); 
		  // save the actual file
		  
		  
		  multipartFile.transferTo(pathFile);}
		 
		model.addAttribute("fileName", employee.getFile1().getOriginalFilename());
		model.addAttribute("FileSize", employee.getFile1().getSize());

		return "file_upload";
	}

}
