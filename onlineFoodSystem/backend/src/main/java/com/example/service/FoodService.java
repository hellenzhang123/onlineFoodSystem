package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.Food;

public interface FoodService {
	public Page<Food> findFoodList(Integer sortId, String name, Pageable pageable) throws Exception;
	public Food addFood(Food food, int StoreId);
	public Food ModifyFood(Food food, int id);
	public Food findOneFood(int id);
	public void deleteFood(int id);
}
