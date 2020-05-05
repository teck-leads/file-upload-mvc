package com.techleads.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.techleads.app.model.Employee;
import com.techleads.app.model.FileUpload;
import com.techleads.app.repository.FileUploadDaoImpl;

@Controller
public class FileUploadController {
	//https://examples.javacodegeeks.com/enterprise-java/spring/file-upload-database-persistence-spring-framework/
	private String LOCATION = "C:\\Users\\DELL\\Desktop\\uploadFiles\\";
	@Autowired
	private FileUploadDaoImpl fileUploadDaoImpl;

	@RequestMapping(value = "/uploadfile", method = RequestMethod.GET)
	public String showUploadForm(@ModelAttribute("empCmd") Employee employee) {
		return "file_upload";
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("empCmd") Employee employee, Model model)
			throws Exception {
		model.addAttribute("fileName", employee.getFile1().getOriginalFilename());
		model.addAttribute("FileSize", employee.getFile1().getSize());
		employee= storeFileLocalPath(employee);
		model.addAttribute("filePath", employee.getFilePath());
		return "file_upload";
	}

	private Employee storeFileLocalPath(Employee employee) throws Exception {
		
		
		FileUpload fileupload =new FileUpload();
		boolean fileUploaded = false;
		MultipartFile multipartFile = employee.getFile1();
		if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			File pathFile = new File(LOCATION);
			// check if directory exist, if not, create directory if
			if (!pathFile.exists()) {
				pathFile.mkdir();
			}
			// create the actual file
			//pathFile = new File(LOCATION + fileName);
			// save the actual file
			byte[] bytes = multipartFile.getBytes();
			
			
			fileupload.setId(100);
			fileupload.setName(fileName);
			fileupload.setFileContent(bytes);
			fileupload.setFilePath(LOCATION + fileName);
			
			fileUploadDaoImpl.saveFile(fileupload);
			Path path = Paths.get(LOCATION + fileName);
			Files.write(path, bytes);

			// multipartFile.transferTo(pathFile);}
			fileUploaded = true;
			employee.setFilePath(String.valueOf(path));
		}
		return employee;
	}
	
	 @GetMapping("/downloadFile/{fileId}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws NumberFormatException, Exception {
	        // Load file from database
		 FileUpload download = fileUploadDaoImpl.downLoadFileById(Integer.parseInt(fileId));
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType("application/octet-stream"))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + download.getName() + "\"")
	                .body(new ByteArrayResource(download.getFileContent()));
	    }

}
