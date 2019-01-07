package com.example.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.dao.OrderformDao;
import com.example.dao.StoreDao;
import com.example.model.Orderform;
import com.example.model.Store;
import com.example.model.User;
import com.example.service.OrderformService;
import com.example.service.UserService;

@Service("orderformService")
public class OrderformServiceImpl implements OrderformService {
	@Autowired
	private OrderformDao orderformDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private UserService userService;


	@Override
	public Page<Orderform> findOrderformList(String state, Date orderTime, Pageable pageable) throws Exception {
		if (state == null && orderTime == null) {
			return orderformDao.findAll(pageable);
		} else {
			if (state == null) {
				return orderformDao.findByOrderTime(orderTime, pageable);
			} else if (orderTime == null) {
				return orderformDao.findByState(state, pageable);
			} else {
				return orderformDao.findByOrderTimeAndState(orderTime, state, pageable);
			}
		}
		
	}

	@Override
	public Orderform addOrderform(Orderform orderform, int storeId) {
		Store store = storeDao.findById(storeId);
		System.out.println(); 
//		orderform.setUser(user);
		orderform.setCreatedAt(new Date());
		orderform.setState("未接单");
		orderform.setUser(userService.getCurrentUser());
		orderform.setStore(store);
		return orderformDao.save(orderform);
	}

	@Override
	public Orderform ModifyOrderform(Orderform store, int id) {
		Orderform findOrderform = orderformDao.findById(id);
		orderformDao.findById(id);
		if (findOrderform == null) {
			//抛异常
			return null;
		} else {
			store.setId(id);
			return orderformDao.save(store);
		}
	}

	@Override
	public Orderform findOneOrderform(int id) {
		if (orderformDao.findById(id) == null) {
			//抛异常
		}
		return orderformDao.findById(id);
	}

	@Override
	public void deleteOrderform(int id) {
		orderformDao.deleteById(id);
	}

	@Override
	public List<Orderform> findOrderformByStore(Integer storeId) {
		return orderformDao.findByStoreId(storeId);
	}

}
