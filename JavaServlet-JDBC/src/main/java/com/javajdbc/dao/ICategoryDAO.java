package com.javajdbc.dao;

import java.util.List;
import com.javajdbc.model.CategoryModel;

public interface ICategoryDAO extends IGenericDAO<CategoryModel> {
	
	List<CategoryModel> finAll();
	Long insert(CategoryModel categoryModel);
	
//	update không cần giá trị trả về vì sử dụng id cũ
	void update (CategoryModel categoryModel);
	void delete(Long id);
	
//	Dùng để get lại model hỗ trợ cho insert và update khi cần get lại model thông qua id
	CategoryModel finOne(Long id);
	CategoryModel finOneByCode(String code);
}
