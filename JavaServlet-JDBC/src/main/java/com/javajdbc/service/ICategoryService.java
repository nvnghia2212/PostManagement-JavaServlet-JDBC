package com.javajdbc.service;

import java.util.List;

import com.javajdbc.model.CategoryModel;

public interface ICategoryService {
	
	List<CategoryModel> finAll();
	CategoryModel insert(CategoryModel categoryModel);
	CategoryModel update(CategoryModel categoryModel);
	void delete( Long[] ids);
//	CategoryModel finOne(Long id);
}
