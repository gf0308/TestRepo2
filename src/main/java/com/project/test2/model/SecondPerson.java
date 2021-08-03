package com.project.test2.model;

import java.io.Serializable;

public class SecondPerson implements Serializable {
	
	private static final long serialVersionUID = -1234567854L;
	
	private String name;
	private int age;
	private transient String job;
	
	public SecondPerson(String name, int age, String job) {
		this.name = name;
		this.age = age;
		this.job = job;
	}
	
	public void printFields() {
		System.out.println("name: "+name + " age: "+age+" job: "+job);
	}
	
}
