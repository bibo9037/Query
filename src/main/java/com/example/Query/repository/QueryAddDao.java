package com.example.Query.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryId;

@Repository
public interface QueryAddDao extends JpaRepository<QueryAdd, QueryId>{
	
	public List<QueryAdd> findByCaption(String caption);

}
