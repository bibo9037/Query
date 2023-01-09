package com.example.Query.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "query_add")
@IdClass(QueryId.class)
public class QueryAdd {

	@Id
	@Column(name = "caption")
	private String caption; // 問卷名稱

	@Id
	@Column(name = "question")
	private String question; // 問卷題目

	@Column(name = "opt")
	private String opt; // 問卷選項

	@Column(name = "start_date")
	private Date startDate; // 開始時間

	@Column(name = "end_date")
	private Date endDate; // 結束時間

	public QueryAdd() {

	}
	
	public QueryAdd(String caption, String question, String opt) {
		this.caption = caption;
		this.question = question;
		this.opt = opt;
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

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
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

}
