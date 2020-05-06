package com.techleads.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUpload {
	private Integer id;
	private String name;
	private String filePath;
	private byte[] fileContent;
	private String contentType;
	private Integer userId;

}
