package com.example.Query.service.ifs;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryDeposit;
import com.example.Query.entity.QueryManager;
import com.example.Query.vo.QueryCount;
import com.example.Query.vo.QueryManagerRes;

public interface QueryService {
	
	// 建立新的問卷1
	public QueryManager creatNewCaption(String caption, String content, Date startDate, Date endDate); 
	
	// 設定新建立問卷題目
	public QueryAdd creatQuestion(String caption, String question, String opt, boolean selectedOption, boolean required);

	// 編輯問卷名稱1
	public QueryManager reviseCaption(String caption, String newCaption, String content, String newContent,
			Date startDate, Date newStartDate, Date endDate, Date newEndDate);

	// 編輯問卷問題跟選項1
	public QueryAdd reviseQuestions(String caption, String question, String newQuestion, String opt, String newOpt, boolean selectedOption, boolean required);

	// 刪除問卷(包含作答者資料一併刪除)1
	public QueryManager deleteCaption(String caption);
	
	// 刪除問卷題目
	public QueryAdd deleteQuestion(String caption, String question);
	
	// 找尋問卷(包含作答者資料)
	public QueryManagerRes findByCaption(String caption);
	
	// 顯示所有問卷1
	public List<QueryManager> showAllCaption();
	
	// 建立作答者資料
	public QueryDeposit creatUserInfo(String caption, String question, String ans, String name, String phone, String mail, int age);
	
	// 顯示所有問卷回答者
	public QueryManagerRes showUserInfoByCaption(String caption);
	
	// 分頁查詢(由新到舊排序)
	public List<QueryDeposit> getQueryPage(int page, int size);

	// 已作答問卷選項統計2
	public List<QueryCount> countByAns(String caption);

	// 問卷的模糊搜尋
	public List<QueryManager> findByCaptionContaining(String caption, Date startDate, Date endDate) throws ParseException;

}
