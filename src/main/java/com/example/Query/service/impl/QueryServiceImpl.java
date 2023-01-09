package com.example.Query.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryDeposit;
import com.example.Query.entity.QueryId;
import com.example.Query.entity.QueryManager;
import com.example.Query.repository.QueryAddDao;
import com.example.Query.repository.QueryDepositDao;
import com.example.Query.repository.QueryManagerDao;
import com.example.Query.service.ifs.QueryService;
import com.example.Query.vo.QueryCount;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired
	private QueryManagerDao queryManagerDao;

	@Autowired
	private QueryAddDao queryAddDao;

	@Autowired
	private QueryDepositDao queryDepositDao;

	// 建立新的問卷
	@Override
	public QueryManager creatNewQuery(String caption, String content, Date startDate, Date endDate) {
		if (queryManagerDao.existsById(caption)) {
			return null;
		}
		QueryManager queryManager = new QueryManager(caption, content, startDate, endDate);
		startDate = new Date();
		return queryManagerDao.save(queryManager);
	}

	// 設定新建立問卷的題目
	@Override
	public QueryAdd creatQuestion(String caption, String question, String opt, boolean selectedOption, boolean required) {
		QueryId queryId = new QueryId(caption, question);

		if (queryManagerDao.findByCaption(caption).isEmpty()) {
			return null;
		}

		if (queryAddDao.existsById(queryId)) {
			return null;
		}

		QueryAdd queryAdd = new QueryAdd(caption, question, opt, selectedOption, required);
		queryAddDao.save(queryAdd);
		return queryAdd;
	}

	// 編輯問卷名稱
	@Override
	public QueryManager reviseCaption(String caption, String newCaption, String content, String newContent,
			String question) {
		Optional<QueryManager> queryManagerOp = queryManagerDao.findById(caption);
		if (!queryManagerOp.isPresent()) {
			return null;
		}
		QueryManager queryManager = queryManagerOp.get();
		queryManagerDao.delete(queryManager);

		if (StringUtils.hasText(newContent)) {
			queryManager.setContent(newContent);
		}

		if (StringUtils.hasText(newCaption)) {
			queryManager.setCaption(newCaption);
			List<QueryAdd> queryAddList = queryAddDao.findByCaption(caption);
			queryAddDao.deleteAll(queryAddList);

			for (QueryAdd queryAdd : queryAddList) {
				queryAdd.setCaption(newCaption);
			}
			queryAddDao.saveAll(queryAddList);
		}
		queryManagerDao.save(queryManager);
		return queryManager;
	}

	// 編輯問卷問題跟選項
	@Override
	public QueryAdd reviseQuestions(String caption, String question, String newQuestion, String opt, String newOpt) {
		QueryId queryId = new QueryId(caption, question);
		Optional<QueryAdd> queryAddOp = queryAddDao.findById(queryId);

		if (!queryAddOp.isPresent()) {
			return null;
		}

		QueryAdd queryAdd = queryAddOp.get();
		queryAddDao.delete(queryAdd);

		if (StringUtils.hasText(newQuestion)) {
			queryAdd.setQuestion(newQuestion);
		}

		if (StringUtils.hasText(newOpt)) {
			queryAdd.setOpt(newOpt);
		}
		return queryAddDao.save(queryAdd);
	}

	// 刪除問卷(包含作答者資料一併刪除)
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
			List<QueryDeposit> queryDepositList = queryDepositDao.findByCaption(caption);
			queryAddDao.deleteAll(queryAddList);
			queryDepositDao.deleteAll(queryDepositList);
		}
		queryManagerDao.delete(queryManager);
		return queryManager;
	}

	@Override
	public List<QueryManager> showAllQuery() {
		return queryManagerDao.findAll();
	}

	// 儲存作答者資料
	@Override
	public QueryDeposit creatUserInfo(String caption, String question, String ans, String name, String phone,
			String mail, int age) {

		if (ans.isEmpty()) {
			return null;
		}

		QueryDeposit queryDeposit = new QueryDeposit(caption, question, ans, name, phone, mail, age);
		queryDeposit.setFinishTime(LocalDateTime.now());

		return queryDepositDao.save(queryDeposit);
	}
	
	@Override
	public List<QueryDeposit> showAllUserInfo() {
		return queryDepositDao.findAll();
	}

	// 分頁查詢(由新到舊排序)
	public List<QueryDeposit> getQueryPage(int page, int size) {
		Page<QueryDeposit> pageResult = queryDepositDao.findAll(PageRequest.of(page, // 查詢的頁數，從0起算
				size, // 查詢的每頁筆數
				Sort.by("finishTime").descending())); // 依CREATE_TIME欄位降冪排序

		List<QueryDeposit> queryDepositList = pageResult.getContent();

		if (queryDepositList.size() < page || size > 10) {
			return null;
		}
		return queryDepositList;
	}

	// 已作答問卷選項統計
	@Override
	public List<QueryCount> countByAns(String question) {
		List<QueryDeposit> queryDepositList = queryDepositDao.findAllByQuestion(question);
		
		Map<String, Integer> count = new HashMap<>();
		
		List<String> questionList = new ArrayList<>();
		
		List<QueryCount> queryCountList = new ArrayList<>();
		
		int totle = 0;
		
		for( QueryDeposit queryDeposit : queryDepositList) {
			questionList.add(queryDeposit.getAns());
		}

		for(String item : questionList) {
			count.put(item, count.getOrDefault(item, 0) +1);
			totle++;
		}
		
		for(Map.Entry<String, Integer> entry : count.entrySet()) {
			
			String answer = entry.getKey();
			int acount = entry.getValue();
			
			int percentage = (int) (acount * 100.0 / totle);
			
			QueryCount queryCount = new QueryCount(answer, totle, acount, percentage);
			queryCountList.add(queryCount);
		}
		
		return queryCountList;
	}

	// 問卷名稱的模糊搜尋
	@Override
	public List<QueryManager> findByCaptionContaining(String caption, Date startDate, Date endDate)
			throws ParseException {

		// 不輸入任何值，全部顯示
		if (!StringUtils.hasText(caption) && startDate == null && endDate == null) {
			return queryManagerDao.findAll();
		}

		// 輸入所有值，全部顯示
		else if (StringUtils.hasText(caption) && startDate != null && endDate != null) {
			return queryManagerDao.findByCaptionContainingAndStartDateBetween(caption, startDate, endDate);
		}

		// 問卷+開始
		else if (StringUtils.hasText(caption) && startDate != null && endDate == null) {
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date end = dateFormat1.parse("2999-12-25");
			return queryManagerDao.findByCaptionContainingAndStartDateBetween(caption, startDate, end);
		}

		// 問卷+結束
		else if (StringUtils.hasText(caption) && startDate == null && endDate != null) {
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date start = dateFormat1.parse("1911-01-01");
			return queryManagerDao.findByCaptionContainingAndStartDateBetween(caption, start, endDate);
		}

		// 開始+結束
		else if (!StringUtils.hasText(caption) && startDate != null && endDate != null) {
			return queryManagerDao.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
		}

		// 結束時間
		else if (!StringUtils.hasText(caption) && startDate == null && endDate != null) {
			return queryManagerDao.findByEndDateLessThanEqual(endDate);
		}

		// 開始時間
		else if (!StringUtils.hasText(caption) && startDate != null && endDate == null) {
			return queryManagerDao.findByStartDateGreaterThanEqual(startDate);
		}

		// 問卷名稱
		else if (StringUtils.hasText(caption) && startDate == null && endDate == null) {
			return queryManagerDao.findByCaptionContaining(caption);
		}

//		return queryManagerDao.findByCaptionContainingAndStartDateBetween(caption, startDate, endDate);
		return null;
	}



}