package com.techleads.app.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.techleads.app.model.Employee;
import com.techleads.app.model.FileUpload;
import com.techleads.app.repository.EmployeeDao;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	public int saveFile(Employee employee) throws Exception {
		try {
			employee.setUuidCode(String.valueOf(UUID.randomUUID()));
			int saveEmployee = employeeDao.saveEmployee(employee);
			return saveEmployee;
		} catch (DataAccessException e) {
			throw e;
		}

	}
	
	public Employee findEmpByMaxId() throws Exception {
		try {
			Employee findEmpByMaxId = employeeDao.findEmpByMaxId();
			
			return findEmpByMaxId;
		} catch (Exception e) {
			throw e;
		}

	}
	
	public FileUpload downLoadFileById(String uuidCode) throws Exception {
		try {
			FileUpload downLoadFileByUUId = employeeDao.downLoadFileById(uuidCode);
			return downLoadFileByUUId;
		} catch (Exception e) {
			throw e;
		}
	}

}
