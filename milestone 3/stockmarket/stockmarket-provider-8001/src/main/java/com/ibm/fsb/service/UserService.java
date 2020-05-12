package com.ibm.fsb.service;

import java.util.List;
import java.util.Optional;

import com.ibm.fsb.entities.User;

public interface UserService {
	
	public User add(User user);

	public Optional<User> get(Long id);
	
	public User loadUserByUsername(String userName);

	public List<User> list();

}
