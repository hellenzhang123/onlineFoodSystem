package com.example.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Food;
import com.example.service.FoodService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class FoodController {
	@Autowired
	private FoodService foodService;
	private static Logger log = LogManager.getLogger(FoodController.class.getName());
	
	
	@GetMapping(value = "/food/list")
	public Page<Food> getEntryByParams(@RequestParam(value = "sortId", required=false) Integer sortId, 
			@RequestParam(value = "name", required=false) String name, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "15") Integer size) throws Exception {  
	    System.out.println("sortId" + sortId);
	    System.out.println("name" + name);
	    @SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(page, size);
	    Page<Food> pages=foodService.findFoodList(sortId, name, pageable);
	    return pages;
	}

	@RequestMapping(value="/food",method= RequestMethod.POST)
	public Food addFood(@RequestParam(value = "storeId", required=false) Integer storeId, @RequestBody Food food) {
		return foodService.addFood(food, storeId);
	}
	
	@RequestMapping(value="/food/{id}",method= RequestMethod.PUT)
	public Food modifyFood(@RequestBody Food food, @PathVariable(value = "id") Integer id) {
		return foodService.ModifyFood(food, id);
	}
	
	@RequestMapping(value="/food/{id}",method= RequestMethod.GET)
	public Food getOneFood(@PathVariable(value = "id") Integer id) {
		return foodService.findOneFood(id);
	}
	
	@RequestMapping(value="/food/{id}",method= RequestMethod.DELETE)
	public void deleteFood(@PathVariable(value = "id") Integer id) {
		foodService.deleteFood(id);
	}
	@RequestMapping(value="/food",method= RequestMethod.DELETE)
	public void deleteFood(@RequestBody ArrayList<Integer> ids) {
		for(Integer item : ids) {
			foodService.deleteFood(item);
		}
	}
}
