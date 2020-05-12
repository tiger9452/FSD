package com.ibm.fsb.service;

import java.util.List;
import java.util.Optional;

import com.ibm.fsb.entities.Company;

public interface CompanyService {

	public Company add(Company company);

	public Optional<Company> get(Long id);

	public List<Company> list();
	
	public List<Company> loadCompanyByName(String companyName);

}
