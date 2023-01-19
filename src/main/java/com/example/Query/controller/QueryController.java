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
import com.example.Query.vo.QueryCount;
import com.example.Query.vo.QueryManagerRes;
import com.example.Query.vo.QueryReq;

@CrossOrigin
@RestController
public class QueryController {

	@Autowired
	private QueryService queryService;

	@PostMapping(value = "/api/creatNewCaption")
	public QueryManagerRes creatNewCaption(@RequestBody QueryReq req) {

		// �P�_�ݨ��D�ػP�y�z�O�_����
		if (!StringUtils.hasText(req.getCaption()) || !StringUtils.hasText(req.getContent())) {
			return new QueryManagerRes(QueryRtnCode.CAPTION_EMPTY.getMessage());
		}

		QueryManager queryManager = queryService.creatNewCaption(req.getCaption(), req.getContent(), req.getStartDate(), req.getEndDate());

		if (queryManager == null) {
			return new QueryManagerRes(QueryRtnCode.EXISTED.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(queryManager, QueryRtnCode.SUCCESSFUL.getMessage());
		return queryManagerRes;
	}

	@PostMapping(value = "/api/creatQuestion")
	public QueryManagerRes creatQuestion(@RequestBody QueryReq req) {
		// �P�_�ݨ��D�جO�_����
		if (!StringUtils.hasText(req.getCaption()) || !StringUtils.hasText(req.getOpt())) {
			return new QueryManagerRes(QueryRtnCode.CAPTION_EMPTY.getMessage());
		}

		QueryAdd queryAdd = queryService.creatQuestion(req.getCaption(), req.getQuestion(), req.getOpt(), req.isSelectedOption(), req.isRequired());

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
				req.getNewContent(), req.getStartDate(), req.getNewStartDate(), req.getEndDate(), req.getNewEndDate());
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
				req.getOpt(), req.getNewOpt(),  req.isSelectedOption(), req.isRequired());
		if (queryAdd == null) {
			return new QueryManagerRes(QueryRtnCode.DELETE_NOT_EXIST.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(queryAdd, QueryRtnCode.SUCCESSFUL.getMessage());
		return queryManagerRes;
	}

	@PostMapping(value = "/api/deleteCaption")
	public QueryManagerRes deleteCaption(@RequestBody QueryReq req) {
		if (!StringUtils.hasText(req.getCaption())) {
			return new QueryManagerRes(QueryRtnCode.DELETE_CAPTION_EMPTY.getMessage());
		}

		QueryManager queryManager = queryService.deleteCaption(req.getCaption());

		if (queryManager == null) {
			return new QueryManagerRes(QueryRtnCode.DELETE_NOT_EXIST.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(QueryRtnCode.DELETE_SUCCESSFUL.getMessage());

		return queryManagerRes;
	}
	
	@PostMapping(value = "/api/deleteQuestion")
	public QueryManagerRes deleteQuestion(@RequestBody QueryReq req) {
		QueryAdd queryAdd = queryService.deleteQuestion(req.getCaption(), req.getQuestion());
		
		QueryManagerRes queryManagerRes = new QueryManagerRes(queryAdd,QueryRtnCode.DELETE_SUCCESSFUL.getMessage());
		
		return queryManagerRes;
	}
	
	
	@PostMapping(value = "/api/findByCaption")
	public QueryManagerRes findByCaption(@RequestBody QueryReq req) {
		return queryService.findByCaption(req.getCaption());
	}
	
	@PostMapping(value = "/api/showAllCaption")
	public QueryManagerRes showAllCaption(){
		List<QueryManager> queryManagerList = queryService.showAllCaption();

		QueryManagerRes queryManagerRes = new QueryManagerRes();
		queryManagerRes.setMessage(QueryRtnCode.SUCCESSFUL.getMessage());
		queryManagerRes.setQueryManagerList(queryManagerList);
		return queryManagerRes;
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
	
	@PostMapping(value = "/api/showUserInfoByCaption")
	public QueryManagerRes showUserInfoByCaption(@RequestBody QueryReq req){
		QueryManagerRes queryManagerRes = queryService.showUserInfoByCaption(req.getCaption());
		queryManagerRes.setMessage(QueryRtnCode.SUCCESSFUL.getMessage());
		return queryManagerRes;
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
	
	@PostMapping(value = "/api/countByAns")
	public QueryManagerRes countByAns(@RequestBody QueryReq req){
		 List<QueryCount> queryCountList = queryService.countByAns(req.getCaption());
				
		QueryManagerRes queryManagerRes = new QueryManagerRes();
		queryManagerRes.setMessage(QueryRtnCode.SEARCH_SUCCESSFUL.getMessage());
		queryManagerRes.setQueryCountList(queryCountList);
		
		return queryManagerRes;
	}
	

	
	@PostMapping(value = "/api/findByCaptionContaining")
	public QueryManagerRes findByCaptionContaining(@RequestBody QueryReq req) throws ParseException {

		if(req.getStartDate().after(req.getEndDate())) {
			return new QueryManagerRes("�}�l������o�j�󵲧����!");
		}
		
		List<QueryManager> queryManagerList = queryService.findByCaptionContaining(req.getCaption(), req.getStartDate(), req.getEndDate());
		
		if (queryManagerList.isEmpty()) {
			return new QueryManagerRes(QueryRtnCode.SEARCH_NOT_EXIST.getMessage());
		}

		QueryManagerRes queryManagerRes = new QueryManagerRes(QueryRtnCode.SEARCH_SUCCESSFUL.getMessage(), queryManagerList);
		return queryManagerRes;
	}


}