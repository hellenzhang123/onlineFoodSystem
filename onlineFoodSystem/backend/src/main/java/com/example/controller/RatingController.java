package com.example.controller;

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

import com.example.model.Rating;
import com.example.service.RatingService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class RatingController {
	@Autowired
	private RatingService ratingService;
	private static Logger log = LogManager.getLogger(RatingController.class.getName());
	
	@RequestMapping(value="/rating/list", method= RequestMethod.GET)
	public Page<Rating> getRatingList(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "15") Integer size) throws Exception{
		log.info("asdfasfsafsfasdf");
		Pageable pageable = new PageRequest(page, size);
		return ratingService.findRatingList(pageable);
	}
	
	@RequestMapping(value="/rating",method= RequestMethod.POST)
	public Rating addStore(@RequestBody Rating rating, @RequestParam(value="storeId") Integer storeId) {
		return ratingService.addRating(rating, storeId);
	}
	
	@RequestMapping(value="/rating/{id}",method= RequestMethod.PUT)
	public Rating modifyRating(@RequestBody Rating rating, @PathVariable(value = "id") Integer id) {
		return ratingService.ModifyRating(rating, id);
	}
	
	@RequestMapping(value="/rating/{id}",method= RequestMethod.DELETE)
	public void deleteStore(@PathVariable(value = "id") Integer id) {
		ratingService.deleteRating(id);
	}
}
