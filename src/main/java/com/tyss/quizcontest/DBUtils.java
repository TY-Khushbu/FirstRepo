package com.tyss.quizcontest;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.tyss.quizcontest.bean.QuizQuestions;
import com.tyss.quizcontest.bean.QuizTopic;
import com.tyss.quizcontest.bean.User;
import com.tyss.quizcontest.dao.QuizQuestionDao;
import com.tyss.quizcontest.dao.QuizQuestionDaoImpl;
import com.tyss.quizcontest.dao.QuizTopicDao;
import com.tyss.quizcontest.dao.QuizTopicDaoImpl;
import com.tyss.quizcontest.dao.UserDao;
import com.tyss.quizcontest.dao.UserDaoImpl;

public class DBUtils {

	public void insertQuizData() {
		try {
			QuestionManager questionManager = new QuestionManager();
			QuizTopicDao quizTopicDao = new QuizTopicDaoImpl();

			QuizTopic quizTopicJava = new QuizTopic();
			quizTopicJava.setTopicId(1);
			quizTopicJava.setTopicName("Java");
			quizTopicJava.getQuestion().addAll(questionManager.getJavaQuestions());
			quizTopicDao.insertTopic(quizTopicJava);

			QuizTopic quizTopicKotlin = new QuizTopic();
			quizTopicKotlin.setTopicId(2);
			quizTopicKotlin.setTopicName("Kotlin");
			quizTopicJava.getQuestion().addAll(questionManager.getKotlinQuestions());
			quizTopicDao.insertTopic(quizTopicKotlin);

			QuizTopic quizTopicPython = new QuizTopic();
			quizTopicPython.setTopicId(3);
			quizTopicPython.setTopicName("Python");
			quizTopicJava.getQuestion().addAll(questionManager.getPythonQuestions());
			quizTopicDao.insertTopic(quizTopicPython);

			QuizQuestionDao quizQuestionDao = new QuizQuestionDaoImpl();

			for (QuizQuestions queItem : questionManager.getJavaQuestions()) {
				queItem.getQuizTopic().setTopicName("Java");
				quizQuestionDao.insertQuestion(queItem);
			}

			for (QuizQuestions queItem : questionManager.getKotlinQuestions()) {
				queItem.getQuizTopic().setTopicName("Kotlin");
				quizQuestionDao.insertQuestion(queItem);
			}

			for (QuizQuestions queItem : questionManager.getPythonQuestions()) {
				queItem.getQuizTopic().setTopicName("Python");
				quizQuestionDao.insertQuestion(queItem);
			}
		} catch (Exception e) {

		}
	}

	public void displayAppOptions() {
		int opt=0;
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("Enter your choice");
			System.out.println("Press 1 for Login");
			System.out.println("Press 2 for Sign Up");
			opt = sc.nextInt();

			switch (opt) {
			case 1:
				System.out.println("Enter your email");
				String email = sc.next();
				System.out.println("Enter your password");
				String password = sc.next();
				UserDao userDao = new UserDaoImpl();
				userDao.loginUser(email, password);
				break;
				
			case 2:
				System.out.println("Enter your Name");
				String name = sc.next();
				System.out.println("Enter your Email");
				String userEmail = sc.next();
				System.out.println("Enter your password");
				String userPassword = sc.next();
				User user = new User();
				user.setUname(name);
				user.setEmail(userEmail);
				user.setPassword(userPassword);
				UserDao userDao1 = new UserDaoImpl();
				userDao1.insertUser(user);
				break;

			default:
				System.out.println("You Have Entered Invalid Number");
			}
		} catch (Exception e) {
			System.out.println("Enter valid input");
		}
	}
}
