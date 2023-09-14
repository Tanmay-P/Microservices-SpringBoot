package com.hot.ser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hot.ser.entity.Hotel;

@Service
public interface HotelService {

	public Hotel addHotel(Hotel hotel);
	
	public Hotel updateHotel(Hotel hotel) throws Exception;
	
	public String deleteHotel(int hotelId) throws Exception;
	
	public Hotel getHotelById(int hotelId) throws Exception;
	
	public List<Hotel> getAllHotels();
}
