package com.javajdbc.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.javajdbc.dao.IGenericDAO;
import com.javajdbc.mapper.RowMapper;

public class AbstractDAO<T> implements IGenericDAO<T> {

//	load driver và kết nối csdl
	public Connection getConnection () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/java_jdbc";
			String user = "root";
			String password = "nghia1197614";

			return DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	private void setParam(PreparedStatement preStatement, Object[] params) {
		for (int i = 0; i < params.length; i++) {
			Object parameter = params[i];
			int index = i + 1;
			try {
				if (parameter instanceof Integer) {
					preStatement.setInt(index, (Integer) parameter);
				}else if (parameter instanceof Long) {
					preStatement.setLong(index, (Long) parameter);
				}else if (parameter instanceof String) {
					preStatement.setString(index, (String) parameter);
				}else if (parameter instanceof Timestamp) {
					preStatement.setTimestamp(index, (Timestamp) parameter);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public <T> List<T> querySQL(String sql, RowMapper<T> rowMapper, Object... params) {
		List<T> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		try {
// Mở connection và thực thi câu tri vấn
			connection = getConnection();
			if(connection != null) {
				preStatement = connection.prepareStatement(sql);
				setParam(preStatement, params);
				resultSet = preStatement.executeQuery();
//---------------------------------------------------------				
// Gán dữ liệu vào model 
				while(resultSet.next()) {
					result.add(rowMapper.mapRow(resultSet));
				}
				return result;
			}else {
				return null;
				}
		} catch (SQLException e) {
			return null;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(preStatement != null) {
					preStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	@Override
	public Long insertSQL(String sql, Object... params) {
		Connection connection = null;
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		Long id = null;
		try {
// Mở connection và thực thi câu truy vấn
			connection = getConnection();
			connection.setAutoCommit(false);
			preStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			setParam(preStatement, params);
			preStatement.executeUpdate();
			
// (Statement.RETURN_GENERATED_KEYS) Get id 
			resultSet = preStatement.getGeneratedKeys();
			if (resultSet.next()) {
// 		Không thể dùng get cột id = resultSet.getLong("id");
				id = resultSet.getLong(1);
			}
			connection.commit();
//--------------------------------------------------------
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(preStatement != null) {
					preStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
		
	}

	@Override
	public void updateAndDeleteSQL(String sql, Object... params) {
		Connection connection = null;
		PreparedStatement preStatement = null;
//		ResultSet resultSet = null;
		try {
// Mở connection và thực thi câu truy vấn
			connection = getConnection();
			connection.setAutoCommit(false);
			preStatement = connection.prepareStatement(sql);
			setParam(preStatement, params);
			preStatement.executeUpdate();
			connection.commit();
//-------------------------------------------------------			
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally{
			try {
				if(connection != null) {
					connection.close();
				}
				if(preStatement != null) {
					preStatement.close();
				}
				/*
				 * if(resultSet != null) { resultSet.close(); }
				 */
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
