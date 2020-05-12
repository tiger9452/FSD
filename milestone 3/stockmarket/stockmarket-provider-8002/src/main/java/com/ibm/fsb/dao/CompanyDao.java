package com.ibm.fsb.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.fsb.entities.Company;

@Repository
public interface CompanyDao extends CrudRepository<Company, Long> {
	
	List<Company> findByNameLike(String companynName);

}
