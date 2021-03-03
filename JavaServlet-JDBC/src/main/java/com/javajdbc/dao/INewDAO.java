package com.javajdbc.dao;

import java.util.List;

import com.javajdbc.model.NewModel;

public interface INewDAO extends IGenericDAO<NewModel> {
	
	List<NewModel> findAll();
	Long insert(NewModel newModel);
	
//	update không cần giá trị trả về vì sử dụng id cũ
	void update(NewModel newModel);
	void delete(Long id);
	
//	Dùng để get lại model hỗ trợ cho insert và update khi cần get lại model thông qua id
	NewModel findOne(Long id);
}
