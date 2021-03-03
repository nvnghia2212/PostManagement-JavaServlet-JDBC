package com.javajdbc.dao;

import java.util.List;

import com.javajdbc.model.CommentModel;

public interface ICommentDAO extends IGenericDAO<CommentModel> {
	List<CommentModel> findAll();
	Long insert(CommentModel commentModel);
	void update(CommentModel commentModel);
	void delete(Long id);
	
	CommentModel findOne(Long id);
}
