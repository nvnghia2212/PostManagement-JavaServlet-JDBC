package com.javajdbc.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.javajdbc.dao.ICategoryDAO;
import com.javajdbc.dao.INewDAO;
import com.javajdbc.model.NewModel;
import com.javajdbc.service.ICategoryService;
import com.javajdbc.service.INewService;

public class NewService implements INewService {

	@Inject
	private INewDAO newDAO;
	
	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<NewModel> findAll() {
		return newDAO.findAll();
	}

	@Override
	public NewModel insert(NewModel newModel) {
		newModel.setCreateBy("người thêm");
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newModel.setModifiedBy("");
		newModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		Long categoryId = (categoryDAO.finOneByCode(newModel.getCategoryCode())).getId();
		newModel.setCategoryId(categoryId);
		Long id = newDAO.insert(newModel);
		return newDAO.findOne(id);
	}

	@Override
	public NewModel update(NewModel newModel) {
		newModel.setModifiedBy("người sửa");
		newModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		Long categoryId = (categoryDAO.finOneByCode(newModel.getCategoryCode())).getId();
		newModel.setCategoryId(categoryId);
		newDAO.update(newModel);
		return newDAO.findOne(newModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			newDAO.delete(id);
		}		
	}

	@Override
	public NewModel findOne(Long id) {
		NewModel newModel = newDAO.findOne(id);
		String categoryCode = (categoryDAO.finOne(newModel.getCategoryId())).getCode();
		newModel.setCategoryCode(categoryCode);
		return newModel;
	}

}
