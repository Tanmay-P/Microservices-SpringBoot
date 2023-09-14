package com.hot.ser.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.hot.ser.entity.Hotel;
import com.hot.ser.entity.HotelRating;
import com.hot.ser.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@PostMapping("/add")
	public Hotel addHotel(@RequestBody Hotel hotel) {
		return hotelService.addHotel(hotel);
	}
	
	@PutMapping("/update")
	public Object updateHotel(@RequestBody Hotel hotel) {
		try {
			return hotelService.updateHotel(hotel);
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public Object deleteHotel(@PathVariable int id) {
		try {
			hotelService.deleteHotel(id);
			restTemplate.delete("http://rating-service/rating/delete/hotel/"+id);
			return "Deleted";
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/{id}")
	public Object getHotel(@PathVariable int id) {
		try {
			Hotel hotel = hotelService.getHotelById(id);
			
			HotelRating ratings = restTemplate.getForObject("http://rating-service/rating/hotel/"+id, HotelRating.class);

			hotel.setRatings(ratings.getRatings());
			
			return hotel;
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/all")
	public Object getAllHotel() {
	
		List<Hotel> listHotels = hotelService.getAllHotels();
		
		return listHotels.stream().map(e -> {
			
			HotelRating ratings = restTemplate.getForObject("http://rating-service/rating/hotel/"+e.getId(), HotelRating.class);
			e.setRatings(ratings.getRatings());

			return new Hotel(e.getId(), e.getHotelName(), e.getLocation(), e.getDescription(), e.getRatings());
		})
				.collect(Collectors.toList());
	}
	
}
