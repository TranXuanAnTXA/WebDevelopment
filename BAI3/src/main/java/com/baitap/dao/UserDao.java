package com.baitap.dao;

import com.baitap.model.User;

public interface UserDao {
	User get(String username);
	
	void insert(User user);
	User login(String username, String password);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	boolean updateProfile(User user);
}