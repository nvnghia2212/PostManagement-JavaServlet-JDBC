package com.javajdbc.dao.impl;

import java.util.List;

import com.javajdbc.dao.IUserDAO;
import com.javajdbc.mapper.UserMapper;
import com.javajdbc.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPassword(String userName, String password) {
		String sql = "SELECT * FROM user JOIN role ON user.roleid = role.id WHERE username = ? AND password = ?";
		List<UserModel> userModels = querySQL(sql, new UserMapper(), userName, password);
		return userModels.isEmpty() ? null : userModels.get(0);
	}

}
