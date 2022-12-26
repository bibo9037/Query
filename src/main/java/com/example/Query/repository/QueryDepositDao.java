package com.example.Query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Query.entity.QueryDeposit;

public interface QueryDepositDao extends JpaRepository<QueryDeposit, String>{

}
