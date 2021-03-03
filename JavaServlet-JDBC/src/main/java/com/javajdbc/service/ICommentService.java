package com.javajdbc.service;

import java.util.List;

import com.javajdbc.model.CommentModel;

public interface ICommentService {
	List<CommentModel> findAll();
	CommentModel insert(CommentModel commentModel);
	CommentModel update(CommentModel commentModel);
	void delete(Long[] ids);
}
