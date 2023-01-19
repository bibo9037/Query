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

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
import com.example.Query.vo.QueryManagerRes;

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
	public QueryManager creatNewCaption(String caption, String content, Date startDate, Date endDate) {
		if (queryManagerDao.existsById(caption)) {
			return null;
		}
		QueryManager queryManager = new QueryManager(caption, content, startDate, endDate);
		startDate = new Date();
		return queryManagerDao.save(queryManager);
	}

	// 設定新建立問卷的題目
	@Override
	public QueryAdd creatQuestion(String caption, String question, String opt, boolean selectedOption,
			boolean required) {
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
			Date startDate, Date newStartDate, Date endDate, Date newEndDate) {
		Optional<QueryManager> queryManagerOp = queryManagerDao.findById(caption);
		if (!queryManagerOp.isPresent()) {
			return null;
		}
		QueryManager queryManager = queryManagerOp.get();
		queryManagerDao.delete(queryManager);

		if (StringUtils.hasText(newContent)) {
			queryManager.setContent(newContent);
		}

		if (newStartDate != null) {
			queryManager.setStartDate(newStartDate);
		}

		if (newEndDate != null) {
			queryManager.setEndDate(newEndDate);
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
	public QueryAdd reviseQuestions(String caption, String question, String newQuestion, String opt, String newOpt,
			boolean selectedOption, boolean required) {
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
		queryAdd.setRequired(required);
		queryAdd.setSelectedOption(selectedOption);
		return queryAddDao.save(queryAdd);
	}

	// 刪除問卷(包含作答者資料一併刪除)
	@Override
	@Transactional
	public QueryManager deleteCaption(String caption) {
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

	// 刪除問卷題目
	@Override
	public QueryAdd deleteQuestion(String caption, String question) {
		QueryId queryId = new QueryId(caption, question);
		queryAddDao.deleteById(queryId);
		return null;
	}

	@Override
	public QueryManagerRes findByCaption(String caption) {
		QueryManager queryManager = queryManagerDao.findById(caption).get();
		List<QueryAdd> queryAddList = queryAddDao.findByCaption(caption);

		if (CollectionUtils.isEmpty(queryAddList)) {
			return null;
		}

		return new QueryManagerRes(queryManager, queryAddList);
	}

	@Override
	public List<QueryManager> showAllCaption() {
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
	public QueryManagerRes showUserInfoByCaption(String caption) {
		QueryManagerRes queryManagerRes = new QueryManagerRes();
		List<QueryAdd> queryAddList = queryAddDao.findByCaption(caption);
		List<QueryDeposit> queryDepositList = queryDepositDao.findByCaption(caption);
		queryManagerRes.setQueryAddList(queryAddList);
		queryManagerRes.setQueryDepositList(queryDepositList);
		return queryManagerRes;
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
	public List<QueryCount> countByAns(String caption) {

		List<QueryDeposit> qDList = queryDepositDao.findByCaption(caption);
		List<QueryCount> queryCountList = new ArrayList<>();

		Map<String, Integer> count = new HashMap<>();

		List<String> answerList = new ArrayList<>();
		List<QueryAdd> queryAddList = queryAddDao.findByCaption(caption);

		// 問卷題目作答分類
		for (QueryDeposit qDeposit : qDList) {
			String qDString = qDeposit.getAns();
			String[] qDStringList = qDString.split(";");
			for (String str : qDStringList) {
				answerList.add(str);
			}
		}

		// 放題目總數的Map 例:(你的性向,8) (aaaaa,7)
		Map<String, Integer> questionMap = new HashMap<>();
		for (QueryAdd queryAdd : queryAddList) {

			queryAdd.getQuestion();
			int x = 0;
			for (String item : answerList) {

				if (queryAdd.getOpt().contains(item)) {
					x++;
					questionMap.put(queryAdd.getQuestion(), x);
				}
			}
		}

		for (String item : answerList) {
			count.put(item, count.getOrDefault(item, 0) + 1);
		}

		// 放題目總數的Map 例:(你的性向,8) (aaaaa,7)
		for (Map.Entry<String, Integer> entry : questionMap.entrySet()) {

			String question = entry.getKey();
			int acount = entry.getValue();

			// 放選項總數的Map 例:(雙性戀,3) (同性戀,1)
			for (Map.Entry<String, Integer> option : count.entrySet()) {
				for (QueryAdd item : queryAddList) {

					if (item.getQuestion().equals(question) && item.getOpt().contains(option.getKey())) {

						double percentage = Math.round((option.getValue() * 1000.0 / acount)) / 10.0;

						QueryCount queryCount = new QueryCount(item.getQuestion(), option.getKey(), acount,
								option.getValue(), percentage);
						queryCountList.add(queryCount);
					}
				}
			}
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
		// 輸入所有值，全部顯示
		return queryManagerDao.findByCaptionContainingAndStartDateBetween(caption, startDate, endDate);
	}
}