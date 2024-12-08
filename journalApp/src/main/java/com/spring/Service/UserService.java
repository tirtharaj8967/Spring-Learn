package com.spring.Service;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.Entity.JournalEntry;
import com.spring.Entity.User;
import com.spring.Repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepo;

	// Save
	public void saveUser(User user) {
		userRepo.save(user);
	}

	// Get All
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	// Get By ID
	public User getUserById(ObjectId id) {
		return userRepo.findById(id).orElse(null);
	}

	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

//		Delete by ID
	public boolean deleteUser(ObjectId id) {
		User entry = getUserById(id);
		if (entry != null) {
			userRepo.deleteById(id);
		}
		return true;
	}

//		Delete All
	public void deleteAllUsers() {
		userRepo.deleteAll();

	}
}
