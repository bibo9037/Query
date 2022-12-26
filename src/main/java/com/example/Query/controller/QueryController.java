package com.example.Query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Query.constants.QueryRtnCode;
import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryManager;
import com.example.Query.service.ifs.QueryService;
import com.example.Query.vo.QueryManagerRes;
import com.example.Query.vo.QueryReq;

@CrossOrigin
@RestController
public class QueryController {

	@Autowired
	private QueryService queryService;

	@PostMapping(value = "/api/creatNewQuery")
	public QueryManagerRes creatNewQuery(@RequestBody QueryReq req) {

		// 判斷問卷題目與描述是否為空
		if (!StringUtils.hasText(req.getCaption()) || !StringUtils.hasText(req.getContent())) {
			return new QueryManagerRes(QueryRtnCode.CAPTION_EMPTY.getMessage());
		}

		QueryManager queryManager = queryService.creatNewQuery(req.getCaption(), req.getContent());

		if (queryManager == null) {
			return new QueryManagerRes(QueryRtnCode.EXISTED.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(queryManager, QueryRtnCode.SUCCESSFUL.getMessage());
		return queryManagerRes;
	}

	@PostMapping(value = "/api/setQuery")
	public QueryManagerRes setQuery(@RequestBody QueryReq req) {
		// 判斷問卷題目是否為空
		if (!StringUtils.hasText(req.getCaption())) {
			return new QueryManagerRes(QueryRtnCode.CAPTION_EMPTY.getMessage());
		}
		
		QueryAdd queryAdd = queryService.setQuery(req.getCaption(), req.getQuestion(), req.getOptions());

		if (queryAdd == null) {
			return new QueryManagerRes(QueryRtnCode.EXISTED.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(queryAdd, QueryRtnCode.SUCCESSFUL.getMessage());
		return queryManagerRes;
	}
	
	@PostMapping(value = "/api/deleteQuery")
	public QueryManagerRes deleteQuery(@RequestBody QueryReq req) {
		if(!StringUtils.hasText(req.getCaption())) {
			return new QueryManagerRes(QueryRtnCode.DELETE_CAPTION_EMPTY.getMessage());
		}
		
		QueryManager queryManager = queryService.deleteQuery(req.getCaption(), req.getContent());

		if (queryManager == null) {
			return new QueryManagerRes(QueryRtnCode.DELETE_NOT_EXIST.getMessage());
		}
		
		QueryManagerRes queryManagerRes = new QueryManagerRes(QueryRtnCode.DELETE_SUCCESSFUL.getMessage());
		
		return queryManagerRes;
	}

}
