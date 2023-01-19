package com.example.Query.vo;

import java.time.LocalDateTime;
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

	@JsonProperty("opt")
	private String opt; // 問卷選項

	@JsonProperty("answer")
	private String ans; // 作答者選項

	@JsonProperty("name")
	private String name; // 作答者名稱

	@JsonProperty("phone")
	private String phone; // 作答者電話

	@JsonProperty("mail")
	private String mail; // 作答者信箱

	@JsonProperty("age")
	private int age; // 作答者年紀

	@JsonProperty("new_caption")
	private String newCaption; // 編輯的新問卷名稱

	@JsonProperty("new_content")
	private String newContent; // 描述內容

	@JsonProperty("new_question")
	private String newQuestion; // 編輯的新問卷問題

	@JsonProperty("new_opt")
	private String newOpt; // 編輯的新問卷選項

	@JsonProperty("new_start_date")
	private Date newStartDate; // 編輯的新問卷選項
	
	@JsonProperty("new_end_date")
	private Date newEndDate; // 編輯的新問卷選項
	
	@JsonProperty("finish_time")
	private LocalDateTime finishTime; // 問卷完成時間

	@JsonProperty("page")
	private int page;
	
	@JsonProperty("size")
	private int size;
	
	@JsonProperty("selectedOption")
	private boolean selectedOption; // 單複選
	
	@JsonProperty("required")
	private boolean required;	// 必填
	
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
