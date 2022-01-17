package com.springboot.practice.core.vo;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private int testscore;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTestscore() {
		return testscore;
	}
	public void setTestscore(int testscore) {
		this.testscore = testscore;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", testscore=" + testscore + "]";
	}
	
	
}
