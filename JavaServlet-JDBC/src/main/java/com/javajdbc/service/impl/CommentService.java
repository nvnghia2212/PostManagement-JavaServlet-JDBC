package com.javajdbc.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.javajdbc.dao.ICommentDAO;
import com.javajdbc.model.CommentModel;
import com.javajdbc.service.ICommentService;

public class CommentService implements ICommentService {

	@Inject
	ICommentDAO commentDAO;
	
	@Override
	public List<CommentModel> findAll() {
		return commentDAO.findAll();
	}

	@Override
	public CommentModel insert(CommentModel commentModel) {
		commentModel.setCreateBy("người thêm");
		commentModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		commentModel.setModifiedBy("");
		commentModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		Long id = commentDAO.insert(commentModel);
		return commentDAO.findOne(id);
	}

	@Override
	public CommentModel update(CommentModel commentModel) {
		commentModel.setModifiedBy("người sửa");
		commentModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		commentDAO.update(commentModel);
		return commentDAO.findOne(commentModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			commentDAO.delete(id);
		}	
	}

}
