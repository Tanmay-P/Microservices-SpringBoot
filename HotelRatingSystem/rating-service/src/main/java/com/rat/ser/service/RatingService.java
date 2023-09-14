package com.rat.ser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rat.ser.entity.HotelRating;
import com.rat.ser.entity.Rating;
import com.rat.ser.entity.UserRating;

@Service
public interface RatingService {

	public Rating addRating(Rating rating);
	
	public Rating updateRating(Rating rating) throws Exception;
	
	public String deleteRating(int ratingId) throws Exception;

	public String deleteRatingByUserId(int userId) throws Exception;

	public String deleteRatingByHotelId(int hotelId) throws Exception;

	public Rating getRatingById(int ratingId) throws Exception;
	
	public UserRating getRatingByUserId(int userId) throws Exception;
	
	public HotelRating getRatingByHotelId(int hotelId) throws Exception;
	
	public List<Rating> getAllRatings();
}
