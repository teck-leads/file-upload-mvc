package com.techleads.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techleads.app.model.Employee;
import com.techleads.app.model.FileUpload;
import com.techleads.app.service.EmployeeService;

@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String showUploadForm(@ModelAttribute("empCmd") Employee employee) {
		return "file_upload";
	}

	
	@RequestMapping(value ={"/register"}, method = RequestMethod.POST)
	public String processEmpForm(@ModelAttribute("empCmd") Employee employee,Model model) throws Exception {
		int saveFile = employeeService.saveFile(employee);
		
		if(saveFile>0) {
			model.addAttribute("status","Success");
			return "redirect:/emp/saved-emp";
		}else {
			model.addAttribute("status","Not Saved");
		}
		return "file_upload";
	}
	
	@RequestMapping(value = {"/saved-emp"}, method = RequestMethod.GET)
	public String savedEmp(@ModelAttribute("empCmd") Employee employee,Model model) throws Exception {
		
		employee = employeeService.findEmpByMaxId();
		model.addAttribute(employee);
		return "result";
	}
	
	@GetMapping("/downloadFile/{uuidCode}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("uuidCode") String uuidCode) throws NumberFormatException, Exception {
        // Load file from database
	 FileUpload download = employeeService.downLoadFileById(uuidCode);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + download.getName() + "\"")
                .body(new ByteArrayResource(download.getFileContent()));
    }

	
	
}
