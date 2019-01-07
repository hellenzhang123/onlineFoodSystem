package com.example.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Sort;
import com.example.service.SortService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class SortController {
	@Autowired
	private SortService sortService;
	private static Logger log = LogManager.getLogger(SortController.class.getName());
	
	@RequestMapping(value="/sort/list", method= RequestMethod.GET)
	public List<Sort> getSortList() throws Exception{
		log.info("asdfasfsafsfasdf");
		return sortService.findSortList();
	}
	
	@RequestMapping(value="/sort",method= RequestMethod.POST)
	public Sort addStore(@RequestBody Sort sort, @RequestParam(value="storeId", required=false) Integer storeId) {
		
		return sortService.addSort(sort, storeId);
	}
	
	@RequestMapping(value="/sort/{id}",method= RequestMethod.GET)
	public Sort findSort(@PathVariable(value = "id") Integer id) {
		return sortService.findOne(id);
	}
	
	@RequestMapping(value="/sort/{id}",method= RequestMethod.PUT)
	public Sort modifySort(@RequestBody Sort sort, @PathVariable(value = "id") Integer id) {
		return sortService.ModifySort(sort, id);
	}
	
	@RequestMapping(value="/sort/{id}",method= RequestMethod.DELETE)
	public void deleteStore(@PathVariable(value = "id") Integer id) {
		sortService.deleteSort(id);
	}
}
