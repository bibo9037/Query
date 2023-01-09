package com.example.Query.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Query.entity.QueryManager;

@Repository
public interface QueryManagerDao extends JpaRepository<QueryManager, String> {

	public List<QueryManager> findByCaption(String caption);

	public List<QueryManager> findByCaptionContaining(String caption);

	public List<QueryManager> findByCaptionContainingAndStartDateBetween(String caption, Date startDate, Date endDate);

	public List<QueryManager> findByCaptionContainingAndStartDateGreaterThanEqual(String caption, Date startDate);

	public List<QueryManager> findByCaptionContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String caption, Date startDate, Date endDate);
	
	public List<QueryManager> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date startDate, Date endDate);

	public List<QueryManager> findByEndDateLessThanEqual(Date endDate);

	public List<QueryManager> findByStartDateGreaterThanEqual(Date startDate);
}
//時間組合:
//問卷名稱
//開始時間
//結束時間
//開始+結束
//問卷+開始
//開始+結束
//全部都有
//全部沒有