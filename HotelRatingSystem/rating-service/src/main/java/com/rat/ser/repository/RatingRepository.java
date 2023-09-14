package com.rat.ser.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rat.ser.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	Optional<List<Rating>> findAllByUserId(int userId); 
	
	Optional<List<Rating>> findAllByHotelId(int hotelId); 
	
	@Query(value = "delete from rating where user_id = :userId", nativeQuery = true)
	Optional<String> deleteAllByUserId(int userId);

	@Query(value = "delete from rating where hotel_id = :hotelId", nativeQuery = true)
	Optional<String> deleteAllByHotelId(int hotelId);
}
