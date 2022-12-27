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

	// �إ߷s���ݨ�
	@Override
	public QueryManager creatNewQuery(String caption, String content) {
		if (queryManagerDao.existsById(caption)) {
			return null;
		}
		QueryManager queryManager = new QueryManager(caption, content);
		return queryManagerDao.save(queryManager);
	}

	// �]�w�s�إ߰ݨ����D��
	@Override
	public QueryAdd setQuery(String caption, String question, String options) {
		QueryId queryId = new QueryId(caption, question);

		if (queryManagerDao.findByCaption(caption).isEmpty()) {
			return null;
		}

		if (queryAddDao.existsById(queryId)) {
			return null;
		}

		QueryAdd queryAdd = new QueryAdd(caption, question, options);
		queryAddDao.save(queryAdd);
		return queryAdd;
	}
	
	// �s��ݨ��W��
	@Override
	public QueryManager reviseCaption(String caption, String newCaption, String content, String newContent, String question) {
		Optional<QueryManager> queryManagerOp = queryManagerDao.findById(caption);
		if(!queryManagerOp.isPresent()) {
			return null;
		}
		QueryManager queryManager = queryManagerOp.get();
		queryManagerDao.delete(queryManager);
		
		if(StringUtils.hasText(newContent)) {
			queryManager.setContent(newContent);
		}
		
		if(StringUtils.hasText(newCaption)) {
			queryManager.setCaption(newCaption);
			List<QueryAdd> queryAddList = queryAddDao.findByCaption(caption);
			queryAddDao.deleteAll(queryAddList);
			
			for(QueryAdd queryAdd : queryAddList) {
				queryAdd.setCaption(newCaption);
			}
			queryAddDao.saveAll(queryAddList);
		}
		queryManagerDao.save(queryManager);
		return queryManager;
	}

	// �s��ݨ����D��ﶵ
	@Override
	public QueryAdd reviseQuestions(String caption, String question, String newQuestion, String options, String newOptions) {
		QueryId queryId = new QueryId(caption, question);
		Optional<QueryAdd> queryAddOp = queryAddDao.findById(queryId);
		
		if(!queryAddOp.isPresent()) {
			return null;
		}
		
		QueryAdd queryAdd = queryAddOp.get();
		queryAddDao.delete(queryAdd);
		
		if(StringUtils.hasText(newQuestion)) {
			queryAdd.setQuestion(newQuestion);
		}
		
		if(StringUtils.hasText(newOptions)) {
			queryAdd.setOptions(newOptions);
		}
		return queryAddDao.save(queryAdd);
	}

	// �R���ݨ�
	@Override
	public QueryManager deleteQuery(String caption, String question) {
		Optional<QueryManager> queryManagerOp = queryManagerDao.findById(caption);

		if (!queryManagerOp.isPresent()) {
			return null;
		}

		QueryManager queryManager = queryManagerOp.get();
		queryManagerDao.delete(queryManager);

		if (StringUtils.hasText(caption)) {
			queryManager.setCaption(caption);
			List<QueryAdd> queryAddList = queryAddDao.findByCaption(caption);
			queryAddDao.deleteAll(queryAddList);
		}
		queryManagerDao.delete(queryManager);
		return queryManager;
	}
	
	// �w�@���ݨ��Ƨ�(�s����)
	@Override
	public void findByFinishTimeOrderByCaptionDesc(String caption, Date finishTime) {

	}

	// �w�@���ݨ��ﶵ�έp
	@Override
	public void countByOptions(String caption, String question, String options) {
		
	}


	// �ΦW�ٷj�M�ݨ�
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
