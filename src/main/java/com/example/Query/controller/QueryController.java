package com.example.Query.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Query.constants.QueryRtnCode;
import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryDeposit;
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

		QueryAdd queryAdd = queryService.setQuery(req.getCaption(), req.getQuestion(), req.getOpt());

		if (queryAdd == null) {
			return new QueryManagerRes(QueryRtnCode.DELETE_NOT_EXIST.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(queryAdd, QueryRtnCode.SUCCESSFUL.getMessage());
		return queryManagerRes;
	}

	@PostMapping(value = "/api/reviseCaption")
	public QueryManagerRes reviseCaption(@RequestBody QueryReq req) {

		if (!StringUtils.hasText(req.getCaption())) {
			return new QueryManagerRes(QueryRtnCode.CAPTION_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getCaption()) && !StringUtils.hasText(req.getContent())) {
			return new QueryManagerRes(QueryRtnCode.CANCEL_REVISE.getMessage());
		}

		QueryManager queryManager = queryService.reviseCaption(req.getCaption(), req.getNewCaption(), req.getContent(),
				req.getNewContent(), req.getQuestion());
		if (queryManager == null) {
			return new QueryManagerRes(QueryRtnCode.DELETE_NOT_EXIST.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(queryManager, QueryRtnCode.SUCCESSFUL.getMessage());
		return queryManagerRes;
	}

	@PostMapping(value = "/api/reviseQuestions")
	public QueryManagerRes reviseQuestions(@RequestBody QueryReq req) {
		if (!StringUtils.hasText(req.getCaption()) || !StringUtils.hasText(req.getQuestion())) {
			return new QueryManagerRes(QueryRtnCode.CAPTION_EMPTY.getMessage());
		}

		QueryAdd queryAdd = queryService.reviseQuestions(req.getCaption(), req.getQuestion(), req.getNewQuestion(),
				req.getOpt(), req.getNewOpt());
		if (queryAdd == null) {
			return new QueryManagerRes(QueryRtnCode.DELETE_NOT_EXIST.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(queryAdd, QueryRtnCode.SUCCESSFUL.getMessage());
		return queryManagerRes;
	}

	@PostMapping(value = "/api/deleteQuery")
	public QueryManagerRes deleteQuery(@RequestBody QueryReq req) {
		if (!StringUtils.hasText(req.getCaption())) {
			return new QueryManagerRes(QueryRtnCode.DELETE_CAPTION_EMPTY.getMessage());
		}

		QueryManager queryManager = queryService.deleteQuery(req.getCaption(), req.getContent());

		if (queryManager == null) {
			return new QueryManagerRes(QueryRtnCode.DELETE_NOT_EXIST.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(QueryRtnCode.DELETE_SUCCESSFUL.getMessage());

		return queryManagerRes;
	}
	
	@PostMapping(value = "/api/showAllQuery")
	public List<QueryAdd> showAllQuery(){
		return queryService.showAllQuery();
	}

	@PostMapping(value = "/api/creatUserInfo")
	public QueryManagerRes creatUserInfo(@RequestBody QueryReq req) {
		if (!StringUtils.hasText(req.getName()) && !StringUtils.hasText(req.getPhone())
				&& !StringUtils.hasText(req.getMail())) {
			return new QueryManagerRes(QueryRtnCode.FAILD.getMessage());
		}

		QueryDeposit queryDeposit = queryService.creatUserInfo(req.getCaption(), req.getQuestion(), req.getAns(),
				req.getName(), req.getPhone(), req.getMail(), req.getAge());
		if (queryDeposit == null) {
			return new QueryManagerRes(QueryRtnCode.ERROR.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(queryDeposit, QueryRtnCode.SAVE_SUCCESSFUL.getMessage());
		return queryManagerRes;
	}
	
	@PostMapping(value = "/api/showAllUserInfo")
	public List<QueryDeposit> showAllUserInfo(){
		return queryService.showAllUserInfo();
	}

	@PostMapping(value = "/api/getQueryPage")
	public QueryManagerRes getQueryPage(@RequestBody QueryReq req) {
		List<QueryDeposit> queryDepositList = queryService.getQueryPage(req.getPage(), req.getSize());

		if (queryDepositList == null) {
			return new QueryManagerRes(QueryRtnCode.SEARCH_FAILD.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(queryDepositList,
				QueryRtnCode.SEARCH_SUCCESSFUL.getMessage());

		return queryManagerRes;
	}

	
	@PostMapping(value = "/api/findByCaptionContaining")
	public QueryManagerRes findByCaptionContaining(@RequestBody QueryReq req) throws ParseException {
		List<QueryManager> queryManagerList = queryService.findByCaptionContaining(req.getCaption(), req.getStartDate(), req.getEndDate());

		if (queryManagerList == null) {
			return new QueryManagerRes(QueryRtnCode.SEARCH_EXIST.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(QueryRtnCode.SEARCH_SUCCESSFUL.getMessage(), queryManagerList);
		return queryManagerRes;
	}


}