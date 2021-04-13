package com.tyss.quizcontest.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.tyss.quizcontest.bean.QuizQuestions;
import com.tyss.quizcontest.bean.QuizTopic;
import com.tyss.quizcontest.bean.Result;

public class QuizTopicDaoImpl implements QuizTopicDao {

	int questionNumber = 0;
	int userScore = 0;
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction transaction = null;

	static void entityMethod() {
		entityManagerFactory = Persistence.createEntityManagerFactory("quizmap");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void insertTopic(QuizTopic qt) {
		try {
			entityMethod();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(qt);
			System.out.println("Data Inserted Successfully");
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			try {
				if (entityManagerFactory != null) {
					entityManagerFactory.close();
				}
				if (entityManager != null) {
					entityManager.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void getTopic() {
		entityManagerFactory = Persistence.createEntityManagerFactory("quizmap");
		entityManager = entityManagerFactory.createEntityManager();
		String select = "from QuizTopic";
		Query query = entityManager.createQuery(select);
		List<QuizTopic> quizSub = query.getResultList();
		for (QuizTopic subject : quizSub) {
			System.out.println("Press " + subject.getTopicId() + " for " + subject.getTopicName());
		}

		try (Scanner sc = new Scanner(System.in);) {

			int opt = sc.nextInt();
			switch (opt) {
			case 1:
				List<QuizQuestions> javaQList = quizSub.get(0).getQuestion();
				loadNextQuestion(javaQList);
				break;
			case 2:
				List<QuizQuestions> kotlinQList = quizSub.get(1).getQuestion();
				loadNextQuestion(kotlinQList);
				break;
			case 3:
				List<QuizQuestions> pythonQList = quizSub.get(2).getQuestion();
				loadNextQuestion(pythonQList);
				break;

			default:
				System.out.println("You Have Entered Invalid Number");
			}
		}
	}

	private void playQuiz(List<QuizQuestions> quesList, int opt) {
		if (questionNumber == quesList.size()) {
			System.out.println("Quiz ends here !!");
			System.out.println("User score = " + userScore);
			return;
		}
		switch (opt) {
		case 1:
			String userOptionValue = quesList.get(questionNumber).getOpt1();
			String correctOption = quesList.get(questionNumber).getCorrectOpt();
			onAnswerSubmitted(userOptionValue, correctOption);
			questionNumber++;
			loadNextQuestion(quesList);
			break;
		case 2:
			onAnswerSubmitted(quesList.get(questionNumber).getOpt2(), quesList.get(questionNumber).getCorrectOpt());
			questionNumber++;
			loadNextQuestion(quesList);
			break;
		case 3:
			onAnswerSubmitted(quesList.get(questionNumber).getOpt3(), quesList.get(questionNumber).getCorrectOpt());
			questionNumber++;
			loadNextQuestion(quesList);
			break;
		case 4:
			onAnswerSubmitted(quesList.get(questionNumber).getOpt4(), quesList.get(questionNumber).getCorrectOpt());
			questionNumber++;
			loadNextQuestion(quesList);
			break;

		default:
			System.out.println("You Have Entered Invalid Number");
		}

	}

	private void loadNextQuestion(List<QuizQuestions> quesList) {
		if (questionNumber == quesList.size()) {
			System.out.println("Quiz ends here !!");
			System.out.println("User score = " + userScore);
			insertResultData();
			return;
		}

		int quesNo = questionNumber + 1;
		System.out.println("Please input the correct option number");
		System.out.println("Ques: " + quesNo + " " + quesList.get(questionNumber).getQuestion());
		System.out.println("1." + quesList.get(questionNumber).getOpt1());
		System.out.println("2." + quesList.get(questionNumber).getOpt2());
		System.out.println("3." + quesList.get(questionNumber).getOpt2());
		System.out.println("4." + quesList.get(questionNumber).getOpt4());
		Scanner sc = new Scanner(System.in);
		int userInput = sc.nextInt();
		playQuiz(quesList, userInput);
	}

	private int onAnswerSubmitted(String userSelectedOption, String correctOption) {
		if (userSelectedOption.equalsIgnoreCase(correctOption)) {
			userScore++;
		}
		return userScore;
	}
	
	private void insertResultData() {
		
		Result result = new Result();
		result.getUser().setUserId(UserDaoImpl.userId);
		result.setAttemptedQuestion(questionNumber);
		result.setCorrectAnsweres(userScore);
		result.setYourScore(userScore);
		ResultDao resultDao=new ResultDaoImpl();
		resultDao.insertResult(result);
		resultDao.displayUserScore(result.getUser());
		
	}
}
