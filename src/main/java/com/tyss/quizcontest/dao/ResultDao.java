package com.tyss.quizcontest.dao;

import com.tyss.quizcontest.bean.Result;
import com.tyss.quizcontest.bean.User;

public interface ResultDao {
	void insertResult(Result result);
	void displayUserScore(User user);
	
}
