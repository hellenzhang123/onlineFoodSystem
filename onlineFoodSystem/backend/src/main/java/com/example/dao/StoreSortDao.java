package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.StoreSort;

@Repository
public interface StoreSortDao extends JpaRepository<StoreSort, Integer> {
	public List<StoreSort> findAll();	
	public StoreSort findById(int id);
}
