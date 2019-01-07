package com.example.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Food;

@Repository
public interface FoodDao extends JpaRepository<Food, Integer> {
	public Page<Food> findBySortId(int sortId, Pageable pageable);
	public Page<Food> findAll(Pageable pageable);
	public Page<Food> findByNameLike(String name, Pageable pageable);
	public Page<Food> findBySortIdAndName(Integer sortId, String name, Pageable pageable);
	public Food findById(int id);
}
