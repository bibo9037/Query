package com.example.Query.service.ifs;

import java.util.Date;
import java.util.List;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryManager;

public interface QueryService {
	
	// 建立新的問卷
	public QueryManager creatNewQuery(String caption, String content); 
	
	// 設定新建立問卷的題目
	public QueryAdd setQuery(String caption, String question, String options);

	// 已作答問卷排序(新到舊)
	public void findByFinishTimeOrderByCaptionDesc(String caption, Date finishTime);
	
	// 已作答問卷選項統計
	public void optionsCount(String caption, String question, String options);
	
	// 刪除問卷
	public QueryManager deleteQuery(String caption, String question);
	
	// 用名稱搜尋問卷
	public List<QueryManager> findQueryByName(String caption);
}
