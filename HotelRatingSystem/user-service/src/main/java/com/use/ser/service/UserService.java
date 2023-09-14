package com.use.ser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.use.ser.entity.User;

@Service
public interface UserService {

	public User addUser(User user);
	
	public User updateUser(User user) throws Exception;
	
	public String deleteUser(int userId) throws Exception;
	
	public User getUserById(int userId) throws Exception;
	
	public List<User> getAllUsers();
}
