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
	private String caption; // �ݨ��W��

	@Id
	@Column(name = "question")
	private String question; // �ݨ��D��

	@Column(name = "options")
	private String options; // �ݨ��ﶵ

	@Column(name = "start_date")
	private Date startDate; // �}�l�ɶ�

	@Column(name = "end_date")
	private Date endDate; // �����ɶ�

	public QueryAdd() {

	}
	
	public QueryAdd(String caption, String question, String options) {
		this.caption = caption;
		this.question = question;
		this.options = options;
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

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
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
