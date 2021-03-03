package com.javajdbc.dao;

import java.util.List;

import com.javajdbc.mapper.RowMapper;

public interface IGenericDAO<T> {
	<T>List<T> querySQL(String sql, RowMapper<T> rowMapper, Object... params);
	Long insertSQL(String sql, Object... params);
	void updateAndDeleteSQL(String sql, Object... params);
}
