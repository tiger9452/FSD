package com.ibm.fsb.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fsb.entities.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	
	User findByUserName(String userName);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
//	@Transactional(propagation=Propagation.NESTED)
	@Query(value = "update users set onetime_code=UUID(), db_source=database() WHERE id=?", nativeQuery = true)
	public Integer updateUser(Long id);
	
}
