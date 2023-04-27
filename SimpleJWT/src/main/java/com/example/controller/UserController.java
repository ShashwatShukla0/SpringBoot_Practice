package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.JwtInterface;
import com.example.service.UserService;

@RestController
public class UserController {
	
	private UserService userServ;
	private JwtInterface jwt;
		
	@Autowired
	public UserController(UserService userServ, JwtInterface jwt) {
		this.userServ = userServ;
		this.jwt = jwt;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> postUser(@RequestBody User user){
		try {
			userServ.saveUser(user);
			return new ResponseEntity<>(user,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		try {
			if(user.getUsername() == null || user.getPassword() == null) {
				throw new UsernameNotFoundException("Username or password is empty");
			}
			
			User userData = userServ.getUserByNameAndPassword(user.getUsername(), user.getPassword());
			if(userData == null) {
				throw new UsernameNotFoundException("Invalid");
			}
			return new ResponseEntity<>(jwt.generateToken(user),HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT); 
		}
	}
}
