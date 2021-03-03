package com.javajdbc.dao;

import com.javajdbc.model.UserModel;

public interface IUserDAO extends IGenericDAO<UserModel> {
	UserModel findByUserNameAndPassword (String userName, String password);
}
