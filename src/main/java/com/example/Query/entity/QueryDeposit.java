package com.example.Query.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "query_deposit")
public class QueryDeposit {
	
	@Id
	@Column(name = "caption")
	private String caption; // 問卷名稱

	@Column(name = "user_name")
	private String userName;	//填寫者名稱
	
	@Column(name = "finish_time")
	private Date finishTime;	//問卷完成時間

	public QueryDeposit() {
		
	}
	
	public QueryDeposit(String caption, String userName, Date finishTime) {
		this.caption = caption;
		this.userName = userName;
		this.finishTime = finishTime;
	}
	
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
}
