package com.example.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Rating;

@Repository
public interface RatingDao extends JpaRepository<Rating, Integer> {
	public Page<Rating> findAll(Pageable pageable);
	public Rating findById(int id);
}
