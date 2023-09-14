package com.hot.ser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

	private int id;
	private int rate;
	private String review;
	private int userId;
	private int hotelId;
}
