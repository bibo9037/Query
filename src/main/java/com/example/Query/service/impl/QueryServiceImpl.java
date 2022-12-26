package com.example.Query.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryId;
import com.example.Query.entity.QueryManager;
import com.example.Query.repository.QueryAddDao;
import com.example.Query.repository.QueryManagerDao;
import com.example.Query.service.ifs.QueryService;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired
	private QueryManagerDao queryManagerDao;

	@Autowired
	private QueryAddDao queryAddDao;

	// 建立新的問卷
	@Override
	public QueryManager creatNewQuery(String caption, String content) {
		if(queryManagerDao.existsById(caption)) {
			return null;
		}
		QueryManager queryManager = new QueryManager(caption, content);
		return queryManagerDao.save(queryManager);
	}

	// 設定新建立問卷的題目
	@Override
	public QueryAdd setQuery(String caption, String question, String options) {
		QueryId queryId = new QueryId(caption, question);

		if (queryAddDao.existsById(queryId)) {
			return null;
		}

		QueryAdd queryAdd = new QueryAdd(caption, question, options);
		queryAddDao.save(queryAdd);
		Optional<QueryAdd> queryAddList = queryAddDao.findById(queryId);
		queryAddList.get().setCaption(caption);
		queryAddDao.save(queryAddList.get());
		return queryAdd;
	}

	// 已作答問卷排序(新到舊)
	@Override
	public void findByFinishTimeOrderByCaptionDesc(String caption, Date finishTime) {

	}

	// 已作答問卷選項統計
	@Override
	public void optionsCount(String caption, String question, String options) {

	}

	// 刪除問卷
	@Override
	public QueryManager deleteQuery(String caption, String question) {
		Optional<QueryManager> queryManagerOp = queryManagerDao.findById(caption);
		
		if (!queryManagerOp.isPresent()) {
			return null;
		}
		
		QueryManager queryManager = queryManagerOp.get();
		queryManagerDao.delete(queryManager);
		
		if(StringUtils.hasText(caption)) {
			queryManager.setCaption(caption);
			List<QueryAdd> queryAddList = queryAddDao.findByCaption(caption);
			queryAddDao.deleteAll(queryAddList);
		}
		queryManagerDao.delete(queryManager);
		return queryManager;
	}

	// 用名稱搜尋問卷
	@Override
	public List<QueryManager> findQueryByName(String caption) {
		List<QueryManager> queryManagerResList = new ArrayList<>();
		List<QueryManager> queryManagerList = queryManagerDao.findByCaption(caption);

		if (queryManagerList.isEmpty()) {
			return null;
		}

		for (QueryManager queryManager : queryManagerList) {
			queryManagerResList.add(queryManager);
		}

		return null;
	}

}
