package com.javajdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javajdbc.model.RoleModel;

public class RoleMapper implements RowMapper<RoleModel> {

	@Override
	public RoleModel mapRow(ResultSet resultSet) {
		try {
			RoleModel roleModel = new RoleModel();
			roleModel.setId(resultSet.getLong("id"));
			roleModel.setName(resultSet.getString("name"));
			roleModel.setCode(resultSet.getString("code"));
			roleModel.setCreateBy(resultSet.getString("createdby"));
			roleModel.setCreatedDate(resultSet.getTimestamp("createddate"));
			roleModel.setModifiedBy(resultSet.getString("modifiedby"));
			roleModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
