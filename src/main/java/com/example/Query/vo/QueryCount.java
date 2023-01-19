package com.example.Query.vo;

import java.util.List;

import com.example.Query.entity.QueryAdd;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryCount {
	
	private String question;
	
	private String answer;
	
	private int totle;
	
	private int acount;

	private double percentage;
	
	private List<String> answerList;
	
	private List<String> stringList;
	
	private List<QueryCount> queryCountList;
	
	private List<QueryAdd> queryAddList;
	
	private String caption;
	
	private String ans;

	public QueryCount() {
		
	}

	public QueryCount(String question, String answer, int totle, int acount, double percentage) {
		this.question = question;
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


	public double getPercentage() {
		return percentage;
	}


	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public List<String> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}

	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
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

	public QueryCount(String caption, String question, String ans) {
		this.caption = caption;
		this.question = question;
		this.ans = ans;
	}

	public List<QueryCount> getQueryCountList() {
		return queryCountList;
	}

	public void setQueryCountList(List<QueryCount> queryCountList) {
		this.queryCountList = queryCountList;
	}

	public List<QueryAdd> getQueryAddList() {
		return queryAddList;
	}

	public void setQueryAddList(List<QueryAdd> queryAddList) {
		this.queryAddList = queryAddList;
	}

}
