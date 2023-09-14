package com.hot.ser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hot.ser.entity.Hotel;
import com.hot.ser.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepo;

	
	@Override
	public Hotel addHotel(Hotel hotel) {
		return hotelRepo.save(hotel);
	}

	@Override
	public Hotel updateHotel(Hotel hotel) throws Exception {
		hotelRepo.findById(hotel.getId()).orElseThrow(() -> new Exception("Not Found"));
		return hotelRepo.save(hotel);
	}

	@Override
	public String deleteHotel(int hotelId) throws Exception {
		hotelRepo.findById(hotelId).orElseThrow(() -> new Exception("Not Found"));
		hotelRepo.deleteById(hotelId);
		return "Deleted";
	}

	@Override
	public Hotel getHotelById(int hotelId) throws Exception {
		return hotelRepo.findById(hotelId).orElseThrow(() -> new Exception("Not Found"));
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepo.findAll();
	}

}
