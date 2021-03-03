package com.javajdbc.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.javajdbc.dao.ICategoryDAO;
import com.javajdbc.model.CategoryModel;
import com.javajdbc.service.ICategoryService;

public class CategoryService implements ICategoryService{

	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> finAll() {
		return categoryDAO.finAll();
	}

	@Override
	public CategoryModel insert(CategoryModel categoryModel) {
		categoryModel.setCreateBy("người thêm");
		categoryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		categoryModel.setModifiedBy("");
		categoryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		Long id = categoryDAO.insert(categoryModel);
		return categoryDAO.finOne(id);
	}

	@Override
	public void delete(Long[] ids) {
		for (long id : ids) {
			categoryDAO.delete(id);
		}
	}

	@Override
	public CategoryModel update(CategoryModel categoryModel) {
		categoryModel.setModifiedBy("người sửa");
		categoryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		categoryDAO.update(categoryModel);
		return categoryDAO.finOne(categoryModel.getId());
	}
	
//	@Override
//	public CategoryModel finOne(Long id) {
//		return categoryDAO.finOne(id);
//	}
}
