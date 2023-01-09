package com.example.Query.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Query.entity.QueryDeposit;

public interface QueryDepositDao extends JpaRepository<QueryDeposit, Integer>{
	
	public List<QueryDeposit> findByAns(String ans);
	
	public List<QueryDeposit> findByCaption(String caption);
	
	@Query(" select qd from QueryDeposit qd where caption = :caption and question = :question ")
	public List<QueryDeposit> findByCaptionAndQuestion(
			@Param("caption") String caption,
			@Param("question") String question);
	
//	public List<QueryDeposit> findByCaptionOrderByFinishTimeDesc(String caption);

}
