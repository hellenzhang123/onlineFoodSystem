package com.example.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Meterial;

@Repository
public interface MeterialDao extends JpaRepository<Meterial, Integer> {
	public Page<Meterial> findByStoreId(Pageable pageable, Integer storeId);
	public Meterial findById(int id);
}
