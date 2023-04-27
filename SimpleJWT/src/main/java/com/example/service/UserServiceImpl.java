package com.example.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepo userrepo;
	
	public UserServiceImpl(UserRepo userrepo) {
		this.userrepo = userrepo;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userrepo.save(user);
	}

	@Override
	public User getUserByNameAndPassword(String name, String password) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userrepo.findByUserNameAndPassword(name, password);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid id or password");
		}
		return user;
	}

}
