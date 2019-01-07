package com.example.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Store;

@Repository
public interface StoreDao extends JpaRepository<Store, Integer> {
	public Page<Store> findBySortId(int sortId, Pageable pageable);
	public Page<Store> findAll(Pageable pageable);
	public Page<Store> findByNameLike(String name, Pageable pageable);
	public Store findById(int id);
	public Page<Store> findBySortIdAndName(Integer id, String name, Pageable pageable);
}
