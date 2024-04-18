package com.dao;

import java.util.List;

import com.model.User;

public interface IUserDao 
{
	User findUserById(int uid);
	int findUid(String account,String password);
	List<User> getAllUsers();
	int addUser(User user);
	User getUserById(int uid);
	void updateUser(User user);
	void deleteUser(int uid);
}
