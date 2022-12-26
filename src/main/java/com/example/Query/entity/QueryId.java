package com.example.Query.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QueryId implements Serializable {

	private String caption; // �ݨ��W��
	
	private String question; // �ݨ��D��

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
