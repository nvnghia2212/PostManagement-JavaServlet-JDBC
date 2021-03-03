package com.javajdbc.service;

import com.javajdbc.model.UserModel;

public interface IUserService {
	UserModel findByUserNameAndPassword (String userName, String password);
}
