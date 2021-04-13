package com.tyss.quizcontest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.tyss.quizcontest.bean.User;

public class UserDaoImpl implements UserDao {

	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction transaction = null;
	public static int userId;

	static void entityMethod() {
		entityManagerFactory = Persistence.createEntityManagerFactory("quizmap");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void insertUser(User user) {
		try {
			entityMethod();
			transaction = entityManager.getTransaction();
            
			transaction.begin();
			entityManager.persist(user);
			System.out.println("Registered Successfully");
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
	public void loginUser(String email, String password) {
		entityManagerFactory = Persistence.createEntityManagerFactory("quizmap");
		entityManager = entityManagerFactory.createEntityManager();
		String select = "from User where email= :email";
		Query query = entityManager.createQuery(select);
		query.setParameter("email", email);
		List<User> user = query.getResultList();
		for (User singleUser : user) {				
			if ((singleUser.getEmail().equalsIgnoreCase(email))
					&& singleUser.getPassword().equalsIgnoreCase(password)) {
				System.out.println("Login Successfull");
				userId=singleUser.getUserId();
				QuizTopicDao quizTopicDao= new QuizTopicDaoImpl();
				quizTopicDao.getTopic();
			} else {
				System.out.println("Invalid username or password");
				break;
			}
		}
	}
}
