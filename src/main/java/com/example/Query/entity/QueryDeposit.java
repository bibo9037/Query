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
	@Column(name = "id") // �ݨ��s��
	private int id;

	@Column(name = "caption")
	private String caption; // �ݨ��W��

	@Column(name = "question")
	private String question; // �ݨ��D��

	@Column(name = "answer")
	private String ans; // �ݨ��ﶵ

	@Column(name = "name")
	private String name; // �@���̦W��

	@Column(name = "phone")
	private String phone; // �@���̹q��

	@Column(name = "mail")
	private String mail; // �@���̫H�c

	@Column(name = "age")
	private int age; // �@���̦~��

	@Column(name = "finish_time")
	private LocalDateTime finishTime; // �ݨ������ɶ�

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
