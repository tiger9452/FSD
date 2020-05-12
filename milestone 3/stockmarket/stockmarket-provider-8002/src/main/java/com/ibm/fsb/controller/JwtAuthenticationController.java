package com.ibm.fsb.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ibm.fsb.entities.User;
import com.ibm.fsb.model.UserDTO;
import com.ibm.fsb.service.JwtUserDetailsService;
import com.ibm.fsb.util.EmailUtil;
import com.ibm.fsb.util.JwtTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin
@Api(value = "JWT Aunthentication controller")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Value("${email.Username}")
	private String fromMail;
	
	@ApiOperation(value = "user login RESTFUL interface, only support POST method", notes = "test1")
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@ApiParam(value = "username and password", required = true) @RequestBody UserDTO userDTO) throws Exception {
		authenticate(userDTO.getUsername(), userDTO.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		response.setHeader("Authorization", "Bearer " + token);
		return ResponseEntity.ok("You have been logged in successfully.");
	}

	@ApiOperation(value = "user register RESTFUL interface, only support POST method", notes = "test2")
	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@ApiParam(value = "username, password, email. mobile", required = true) @RequestBody UserDTO userDTO) throws Exception {
		userDetailsService.update(userDetailsService.save(userDTO).getId());
		User user = userDetailsService.load(userDTO.getUsername());
		// sending email with the temporary pass code to activate the new account
		emailUtil.sendMail(fromMail, userDTO.getEmail(), null, "one time pass code for verify your new registered account",
				"Dear investor, <br/>Here is the pass code, please fill in the code filed for confirm page when you first time login our system: <br/>" 
		  + user.getOnetimeCode(), null);
		return ResponseEntity.ok(user);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}