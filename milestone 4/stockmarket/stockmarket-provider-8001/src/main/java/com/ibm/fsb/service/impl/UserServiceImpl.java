package com.ibm.fsb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fsb.dao.UserDao;
import com.ibm.fsb.entities.User;
import com.ibm.fsb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;

	@Override
	public User add(User user) {
		return dao.save(user);
	}

	@Override
	public Optional<User> get(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public User loadUserByUsername(String userName) {
		return dao.findByUserName(userName);
	}

	@Override
	public List<User> list() {
		return (List<User>) dao.findAll();
	}

}
