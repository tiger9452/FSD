package com.ibm.fsb.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 9198741457788218252L;
	
	private String username;
	private String password;
	private String email;
	private String mobile;
	
}
