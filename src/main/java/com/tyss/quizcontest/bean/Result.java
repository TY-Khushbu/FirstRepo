package com.tyss.quizcontest.bean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;


@Entity
@Data
public class Result {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resId;
	
	private int yourScore;
	private int attemptedQuestion;
	private int correctAnsweres;

	@OneToOne
	User user=new User();
}
