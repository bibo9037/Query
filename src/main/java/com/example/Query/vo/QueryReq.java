package com.example.Query.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryReq {
	
	@JsonProperty("caption")
	private String caption; // 問卷名稱

	@JsonProperty("content")
	private String content; // 描述內容

	@JsonProperty("start_date")
	private Date startDate; // 開始時間

	@JsonProperty("end_date")
	private Date endDate; // 結束時間

	@JsonProperty("state")
	private String state; // 狀態
	
	@JsonProperty("question")
	private String question; // 問卷題目
	
	@JsonProperty("options")
	private String options; // 問卷選項
	
	@JsonProperty("user_name")
	private String userName;	// 填寫者名稱
	
	@JsonProperty("new_caption")
	private String newCaption;	// 編輯的新問卷名稱
	
	@JsonProperty("new_content")
	private String newContent; // 描述內容
	
	@JsonProperty("new_question")
	private String newQuestion;	// 編輯的新問卷問題 
	
	@JsonProperty("new_options")
	private String newOptions;	// 編輯的新問卷選項
	
	public QueryReq() {
		
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNewCaption() {
		return newCaption;
	}

	public void setNewCaption(String newCaption) {
		this.newCaption = newCaption;
	}

	public String getNewQuestion() {
		return newQuestion;
	}

	public void setNewQuestion(String newQuestion) {
		this.newQuestion = newQuestion;
	}

	public String getNewOptions() {
		return newOptions;
	}

	public void setNewOptions(String newOptions) {
		this.newOptions = newOptions;
	}

	public String getNewContent() {
		return newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}
	
}
