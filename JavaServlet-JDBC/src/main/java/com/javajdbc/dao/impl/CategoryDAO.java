package com.javajdbc.dao.impl;

import java.util.List;

import com.javajdbc.dao.ICategoryDAO;
import com.javajdbc.mapper.CategoryMapper;
import com.javajdbc.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
	
	@Override
	public List<CategoryModel> finAll() {	
		String sql = "Select * From Category";
		return querySQL(sql, new CategoryMapper());
	}

	@Override
	public Long insert(CategoryModel categoryModel) {
		String sql = "INSERT INTO Category (name,code,createdby,createddate,modifiedby,modifieddate) VALUE (?,?,?,?,?,?)";
		return insertSQL(sql, 
				categoryModel.getName(),
				categoryModel.getCode(),
				categoryModel.getCreateBy(),
				categoryModel.getCreatedDate(),
				categoryModel.getModifiedBy(),
				categoryModel.getModifiedDate());
	}

	@Override
	public void delete(Long id) {
		String sqlCom = "DELETE comment FROM comment JOIN news ON comment.new_id = news.id WHERE news.category_id = ?";
		updateAndDeleteSQL(sqlCom, id);
		
		String sqlNew = "DELETE FROM News WHERE category_id = ?";
		updateAndDeleteSQL(sqlNew, id);
		
		String sql = "DELETE FROM Category WHERE id = ?";
		updateAndDeleteSQL(sql, id);
	}

	@Override
	public void update(CategoryModel categoryModel) {
		String sql = "UPDATE Category SET code = ?, name = ?, modifiedby = ?, modifieddate = ? WHERE id = ?";
		updateAndDeleteSQL(sql, 
				categoryModel.getCode(),
				categoryModel.getName(),
				categoryModel.getModifiedBy(),
				categoryModel.getModifiedDate(),
				categoryModel.getId());
	}
	
	@Override
	public CategoryModel finOne(Long id) {
		String sql = "Select * from Category where id = ?";
		List<CategoryModel> categoryModel = querySQL(sql, new CategoryMapper(), id);
		return categoryModel.isEmpty() ? null : categoryModel.get(0);
	}

	@Override
	public CategoryModel finOneByCode(String code) {
		String sql = "Select * from Category where code = ?";
		List<CategoryModel> categoryModel = querySQL(sql, new CategoryMapper(), code);
		return categoryModel.isEmpty() ? null : categoryModel.get(0);
	}
}
