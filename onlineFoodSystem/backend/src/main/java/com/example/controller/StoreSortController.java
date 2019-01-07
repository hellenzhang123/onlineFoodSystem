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
import org.springframework.web.bind.annotation.RestController;

import com.example.model.StoreSort;
import com.example.service.StoreSortService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class StoreSortController {
	@Autowired
	private StoreSortService storeSortService;
	private static Logger log = LogManager.getLogger(StoreSortController.class.getName());
	
	@RequestMapping(value="/storeSort/list", method= RequestMethod.GET)
	public List<StoreSort> getSortList() throws Exception{
		log.info("asdfasfsafsfasdf");
		return storeSortService.findStoreSortList();
	}
	
	@RequestMapping(value="/storeSort",method= RequestMethod.POST)
	public StoreSort addStore(@RequestBody StoreSort storeSort) {
		return storeSortService.addStoreSort(storeSort);
	}
	
	@RequestMapping(value="/storeSort/{id}",method= RequestMethod.PUT)
	public StoreSort modifyStoreSort(@RequestBody StoreSort storeSort, @PathVariable(value = "id") Integer id) {
		return storeSortService.ModifyStoreSort(storeSort, id);
	}
	
	@RequestMapping(value="/storeSort/{id}",method= RequestMethod.DELETE)
	public void deleteStore(@PathVariable(value = "id") Integer id) {
		storeSortService.deleteStoreSort(id);
	}
}
