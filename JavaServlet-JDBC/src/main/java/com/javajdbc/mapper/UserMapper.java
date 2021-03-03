package com.javajdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javajdbc.model.RoleModel;
import com.javajdbc.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		try {
			UserModel userModel = new UserModel();
			userModel.setId(resultSet.getLong("id"));
			userModel.setUserName(resultSet.getString("username"));
			userModel.setPassword(resultSet.getString("password"));
			userModel.setFullName(resultSet.getString("fullname"));
			userModel.setStatus(resultSet.getInt("status"));
			userModel.setRoleId(resultSet.getLong("roleid"));
			userModel.setCreateBy(resultSet.getString("createdby"));
			userModel.setCreatedDate(resultSet.getTimestamp("createddate"));
			userModel.setModifiedBy(resultSet.getString("modifiedby"));
			userModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			
			RoleModel roleModel = new RoleModel();
			roleModel.setCode(resultSet.getString("code"));
			roleModel.setName(resultSet.getString("name"));
			
			userModel.setRole(roleModel);
			return userModel;
		} catch (SQLException e) {
			return null;
		}
	}

}
