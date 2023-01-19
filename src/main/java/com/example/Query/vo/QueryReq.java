package com.example.Query.vo;

import java.time.LocalDateTime;
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

	@JsonProperty("opt")
	private String opt; // �ݨ��ﶵ

	@JsonProperty("answer")
	private String ans; // �@���̿ﶵ

	@JsonProperty("name")
	private String name; // �@���̦W��

	@JsonProperty("phone")
	private String phone; // �@���̹q��

	@JsonProperty("mail")
	private String mail; // �@���̫H�c

	@JsonProperty("age")
	private int age; // �@���̦~��

	@JsonProperty("new_caption")
	private String newCaption; // �s�誺�s�ݨ��W��

	@JsonProperty("new_content")
	private String newContent; // �y�z���e

	@JsonProperty("new_question")
	private String newQuestion; // �s�誺�s�ݨ����D

	@JsonProperty("new_opt")
	private String newOpt; // �s�誺�s�ݨ��ﶵ

	@JsonProperty("new_start_date")
	private Date newStartDate; // �s�誺�s�ݨ��ﶵ
	
	@JsonProperty("new_end_date")
	private Date newEndDate; // �s�誺�s�ݨ��ﶵ
	
	@JsonProperty("finish_time")
	private LocalDateTime finishTime; // �ݨ������ɶ�

	@JsonProperty("page")
	private int page;
	
	@JsonProperty("size")
	private int size;
	
	@JsonProperty("selectedOption")
	private boolean selectedOption; // ��ƿ�
	
	@JsonProperty("required")
	private boolean required;	// ����
	
	public QueryReq() {

	}
	
	public QueryReq(int page, int size) {
		this.page = page;
		this.size = size;
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

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNewCaption() {
		return newCaption;
	}

	public void setNewCaption(String newCaption) {
		this.newCaption = newCaption;
	}

	public String getNewContent() {
		return newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}

	public String getNewQuestion() {
		return newQuestion;
	}

	public void setNewQuestion(String newQuestion) {
		this.newQuestion = newQuestion;
	}

	public String getNewOpt() {
		return newOpt;
	}

	public void setNewOpt(String newOpt) {
		this.newOpt = newOpt;
	}

	public LocalDateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean isSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(boolean selectedOption) {
		this.selectedOption = selectedOption;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Date getNewStartDate() {
		return newStartDate;
	}

	public void setNewStartDate(Date newStartDate) {
		this.newStartDate = newStartDate;
	}

	public Date getNewEndDate() {
		return newEndDate;
	}

	public void setNewEndDate(Date newEndDate) {
		this.newEndDate = newEndDate;
	}

}
