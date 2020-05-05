package com.techleads.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.techleads.app.model.FileUpload;

@Repository
public class FileUploadDaoImpl {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int saveFile(FileUpload fileUpload) throws Exception {
		try {
			Object[] params = { fileUpload.getId(), fileUpload.getName(), fileUpload.getFileContent(),
					fileUpload.getFilePath() };

			String QRY = "INSERT INTO UPLOADFILE ( ID,NAME, FILE_UPLOAD, FILE_PATH ) VALUES ( ?, ?, ?, ? )";
			int update = jdbcTemplate.update(QRY, params);
			return update;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	public FileUpload downLoadFileById(Integer id) throws Exception {

		try {
			Object[] params = { id };
			FileUpload file = new FileUpload();
			jdbcTemplate.query("SELECT FILE_UPLOAD, NAME FROM UPLOADFILE WHERE ID=?", params, new ResultSetExtractor<FileUpload>() {

				@Override
				public FileUpload extractData(ResultSet rs) throws SQLException, DataAccessException {
					while (rs.next()) {
						file.setFileContent(rs.getBytes("FILE_UPLOAD"));
						file.setName(rs.getString("NAME"));
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
