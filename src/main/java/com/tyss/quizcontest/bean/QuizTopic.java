package com.tyss.quizcontest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class QuizTopic implements Serializable {

	private int topicId;

	@Id
	private String topicName;

	@OneToMany(mappedBy = "quizTopic")
	private List<QuizQuestions> question = new ArrayList<QuizQuestions>();

}
