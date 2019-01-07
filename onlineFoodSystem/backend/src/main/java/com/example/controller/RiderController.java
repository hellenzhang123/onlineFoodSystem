package com.example.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.RiderDao;
import com.example.model.Rider;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class RiderController {
	@Autowired
	private RiderDao riderDao;
	private static Logger log = LogManager.getLogger(RiderController.class.getName());
	
	@RequestMapping(value="/rider/list", method= RequestMethod.GET)
	public List<Rider> getRatingList() throws Exception{
		log.info("asdfasfsafsfasdf");
		return riderDao.findAll();
	}
	
	@RequestMapping(value="/rider", method= RequestMethod.GET)
	public List<Rider> getRatingListByName(@RequestParam(value = "name") String name) throws Exception{
		log.info("asdfasfsafsfasdf");
		return riderDao.findByNameLike(name);
	}

}
