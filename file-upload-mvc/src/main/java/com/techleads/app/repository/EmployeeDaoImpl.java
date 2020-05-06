package com.techleads.app.repository;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.techleads.app.commons.DBQueries;
import com.techleads.app.model.Employee;
import com.techleads.app.model.FileUpload;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	private String LOCATION = "C:\\Users\\DELL\\Desktop\\uploadFiles\\";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public FileUpload downLoadFileById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveEmployee(Employee employee) throws Exception {
		try {
			KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
			SqlParameterSource paramSource = new MapSqlParameterSource().addValue("name", employee.getName())
					.addValue("address", employee.getAddress()).addValue("uuid", employee.getUuidCode());
			int update = namedParameterJdbcTemplate.update(DBQueries.INSERT_USERS, paramSource, generatedKeyHolder,
					new String[] { "ID" });

			Integer gentedKeyUserId = generatedKeyHolder.getKey().intValue();
			employee.setId(gentedKeyUserId);

			if (!employee.getFile1().isEmpty()) {
				saveFile(employee);
			}
			return update;
		} catch (InvalidDataAccessApiUsageException e) {
			throw e;
		} catch (DataAccessException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int saveFile(Employee employee) throws Exception {

		try {
			File pathFile = new File(LOCATION);
			String fileName = StringUtils.cleanPath(employee.getFile1().getOriginalFilename());
			MultipartFile multipartFile = employee.getFile1();
			FileUpload fileUpload = new FileUpload();

			fileUpload.setName(fileName);
			fileUpload.setFileContent(multipartFile.getBytes());
			fileUpload.setContentType(multipartFile.getContentType());
			fileUpload.setFilePath(pathFile + "" + fileName);
			fileUpload.setUserId(employee.getId());

			KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
			SqlParameterSource paramSource = new MapSqlParameterSource().addValue("fileName", fileUpload.getName())
					.addValue("file_content", fileUpload.getFileContent())
					.addValue("filePath", fileUpload.getFilePath()).addValue("contentType", fileUpload.getContentType())
					.addValue("userId", fileUpload.getUserId());
			int update = namedParameterJdbcTemplate.update(DBQueries.INSERT_FILE_DATA, paramSource, generatedKeyHolder,
					new String[] { "ID" });
			return update;
		} catch (DataAccessException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public Employee findEmpByMaxId() throws Exception {
		try {
			SqlParameterSource paramSource = new MapSqlParameterSource();
			Employee emp = new Employee();
			namedParameterJdbcTemplate.query(DBQueries.SELECT_MAX_ID_RECORD, paramSource,
					new ResultSetExtractor<Employee>() {

						@Override
						public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
							while (rs.next()) {
								emp.setId(rs.getInt("ID"));
								emp.setName(rs.getString("NAME"));
								emp.setAddress(rs.getString("CITY"));
								emp.setUuidCode(rs.getString("UUID_CODE"));
								FileUpload fileUpload = new FileUpload();
								fileUpload.setId(rs.getInt("FID"));
								fileUpload.setName(rs.getString("FNAME"));
								fileUpload.setFilePath(rs.getString("FILE_PATH"));
								fileUpload.setContentType(rs.getString("CONTENT_TYP"));
								fileUpload.setUserId(rs.getInt("USER_ID"));
								emp.setFileUpload(fileUpload);
							}
							return emp;
						}
					});
			return emp;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public FileUpload downLoadFileById(String uuidCode) throws Exception {

		try {
			SqlParameterSource paramSource = new MapSqlParameterSource().addValue("uuidCode", uuidCode);
			FileUpload file = new FileUpload();
			namedParameterJdbcTemplate.query(DBQueries.FILE_DOWNLOAD_BY_UUID, paramSource,
					new ResultSetExtractor<FileUpload>() {

						@Override
						public FileUpload extractData(ResultSet rs) throws SQLException, DataAccessException {
							while (rs.next()) {
								file.setFileContent(rs.getBytes("FILE_UPLOAD"));
								file.setName(rs.getString("NAME"));
								file.setFilePath(rs.getString("FILE_PATH"));
								file.setContentType(rs.getString("CONTENT_TYP"));
							}
							return file;
						}
					});
			return file;
		} catch (DataAccessException e) {
			throw e;
		}
	}
}
