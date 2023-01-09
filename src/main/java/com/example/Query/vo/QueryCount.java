package com.example.Query.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryCount {
	
	private String answer;
	
	private int totle;
	
	private int acount;

	private int percentage;

	public QueryCount() {
		
	}

	public QueryCount(String answer, int totle, int acount, int percentage) {
		this.answer = answer;
		this.totle = totle;
		this.acount = acount;
		this.percentage = percentage;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getTotle() {
		return totle;
	}

	public void setTotle(int totle) {
		this.totle = totle;
	}

	public int getAcount() {
		return acount;
	}

	public void setAcount(int acount) {
		this.acount = acount;
	}


	public int getPercentage() {
		return percentage;
	}


	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

}
