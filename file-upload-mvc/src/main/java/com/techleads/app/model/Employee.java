package com.techleads.app.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private Integer id;
	private String name;
	private String address;
	private MultipartFile file1;
	private MultipartFile file2;
	private String uuidCode;
	
	private FileUpload fileUpload;

}
