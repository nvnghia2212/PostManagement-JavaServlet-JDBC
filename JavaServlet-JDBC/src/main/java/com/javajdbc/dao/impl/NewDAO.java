package com.javajdbc.dao.impl;

import java.util.List;

import com.javajdbc.dao.INewDAO;
import com.javajdbc.mapper.NewMapper;
import com.javajdbc.model.NewModel;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findAll() {
		String sql = "SELECT * FROM news";
		return querySQL(sql, new NewMapper());
	}

	@Override
	public Long insert(NewModel newModel) {
		String sql = "INSERT INTO news (title, thumbnail, shortdescription, content, category_id, createdby, createddate, modifiedby, modifieddate) VALUE (?,?,?,?,?,?,?,?,?)";
		return insertSQL(sql, newModel.getTitle(), 
				newModel.getThumbnail(), 
				newModel.getShortDescription(),
				newModel.getContent(),
				newModel.getCategoryId(),
				newModel.getCreateBy(),
				newModel.getCreatedDate(),
				newModel.getModifiedBy(),
				newModel.getModifiedDate());
	}

	@Override
	public void update(NewModel newModel) {
		String sql = "UPDATE news SET title = ?, thumbnail = ?, shortdescription = ?, content = ?, category_id = ?, modifiedby = ?, modifieddate = ? WHERE id = ?";
		updateAndDeleteSQL(sql, newModel.getTitle(), 
				newModel.getThumbnail(), 
				newModel.getShortDescription(),
				newModel.getContent(),
				newModel.getCategoryId(),
				newModel.getModifiedBy(),
				newModel.getModifiedDate(),
				newModel.getId());
	}

	@Override
	public void delete(Long id) {
		
		String sqlCom = "DELETE FROM Comment WHERE new_id = ?";
		updateAndDeleteSQL(sqlCom, id);
		
		String sql = "DELETE FROM news WHERE id = ?";
		updateAndDeleteSQL(sql, id);
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewModel> newModel = querySQL(sql, new NewMapper(), id);
		return newModel.isEmpty() ? null : newModel.get(0);
	}

}
