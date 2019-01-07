package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Meterial;
import com.example.service.MeterialService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class MeterialController {
	@Autowired
	private MeterialService meterialService;
	private static Logger log = LogManager.getLogger(MeterialController.class.getName());
	
	@RequestMapping(value="/meterial/list", method= RequestMethod.GET)
	public Page<Meterial> getMeterialList(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "15") Integer size,
		@RequestParam(value="storeId") Integer storeId) throws Exception{
		log.info("asdfasfsafsfasdf");
		Pageable pageable = new PageRequest(page, size);
		return meterialService.findMeterialList(pageable, storeId);
	}
	
	@RequestMapping(value="/meterial",method= RequestMethod.POST)
	public Meterial addStore(@RequestBody Meterial meterial, @RequestParam(value="storeId") Integer storeId) {
		return meterialService.addMeterial(meterial, storeId);
	}
	
	@RequestMapping(value="/meterial/{id}",method= RequestMethod.PUT)
	public Meterial modifyMeterial(@RequestBody Meterial meterial, @PathVariable(value = "id") Integer id) {
		return meterialService.ModifyMeterial(meterial, id);
	}
	
	@RequestMapping(value="/meterial",method= RequestMethod.DELETE)
	public void deleteStore(@RequestBody ArrayList<Integer> ids) {
		for(Integer item : ids) {
			meterialService.deleteMeterial(item);
		}
		
	}
}
