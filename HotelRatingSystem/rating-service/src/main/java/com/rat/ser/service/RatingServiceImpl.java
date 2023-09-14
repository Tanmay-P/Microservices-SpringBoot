package com.rat.ser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rat.ser.entity.HotelRating;
import com.rat.ser.entity.Rating;
import com.rat.ser.entity.UserRating;
import com.rat.ser.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepo;
	
	@Override
	public Rating addRating(Rating rating) {
		return ratingRepo.save(rating);
	}

	@Override
	public Rating updateRating(Rating rating) throws Exception {
		ratingRepo.findById(rating.getId()).orElseThrow(() -> new Exception("Not Found"));
		return ratingRepo.save(rating);
	}

	@Override
	public String deleteRating(int ratingId) throws Exception {
		ratingRepo.findById(ratingId).orElseThrow(() -> new Exception("Not Found"));
		return "Deleted";
	}
	
	@Override
	public String deleteRatingByUserId(int userId) throws Exception {
		ratingRepo.deleteAllByUserId(userId).orElseThrow(() -> new Exception("Not Found"));
		return "Deleted";
	}

	@Override
	public String deleteRatingByHotelId(int hotelId) throws Exception {
		ratingRepo.deleteAllByHotelId(hotelId).orElseThrow(() -> new Exception("Not Found"));
		return "Delete";
	}

	@Override
	public Rating getRatingById(int ratingId) throws Exception {
		return ratingRepo.findById(ratingId).orElseThrow(() -> new Exception("Not Found"));
	}

	@Override
	public UserRating getRatingByUserId(int userId) throws Exception {
		List<Rating> list = ratingRepo.findAllByUserId(userId).orElseThrow(() -> new Exception("Not Found"));
		
		UserRating userRating = new UserRating();
		userRating.setRatings(list);
		
		return userRating;
	}

	@Override
	public HotelRating getRatingByHotelId(int hotelId) throws Exception {
		List<Rating> list = ratingRepo.findAllByHotelId(hotelId).orElseThrow(() -> new Exception("Not Found"));

		HotelRating hotelRating = new HotelRating();
		hotelRating.setRatings(list);
		
		return hotelRating;
	}

	@Override
	public List<Rating> getAllRatings() {
		return ratingRepo.findAll();
	}

}
