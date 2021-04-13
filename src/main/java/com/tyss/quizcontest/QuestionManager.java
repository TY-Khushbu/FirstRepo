package com.tyss.quizcontest;

import java.util.ArrayList;
import java.util.List;

import com.tyss.quizcontest.bean.QuizQuestions;
import com.tyss.quizcontest.dao.QuizQuestionDao;
import com.tyss.quizcontest.dao.QuizQuestionDaoImpl;

public class QuestionManager {

	private final String JAVA = "java";

	void insertAllQuestions(List<QuizQuestions> queList) {

		QuizQuestionDao quizQuestionDao = new QuizQuestionDaoImpl();

		for (QuizQuestions queItem : queList) {
			quizQuestionDao.insertQuestion(queItem);
		}

	}

	public List<QuizQuestions> getJavaQuestions() {
		QuizQuestions questions1 = new QuizQuestions("Java Is what?", "language", "program", "code", "OS",
				"Language");

		QuizQuestions questions2 = new QuizQuestions("Java Is which of below?", "language", "program", "code",
				"OS", "Language");

		QuizQuestions questions3 = new QuizQuestions( "Java Is exactly ?", "language", "program", "code", "OS",
				"Language");

		QuizQuestions questions4 = new QuizQuestions("Java is best defined as ?", "language", "program", "code",
				"OS", "Language");

		QuizQuestions questions5 = new QuizQuestions("Java Is good as ?", "language", "program", "code", "OS",
				"Language");

		ArrayList<QuizQuestions> queList = new ArrayList<QuizQuestions>();
		queList.add(questions1);
		queList.add(questions2);
		queList.add(questions3);
		queList.add(questions4);
		queList.add(questions5);
		return queList;
	}
	
	
	public List<QuizQuestions> getKotlinQuestions() {
		QuizQuestions questions1 = new QuizQuestions("Kotlin Is what?", "language", "program", "code", "OS",
				"Language");

		QuizQuestions questions2 = new QuizQuestions("Kotlin Is which of below?", "language", "program", "code",
				"OS", "Language");

		QuizQuestions questions3 = new QuizQuestions( "Kotlin Is exactly ?", "language", "program", "code", "OS",
				"Language");

		QuizQuestions questions4 = new QuizQuestions("Kotlin is best defined as ?", "language", "program", "code",
				"OS", "Language");

		QuizQuestions questions5 = new QuizQuestions("Kotlin Is good as ?", "language", "program", "code", "OS",
				"Language");

		ArrayList<QuizQuestions> queList = new ArrayList<QuizQuestions>();
		queList.add(questions1);
		queList.add(questions2);
		queList.add(questions3);
		queList.add(questions4);
		queList.add(questions5);
		return queList;
	}
	
	
	public List<QuizQuestions> getPythonQuestions() {
		QuizQuestions questions1 = new QuizQuestions("Python Is what?", "language", "program", "code", "OS",
				"Language");

		QuizQuestions questions2 = new QuizQuestions("Python Is which of below?", "language", "program", "code",
				"OS", "Language");

		QuizQuestions questions3 = new QuizQuestions( "Python Is exactly ?", "language", "program", "code", "OS",
				"Language");

		QuizQuestions questions4 = new QuizQuestions("Python is best defined as ?", "language", "program", "code",
				"OS", "Language");

		QuizQuestions questions5 = new QuizQuestions("Python Is good as ?", "language", "program", "code", "OS",
				"Language");

		ArrayList<QuizQuestions> queList = new ArrayList<QuizQuestions>();
		queList.add(questions1);
		queList.add(questions2);
		queList.add(questions3);
		queList.add(questions4);
		queList.add(questions5);
		return queList;
	}
}
