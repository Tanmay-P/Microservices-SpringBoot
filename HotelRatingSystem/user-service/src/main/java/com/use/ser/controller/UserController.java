package com.use.ser.controller;

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

import com.use.ser.entity.User;
import com.use.ser.entity.UserRating;
import com.use.ser.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping("/update")
	public Object updateUser(@RequestBody User user) {
		try {
			return userService.updateUser(user);
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public Object deleteUser(@PathVariable int id) {
		try {
			userService.deleteUser(id);
			restTemplate.delete("http://rating-service/rating/delete/user/"+id);
			return "Deleted";
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/{id}")
	public Object getUser(@PathVariable int id) {
		try {
			User user = userService.getUserById(id);
			
			UserRating ratings = restTemplate.getForObject("http://rating-service/rating/user/"+id, UserRating.class);
			
			user.setRatings(ratings.getRatings());
			
			return user;
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/all")
	public Object getAllUsers() {
		
		List<User> userList = userService.getAllUsers();
		
		return 	userList.stream().map(e -> {
					UserRating ratings = restTemplate.getForObject("http://rating-service/rating/user/"+e.getId(), UserRating.class);
					e.setRatings(ratings.getRatings());
					return new User(e.getId(), e.getName(), e.getGender(), e.getRatings());
				})
				.collect(Collectors.toList());
	}
}
