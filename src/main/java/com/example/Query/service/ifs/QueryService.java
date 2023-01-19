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
	
	// �إ߷s���ݨ�1
	public QueryManager creatNewCaption(String caption, String content, Date startDate, Date endDate); 
	
	// �]�w�s�إ߰ݨ��D��
	public QueryAdd creatQuestion(String caption, String question, String opt, boolean selectedOption, boolean required);

	// �s��ݨ��W��1
	public QueryManager reviseCaption(String caption, String newCaption, String content, String newContent,
			Date startDate, Date newStartDate, Date endDate, Date newEndDate);

	// �s��ݨ����D��ﶵ1
	public QueryAdd reviseQuestions(String caption, String question, String newQuestion, String opt, String newOpt, boolean selectedOption, boolean required);

	// �R���ݨ�(�]�t�@���̸�Ƥ@�֧R��)1
	public QueryManager deleteCaption(String caption);
	
	// �R���ݨ��D��
	public QueryAdd deleteQuestion(String caption, String question);
	
	// ��M�ݨ�(�]�t�@���̸��)
	public QueryManagerRes findByCaption(String caption);
	
	// ��ܩҦ��ݨ�1
	public List<QueryManager> showAllCaption();
	
	// �إߧ@���̸��
	public QueryDeposit creatUserInfo(String caption, String question, String ans, String name, String phone, String mail, int age);
	
	// ��ܩҦ��ݨ��^����
	public QueryManagerRes showUserInfoByCaption(String caption);
	
	// �����d��(�ѷs���±Ƨ�)
	public List<QueryDeposit> getQueryPage(int page, int size);

	// �w�@���ݨ��ﶵ�έp2
	public List<QueryCount> countByAns(String caption);

	// �ݨ����ҽk�j�M
	public List<QueryManager> findByCaptionContaining(String caption, Date startDate, Date endDate) throws ParseException;

}
