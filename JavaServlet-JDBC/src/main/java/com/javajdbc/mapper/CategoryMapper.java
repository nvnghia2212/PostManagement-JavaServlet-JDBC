package com.javajdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javajdbc.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		try {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setId(resultSet.getLong("id"));
			categoryModel.setName(resultSet.getString("name"));
			categoryModel.setCode(resultSet.getString("code"));
			categoryModel.setCreateBy(resultSet.getString("createdby"));
			categoryModel.setCreatedDate(resultSet.getTimestamp("createddate"));
			categoryModel.setModifiedBy(resultSet.getString("modifiedby"));
			categoryModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			return categoryModel;
		} catch (SQLException e) {
			return null;
		}
	}
}
