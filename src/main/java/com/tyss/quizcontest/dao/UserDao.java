package com.tyss.quizcontest.dao;

import com.tyss.quizcontest.bean.User;

public interface UserDao {
	void insertUser(User user);
	void loginUser(String email,String password);
	
}
