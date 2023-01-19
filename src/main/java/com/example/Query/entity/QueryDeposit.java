package com.example.Query.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "query_deposit")
public class QueryDeposit {
	
	@Id
	@Column(name = "id") // 問卷編號
	private int id;

	@Column(name = "caption")
	private String caption; // 問卷名稱

	@Column(name = "question")
	private String question; // 問卷題目

	@Column(name = "answer")
	private String ans; // 問卷選項

	@Column(name = "name")
	private String name; // 作答者名稱

	@Column(name = "phone")
	private String phone; // 作答者電話

	@Column(name = "mail")
	private String mail; // 作答者信箱

	@Column(name = "age")
	private int age; // 作答者年紀

	@Column(name = "finish_time")
	private LocalDateTime finishTime; // 問卷完成時間

	public QueryDeposit() {

	}

	public QueryDeposit(String caption, String name, LocalDateTime finishTime) {
		this.caption = caption;
		this.name = name;
		this.finishTime = finishTime;
	}
	
	public QueryDeposit(String caption, String question, String ans, String name, String phone, String mail, int age) {
		this.caption = caption;
		this.question = question;
		this.ans = ans;
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}

	public QueryDeposit(String caption, String question, String ans) {
		this.caption = caption;
		this.question = question;
		this.ans = ans;
	}

}
