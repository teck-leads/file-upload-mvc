package com.techleads.app.repository;

import com.techleads.app.model.Employee;
import com.techleads.app.model.FileUpload;

public interface EmployeeDao {
	 int saveFile(Employee employee) throws Exception;
	 FileUpload downLoadFileById(Integer id) throws Exception;
	 int saveEmployee(Employee employee)throws Exception;
	 public Employee findEmpByMaxId()throws Exception;
	 public FileUpload downLoadFileById(String uuidCode) throws Exception;
}
