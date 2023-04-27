package com.example.service;

import java.util.Map;


import com.example.model.User;


public interface JwtInterface {
	
	Map<String,String> generateToken(User user);

}
