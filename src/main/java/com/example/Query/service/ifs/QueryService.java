package com.example.Query.service.ifs;

import java.util.Date;
import java.util.List;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryManager;

public interface QueryService {
	
	// �إ߷s���ݨ�
	public QueryManager creatNewQuery(String caption, String content); 
	
	// �]�w�s�إ߰ݨ����D��
	public QueryAdd setQuery(String caption, String question, String options);

	// �w�@���ݨ��Ƨ�(�s����)
	public void findByFinishTimeOrderByCaptionDesc(String caption, Date finishTime);
	
	// �w�@���ݨ��ﶵ�έp
	public void optionsCount(String caption, String question, String options);
	
	// �R���ݨ�
	public QueryManager deleteQuery(String caption, String question);
	
	// �ΦW�ٷj�M�ݨ�
	public List<QueryManager> findQueryByName(String caption);
}
