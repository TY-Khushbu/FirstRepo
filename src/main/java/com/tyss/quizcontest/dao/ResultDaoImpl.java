package com.tyss.quizcontest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.tyss.quizcontest.bean.Result;
import com.tyss.quizcontest.bean.User;

public class ResultDaoImpl implements ResultDao {
	
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction transaction = null;

	static void entityMethod() {
		entityManagerFactory = Persistence.createEntityManagerFactory("quizmap");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void insertResult(Result result) {
		
		try {
			entityMethod();
			transaction = entityManager.getTransaction();
          
			transaction.begin();
			entityManager.persist(result);
			System.out.println("Thank You, Your quiz has been submitted");
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
	public void displayUserScore(User user) {
		int attemptedQuestions = 0;
		int totalMarks = 0;
		int totalCorrectAnswers = 0;
		
		entityManagerFactory = Persistence.createEntityManagerFactory("quizmap");
		entityManager = entityManagerFactory.createEntityManager();
		String select = "from Result where user= :user";
		Query query = entityManager.createQuery(select);
		query.setParameter("user", user);
		List<Result> resultList = query.getResultList();
		for(Result resultData:resultList) {
			attemptedQuestions = attemptedQuestions+ resultData.getAttemptedQuestion();
			totalMarks = totalMarks + resultData.getYourScore();
			totalCorrectAnswers = totalCorrectAnswers + resultData.getCorrectAnsweres();
		}
		System.out.println("Calculating Results.....");
		System.out.println("Total Questions Attempted = "+attemptedQuestions);
		System.out.println("Total Correct Answers given = " + totalCorrectAnswers);
		System.out.println("Your Score ="+totalMarks);
		
	}

}
