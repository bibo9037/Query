package com.example.Query.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryReq {
	
	@JsonProperty("caption")
	private String caption; // �ݨ��W��

	@JsonProperty("content")
	private String content; // �y�z���e

	@JsonProperty("start_date")
	private Date startDate; // �}�l�ɶ�

	@JsonProperty("end_date")
	private Date endDate; // �����ɶ�

	@JsonProperty("state")
	private String state; // ���A
	
	@JsonProperty("question")
	private String question; // �ݨ��D��
	
	@JsonProperty("options")
	private String options; // �ݨ��ﶵ
	
	@JsonProperty("user_name")
	private String userName;	//��g�̦W��
	
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
	
}
