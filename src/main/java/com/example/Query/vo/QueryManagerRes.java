package com.example.Query.vo;

import java.util.List;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryDeposit;
import com.example.Query.entity.QueryManager;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryManagerRes {

//	private List<QueryManager> queryManagerList;

	private String message;

	private QueryManager queryManager;
	
	private QueryAdd queryAdd;
	
	private QueryDeposit queryDeposit;

	private List<QueryManagerRes> queryManagerResList;
	
	public QueryManagerRes() {

	}

	public QueryManagerRes(String message) {
		this.message = message;
	}

	public QueryManagerRes(QueryManager queryManager, String message) {
		this.queryManager = queryManager;
		this.message = message;
	}
	
	public QueryManagerRes(QueryAdd queryAdd, String message) {
		this.queryAdd = queryAdd;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public QueryManager getQueryManager() {
		return queryManager;
	}

	public void setQueryManager(QueryManager queryManager) {
		this.queryManager = queryManager;
	}

	public QueryAdd getQueryAdd() {
		return queryAdd;
	}

	public void setQueryAdd(QueryAdd queryAdd) {
		this.queryAdd = queryAdd;
	}

	public QueryDeposit getQueryDeposit() {
		return queryDeposit;
	}

	public void setQueryDeposit(QueryDeposit queryDeposit) {
		this.queryDeposit = queryDeposit;
	}

	public List<QueryManagerRes> getQueryManagerResList() {
		return queryManagerResList;
	}

	public void setQueryManagerResList(List<QueryManagerRes> queryManagerResList) {
		this.queryManagerResList = queryManagerResList;
	}

}