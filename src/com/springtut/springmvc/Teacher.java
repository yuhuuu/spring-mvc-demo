package com.springtut.springmvc;

import java.util.LinkedHashMap;

public class Teacher {
	private String name;
	private String subject;
	
	private LinkedHashMap<String,String> subjectOptions;
	
	
	public Teacher() {
		this.subjectOptions = new LinkedHashMap<String, String>();
		this.subjectOptions.put("Math", "Mathematics");
		this.subjectOptions.put("English", "English");
		this.subjectOptions.put("Comp", "Computer");
		this.subjectOptions.put("Sci", "Science");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LinkedHashMap<String, String> getSubjectOptions() {
		return subjectOptions;
	}
	
	
}
