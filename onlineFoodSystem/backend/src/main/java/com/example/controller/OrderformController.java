package com.example.controller;

import java.util.Date;
import java.util.List;

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
import com.example.model.Orderform;
import com.example.service.OrderformService;
import com.example.service.StoreService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderformController {
	@Autowired
	private OrderformService orderformService;
//	@Autowired
//	private StoreService storeService;
	private static Logger log = LogManager.getLogger(OrderformController.class.getName());
	
	@GetMapping(value = "/orderform/list")
	public Page<Orderform> getEntryByParams(@RequestParam(value = "state", required=false) String state, 
			@RequestParam(value = "orderTime", required=false) Date orderTime, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "15") Integer size) throws Exception {  
	    System.out.println("sortId" + state);
	    System.out.println("name" + orderTime);
		Sort sort = new Sort(Sort.Direction.DESC, "id");
	    @SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(page, size, sort);
	    Page<Orderform> pages=orderformService.findOrderformList(state, orderTime, pageable);
	    return pages;
	}
	
	@GetMapping(value = "/orderform/store")
	public List<Orderform> getOrderformByStore(@RequestParam(value = "storeId", required=true) Integer storeId) throws Exception {  
	    return orderformService.findOrderformByStore(storeId);
	}
	
	@RequestMapping(value="/orderform",method= RequestMethod.POST)
	public Orderform addOrderform(@RequestBody Orderform orderform, @RequestParam(value = "storeId", required=true) Integer storeId) {
		
		return orderformService.addOrderform(orderform, storeId);
	}
	
	@RequestMapping(value="/orderform/{id}",method= RequestMethod.PUT)
	public Orderform modifyOrderform(@RequestBody Orderform orderform, @PathVariable(value = "id") Integer id) {
		System.out.println(orderform);
		return orderformService.ModifyOrderform(orderform, id);
	}
	
	@RequestMapping(value="/orderform/{id}",method= RequestMethod.GET)
	public Orderform getOneOrderform(@PathVariable(value = "id") Integer id) {
		return orderformService.findOneOrderform(id);
	}
	
	@RequestMapping(value="/orderform/{id}",method= RequestMethod.DELETE)
	public void deleteOrderform(@PathVariable(value = "id") Integer id) {
		orderformService.deleteOrderform(id);
	}
}
