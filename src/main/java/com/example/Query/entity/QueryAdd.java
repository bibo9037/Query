package com.example.Query.entity;

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
	
	@Column(name = "selected_option")
	private boolean selectedOption; // 單複選
	
	@Column(name = "required")
	private boolean required;	// 必填

	public QueryAdd() {

	}

	public QueryAdd(String caption, String question, String opt, boolean selectedOption, boolean required) {
		this.caption = caption;
		this.question = question;
		this.opt = opt;
		this.selectedOption = selectedOption;
		this.required = required;
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

}
