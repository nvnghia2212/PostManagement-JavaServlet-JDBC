package com.javajdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javajdbc.model.CommentModel;

public class CommentMapper implements RowMapper<CommentModel>{

	@Override
	public CommentModel mapRow(ResultSet resultSet) {
		try {
			CommentModel commentModel = new CommentModel();
			commentModel.setId(resultSet.getLong("id"));
			commentModel.setContent(resultSet.getString("content"));
			commentModel.setUserId(resultSet.getLong("user_id"));
			commentModel.setNewId(resultSet.getLong("new_id"));
			commentModel.setCreateBy(resultSet.getString("createdby"));
			commentModel.setCreatedDate(resultSet.getTimestamp("createddate"));
			commentModel.setModifiedBy(resultSet.getString("modifiedby"));
			commentModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			return commentModel;
		} catch (SQLException e) {
			return null;
		}
	}

}
