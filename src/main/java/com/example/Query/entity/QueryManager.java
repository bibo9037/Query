package com.example.Query.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "query_manager")
public class QueryManager {

	@Id
	@Column(name = "caption")
	private String caption; // �ݨ��W��

	@Column(name = "content")
	private String content; // �y�z���e

	@Column(name = "start_date")
	private Date startDate; // �}�l�ɶ�

	@Column(name = "end_date")
	private Date endDate; // �����ɶ�

	@Column(name = "state")
	private boolean state; // ���A

	public QueryManager() {

	}
	
	public QueryManager(String caption) {
		this.caption = caption;
	}

	public QueryManager(String caption, String content) {
		this.caption = caption;
		this.content = content;
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
