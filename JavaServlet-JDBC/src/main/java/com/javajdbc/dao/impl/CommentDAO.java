package com.javajdbc.dao.impl;

import java.util.List;

import com.javajdbc.dao.ICommentDAO;
import com.javajdbc.mapper.CommentMapper;
import com.javajdbc.model.CommentModel;

public class CommentDAO extends AbstractDAO<CommentModel> implements ICommentDAO{

	@Override
	public List<CommentModel> findAll() {
		String sql = "SELECT * FROM Comment";
		return querySQL(sql, new CommentMapper());
	}

	@Override
	public Long insert(CommentModel commentModel) {
		String sql = "INSERT INTO Comment (content, user_id, new_id, createdby, createddate, modifiedby, modifieddate) VALUE (?,?,?,?,?,?,?)";
		return insertSQL(sql, commentModel.getContent(),
				commentModel.getUserId(),
				commentModel.getNewId(),
				commentModel.getCreateBy(),
				commentModel.getCreatedDate(),
				commentModel.getModifiedBy(),
				commentModel.getModifiedDate());
	}

	@Override
	public void update(CommentModel commentModel) {
		String sql = "UPDATE comment SET content = ?, user_id = ?, new_id = ?, modifiedby = ?, modifieddate = ? WHERE id = ?";
		updateAndDeleteSQL(sql, commentModel.getContent(),
				commentModel.getUserId(),
				commentModel.getNewId(),
				commentModel.getModifiedBy(),
				commentModel.getModifiedDate(),
				commentModel.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM Comment WHERE id = ?";
		updateAndDeleteSQL(sql, id);
	}

	@Override
	public CommentModel findOne(Long id) {
		String sql = "SELECT * FROM Comment WHERE id = ?";
		List<CommentModel> commentModel = querySQL(sql, new CommentMapper(), id);
		return commentModel.isEmpty() ? null : commentModel.get(0);
	}

}
