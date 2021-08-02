package com.project.model;

import java.io.Serializable;

public class Person implements Serializable {
	
	private static final long serialVersionUID = -43235643356543L;
	private String name;
	private int age;
	private transient String nickName; // 직렬화에서 제외핧 필드
	
	public Person(String name, int age, String nickName) {
		this.name = name;
		this.age = age;
		this.nickName = nickName;
	}
	
	public void printPersonInfo() {
		System.out.println("name : " + name + " age : " + age + " nickName : " + nickName);
	}

}
