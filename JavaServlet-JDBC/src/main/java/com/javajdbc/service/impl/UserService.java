package com.javajdbc.service.impl;

import javax.inject.Inject;

import com.javajdbc.dao.IUserDAO;
import com.javajdbc.dao.impl.UserDAO;
import com.javajdbc.model.UserModel;
import com.javajdbc.service.IUserService;

public class UserService implements IUserService {
	
	@Inject
	private IUserDAO userDAO;

	@Override
	public UserModel findByUserNameAndPassword(String userName, String password) {
		return userDAO.findByUserNameAndPassword(userName, password);
	}

}
