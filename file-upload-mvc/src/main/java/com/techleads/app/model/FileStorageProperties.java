package com.techleads.app.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@ConfigurationProperties(prefix = "file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileStorageProperties {
	 private String uploadDir;

}
