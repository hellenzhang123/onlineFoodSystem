package com.example.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dao.RatingDao;
import com.example.dao.StoreDao;
import com.example.model.Rating;
import com.example.model.Store;
import com.example.service.RatingService;
import com.example.service.UserService;


@Service("RatingService")
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingDao ratingDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private UserService userService;

	@Override
	public Rating addRating(Rating rating, int storeId) {
		// TODO Auto-generated method stub
		Store store = storeDao.findById(storeId);
		Rating newrating = ratingDao.save(rating);
		newrating.setUsername(userService.getCurrentUser().getName());
		newrating.setAvatar(userService.getCurrentUser().getAvatar());
		newrating.setRateTime(new Date());
		List<Rating> ratings = new ArrayList<Rating> ();
		ratings.add(newrating);
		store.setRating(ratings);
		storeDao.save(store);
		return newrating;
	}

	@Override
	public Rating ModifyRating(Rating meterial, int id) {
		Rating findRating = ratingDao.findById(id);
		ratingDao.findById(id);
		if (findRating == null) {
			//抛异常
			return null;
		} else {
			meterial.setId(id);
			return ratingDao.save(meterial);
		}
	}

	@Override
	public void deleteRating(int id) {
		ratingDao.deleteById(id);
	}

	@Override
	public Page<Rating> findRatingList(Pageable pageable) {
		return ratingDao.findAll(pageable);
	}

}
