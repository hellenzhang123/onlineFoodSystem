package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Rider;

@Repository
public interface RiderDao extends JpaRepository<Rider, Integer> {
	public List<Rider> findAll();
	public List<Rider> findByNameLike(String name);
}
