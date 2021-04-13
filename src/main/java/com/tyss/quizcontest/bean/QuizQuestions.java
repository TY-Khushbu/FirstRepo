package com.tyss.quizcontest.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class QuizQuestions {

	public QuizQuestions() {
		super();
	}

	public QuizQuestions(String question, String opt1,
			String opt2, String opt3, String opt4,
			String correctOpt) {
		super();
		this.question = question;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.opt4 = opt4;
		this.correctOpt = correctOpt;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int questionId;
	private String question;
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private String correctOpt;
	
	@ManyToOne
	private QuizTopic quizTopic = new QuizTopic();

}
