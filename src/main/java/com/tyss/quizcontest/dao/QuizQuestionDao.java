package com.tyss.quizcontest.dao;
import java.util.List;
import com.tyss.quizcontest.bean.QuizQuestions;

public interface QuizQuestionDao {
	
	void insertQuestion(QuizQuestions questions);
	List<QuizQuestions> getQuestionBySubject(String subName);

}
