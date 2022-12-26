package com.example.Query.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QueryId implements Serializable {

	private String caption; // 問卷名稱
	
	private String question; // 問卷題目

	public QueryId() {

	}

	public QueryId(String caption, String question) {
		this.caption =caption;
		this.question = question;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
}
