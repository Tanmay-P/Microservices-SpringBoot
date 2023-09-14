package com.rat.ser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rat.ser.entity.Rating;
import com.rat.ser.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/add")
	public Rating addRating(@RequestBody Rating rating) {
		return ratingService.addRating(rating);
	}
	
	@PutMapping("/update")
	public Object updateRating(@RequestBody Rating rating) {
		try {
			return ratingService.updateRating(rating);
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public Object deleteRating(@PathVariable int id) {
		try {
			return ratingService.deleteRating(id);
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@DeleteMapping("/delete/user/{id}")
	public Object deleteRatingByUser(@PathVariable int id) {
		try {
			return ratingService.deleteRatingByUserId(id);
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@DeleteMapping("/delete/hotel/{id}")
	public Object deleteRatingByHotel(@PathVariable int id) {
		try {
			return ratingService.deleteRatingByHotelId(id);
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/{id}")
	public Object getRating(@PathVariable int id) {
		try {
			return ratingService.getRatingById(id);
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/user/{id}")
	public Object getRatingByUser(@PathVariable int id) {
		try {
			return ratingService.getRatingByUserId(id);
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/hotel/{id}")
	public Object getRatingByHotel(@PathVariable int id) {
		try {
			return ratingService.getRatingByHotelId(id);
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/all")
	public List<Rating> getAllRatings() {
		return ratingService.getAllRatings();
	}
}
