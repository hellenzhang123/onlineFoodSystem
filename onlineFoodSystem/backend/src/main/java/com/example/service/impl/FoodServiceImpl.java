package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dao.FoodDao;
import com.example.dao.SortDao;
import com.example.dao.StoreDao;
import com.example.model.Food;
import com.example.model.Sort;
import com.example.model.Store;
import com.example.service.FoodService;

@Service("foodService")
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodDao foodDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private SortDao sortDao;

	@Override
	public Page<Food> findFoodList(Integer id, String name, Pageable pageable) throws Exception {
		if (id == null && name == null) {
			return foodDao.findAll(pageable);
		} else {
			if (id == null) {
				return foodDao.findByNameLike(name, pageable);
			} else if (name == null) {
				return foodDao.findBySortId(id, pageable);
			} else {
				return foodDao.findBySortIdAndName(id, name, pageable);
			}
		}
		
	}

	@Override
	public Food addFood(Food food, int StoreId) {
		Store store = storeDao.findById(StoreId);
		int sortId = food.getSort().getId();
		Sort sort = sortDao.findById(sortId);
		food.setSort(sort);
		Food newFood = foodDao.save(food);
		List<Food> foods = store.getFood();
		foods.add(newFood);
		store.setFood(foods);
		storeDao.save(store);
		return newFood;
	}

	@Override
	public Food ModifyFood(Food food, int id) {
		Food findFood = foodDao.findById(id);
		foodDao.findById(id);
		if (findFood == null) {
			//抛异常
			return null;
		} else {
			food.setId(id);
			return foodDao.save(food);
		}
	}

	@Override
	public Food findOneFood(int id) {
		if (foodDao.findById(id) == null) {
			//抛异常
		}
		return foodDao.findById(id);
	}

	@Override
	public void deleteFood(int id) {
		foodDao.deleteById(id);
	}

}
