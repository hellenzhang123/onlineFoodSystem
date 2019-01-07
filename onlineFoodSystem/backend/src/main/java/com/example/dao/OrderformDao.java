package com.example.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Orderform;


@Repository
public interface OrderformDao extends JpaRepository<Orderform, Integer> {
	public List<Orderform> findAll();
	public Orderform findById(int id);
	public Page<Orderform> findByOrderTime(Date orderTime, Pageable pageable);
	public Page<Orderform> findByState(String state, Pageable pageable);
	public Page<Orderform> findAll(Pageable pageable);
	public Page<Orderform> findByOrderTimeAndState(Date orderTime, String state, Pageable pageable);
	public List<Orderform> findByStoreId(Integer storeId);
	//根据时间筛选订单
}
