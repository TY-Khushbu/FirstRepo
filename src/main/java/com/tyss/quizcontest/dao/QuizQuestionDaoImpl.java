package com.tyss.quizcontest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.tyss.quizcontest.bean.QuizQuestions;

public class QuizQuestionDaoImpl implements QuizQuestionDao {
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction transaction = null;

	static void entityMethod() {
		entityManagerFactory = Persistence.createEntityManagerFactory("quizmap");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void insertQuestion(QuizQuestions questions) {
		try {
			entityMethod();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(questions);
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
	public List<QuizQuestions> getQuestionBySubject(String subName) {
		entityManagerFactory = Persistence.createEntityManagerFactory("quizmap");
		entityManager = entityManagerFactory.createEntityManager();
		String select = "from QuizQuestions where quizTopic= :subName";
		Query query = entityManager.createQuery(select);
		query.setParameter("quizTopic", subName);
		List<QuizQuestions> qList = query.getResultList();
		for (QuizQuestions question : qList) {

			System.out.println(question.getQuestion());

		}

		return qList;
	}

}
