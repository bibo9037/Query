package com.example.Query.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Query.entity.QueryManager;

@Repository
public interface QueryManagerDao extends JpaRepository<QueryManager, String>{

	public List<QueryManager> findByCaption(String caption);
	
//	public List<QueryManager> findByCaption(List<QueryManagerRes> queryManagerResList );

}
