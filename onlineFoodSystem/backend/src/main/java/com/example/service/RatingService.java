package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.Rating;

public interface RatingService {
	public Page<Rating> findRatingList(Pageable pageable);
	public Rating addRating(Rating rating, int storeId);
	public Rating ModifyRating(Rating Rating, int id);
	public void deleteRating(int id);
}
