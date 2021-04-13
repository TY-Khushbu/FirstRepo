package com.tyss.quizcontest.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private int userId;
	private String uname;
	private String email;
    private String password;
    
   // @OneToOne
    //private Result result= new Result();
    

}
