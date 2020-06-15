package com.ibm.fsb.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.fsb.dao.UserDao;
import com.ibm.fsb.entities.User;
import com.ibm.fsb.entities.enums.EUserType;
import com.ibm.fsb.model.UserDTO;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}
	
	public User save(UserDTO user) {
		User newUser = new User();
		newUser.setUserName(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmailAddr(user.getEmail());
		newUser.setMobileNumber(user.getMobile());
		newUser.setConfirmed(false);
		newUser.setType(EUserType.investor);
		return userDao.save(newUser);
	}
	
	public Integer update(Long id) {
		return userDao.updateUser(id);
	}
	
	public User load(String username) {
		return userDao.findByUserName(username);
	}
}
