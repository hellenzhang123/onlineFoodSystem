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
import com.example.model.Store;
import com.example.service.StoreService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class StoreController {
	@Autowired
	private StoreService storeService;
	private static Logger log = LogManager.getLogger(StoreController.class.getName());
	
	@GetMapping(value = "/store/list")
	public Page<Store> getEntryByParams(@RequestParam(value = "sortId", required=false) Integer sortId, 
			@RequestParam(value = "name", required=false) String name, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "15") Integer size) throws Exception {  
	    @SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(page, size);
	    Page<Store> pages=storeService.findStoreList(sortId, name, pageable);
	    return pages;
	}
	
	@RequestMapping(value="/store",method= RequestMethod.POST)
	public Store addStore(@RequestBody Store store, @RequestParam(value="sortId") Integer sortId) {
		return storeService.addStore(store, sortId);
	}
	
	@RequestMapping(value="/store/{id}",method= RequestMethod.PUT)
	public Store modifyStore(@RequestBody Store store, @PathVariable(value = "id") Integer id) {
		return storeService.ModifyStore(store, id);
	}
	
	@RequestMapping(value="/store/{id}",method= RequestMethod.GET)
	public Store getOneStore(@PathVariable(value = "id") Integer id) {
		return storeService.findOneStore(id);
	}

	@RequestMapping(value="/store",method= RequestMethod.DELETE)
	public void deleteStore(@RequestBody ArrayList<Integer> ids) {
		for(Integer item : ids) {
			storeService.deleteStore(item);
		}
	}
}
