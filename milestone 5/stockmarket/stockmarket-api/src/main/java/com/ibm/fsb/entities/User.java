package com.ibm.fsb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ibm.fsb.entities.enums.EUserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)

@Entity
@Table(name = "users")
@EntityListeners(User.class)
public class User extends AbstractEntityBean {
	
	@Column(length = 60, name = "[name]", nullable = false)
	private String userName;
	
	@Column(length = 60, nullable = false)
	private String password;
	
	@Column(length = 50, name = "email_addr")
	private String emailAddr;
	
	@Column(length = 20, name = "mobile_number")
	private String mobileNumber;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "[type]")
	private EUserType type;
	
	@Column(length = 50, name = "onetime_code")
	private String onetimeCode;
	
	@Column(nullable = false, columnDefinition = "BIT DEFAULT b'0'")
	private Boolean confirmed;
	
	@Column(length = 255, name = "jwt_token")
	private String jwtToken;
	
	@Column(length = 10, name = "db_source")
	private String dbSource;
}
