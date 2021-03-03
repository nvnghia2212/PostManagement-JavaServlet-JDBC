package com.javajdbc.service;

import java.util.List;

import com.javajdbc.model.NewModel;

public interface INewService {
	
	List<NewModel> findAll();
	NewModel insert(NewModel newModel);
	NewModel update(NewModel newModel);
	void delete(Long[] ids);
	
	NewModel findOne(Long id);
}
