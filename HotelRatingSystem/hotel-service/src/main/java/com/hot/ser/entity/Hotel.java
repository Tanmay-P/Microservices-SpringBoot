package com.hot.ser.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String hotelName;
	private String location;
	private String description;
	@Transient
	private List<Rating> ratings;
	
	public Hotel(String hotelName, String location, String description) {
		this.hotelName = hotelName;
		this.location = location;
		this.description = description;
	}
}
