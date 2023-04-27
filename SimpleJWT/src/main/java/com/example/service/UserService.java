package com.example.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.User;

@Service
public interface UserService {
	public void saveUser(User user);
	public User getUserByNameAndPassword(String name,String password) throws UsernameNotFoundException;
}
