package com.spring.Controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Entity.User;
import com.spring.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
			userService.saveUser(user);
			return user;
	}

	@GetMapping("getAllUsers")
	public List<User> getAll() {
		return userService.getAllUsers();
	}

	@GetMapping("getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable ObjectId id) {
		Optional<User> user = Optional.ofNullable(userService.getUserById(id));
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("updateUser")
	public ResponseEntity<User> updateJournal(@RequestBody User updatedUser) {
		
		 User oldUser = userService.findByUserName(updatedUser.getUserName()); 
		  if(oldUser != null) {
			  oldUser.setPassword(updatedUser.getPassword()!=null && !updatedUser.getPassword().equals("") ? updatedUser.getPassword() : oldUser.getPassword());
			  userService.saveUser(oldUser);
			return new ResponseEntity<User>(oldUser, HttpStatus.OK);
		  } 
		  else {
			  return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		  }
	}

	@DeleteMapping("deleteUser/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable ObjectId id) {
		userService.deleteUser(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

//	@DeleteMapping("deleteAllUsers")
//	public ResponseEntity<User> deleteAllUsers() {
//		userService.deleteAllUsers();
//		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//	}
}
