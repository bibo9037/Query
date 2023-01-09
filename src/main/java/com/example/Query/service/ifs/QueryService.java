package com.example.Query.service.ifs;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryDeposit;
import com.example.Query.entity.QueryManager;

public interface QueryService {
	
	// �إ߷s���ݨ�
	public QueryManager creatNewQuery(String caption, String content, Date startDate, Date endDate); 
	
	// �]�w�s�إ߰ݨ��D��
	public QueryAdd setQuery(String caption, String question, String opt);

	// �s��ݨ��W��
	public QueryManager reviseCaption(String caption, String newCaption, String content, String newContent, String question);

	// �s��ݨ����D��ﶵ
	public QueryAdd reviseQuestions(String caption, String question, String newQuestion, String opt, String newSelect);

	// �R���ݨ�(�]�t�@���̸�Ƥ@�֧R��)
	public QueryManager deleteQuery(String caption, String question);
	
	// ��ܩҦ��ݨ�
	public List<QueryAdd> showAllQuery();
	
	// �إߧ@���̸��
	public QueryDeposit creatUserInfo(String caption, String question, String ans, String name, String phone, String mail, int age);
	
	// ��ܩҦ��ݨ��^����
	public List<QueryDeposit> showAllUserInfo();
	
	// �����d��(�ѷs���±Ƨ�)
	public List<QueryDeposit> getQueryPage(int page, int size);

	// �w�@���ݨ��ﶵ�έp
	public QueryDeposit countByOpt(int id, String caption, String question, String ans);

	// �ݨ����ҽk�j�M
	public List<QueryManager> findByCaptionContaining(String caption, Date startDate, Date endDate) throws ParseException;
}
