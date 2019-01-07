package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.Orderform;

public interface OrderformService {
	public Orderform ModifyOrderform(Orderform orderform, int id);
	public Orderform findOneOrderform(int id);
	public void deleteOrderform(int id);
	public Page<Orderform> findOrderformList(String state, Date orderTime, Pageable pageable) throws Exception;
	Orderform addOrderform(Orderform orderform, int storeId);
	public List<Orderform> findOrderformByStore(Integer StoreId);
}
