package com.use.ser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.use.ser.entity.User;
import com.use.ser.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user) throws Exception {
		userRepo.findById(user.getId()).orElseThrow(() -> new Exception("Not Found"));
		return userRepo.save(user);
	}

	@Override
	public String deleteUser(int userId) throws Exception {
		userRepo.findById(userId).orElseThrow(() -> new Exception("Not Found"));
		userRepo.deleteById(userId);
		return "Deleted";
	}

	@Override
	public User getUserById(int userId) throws Exception {
		return userRepo.findById(userId).orElseThrow(() -> new Exception("Not Found"));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
}
