package com.ibm.fsb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fsb.dao.CompanyDao;
import com.ibm.fsb.entities.Company;
import com.ibm.fsb.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao dao;

	@Override
	public Company add(Company company) {
		return dao.save(company);
	}

	@Override
	public Optional<Company> get(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public List<Company> list() {
		return (List<Company>) dao.findAll();
	}
	
	@Override
	public List<Company> loadCompanyByName(String companyName) {
		return dao.findByNameLike(companyName);
	}

}
