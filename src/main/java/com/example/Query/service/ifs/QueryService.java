package com.example.Query.service.ifs;

import java.util.Date;
import java.util.List;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryManager;

public interface QueryService {
	
	// �إ߷s���ݨ�
	public QueryManager creatNewQuery(String caption, String content); 
	
	// �]�w�s�إ߰ݨ��D��
	public QueryAdd setQuery(String caption, String question, String options);

	// �s��ݨ��W��
	public QueryManager reviseCaption(String caption, String newCaption, String content, String newContent, String question);

	// �R���ݨ�
	public QueryManager deleteQuery(String caption, String question);
	
	// �s��ݨ����D��ﶵ
	public QueryAdd reviseQuestions(String caption, String question, String newQuestion, String options, String newOptions);
	
	// �w�@���ݨ��Ƨ�(�s����)
	public void findByFinishTimeOrderByCaptionDesc(String caption, Date finishTime);
	
	// �w�@���ݨ��ﶵ�έp
	public void countByOptions(String caption, String question, String options);
	
	// �ΦW�ٷj�M�ݨ�
	public List<QueryManager> findQueryByName(String caption);
}
