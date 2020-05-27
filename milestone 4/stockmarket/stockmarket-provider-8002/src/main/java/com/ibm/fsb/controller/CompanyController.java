package com.ibm.fsb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsb.entities.Company;
import com.ibm.fsb.service.CompanyService;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "company management controller")
public class CompanyController {
	
	@Autowired
	private CompanyService service;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@ApiOperation(value = "adding new Company RESTFUL interface, only support POST method", notes = "test1")
	@RequestMapping(value = "/api/company/add", method = RequestMethod.POST)
	public Company add(@ApiParam(value = "name, name, turnover, ceo, boardDirectors, briefWriteup...", required = true) @RequestBody Company company) {
		return service.add(company);
	}
	
	@ApiOperation(value = "get company by identity RESTFUL interface, only support GET method", notes = "test2")
	@RequestMapping(value = "/api/company/get/{id}", method = RequestMethod.GET)
	public Optional<Company> get(@ApiParam(value = "company id", required = true) @PathVariable("id") Long id) {
		return service.get(id);
	}
	
	@ApiOperation(value = "get all companys RESTFUL interface, only support GET method", notes = "test3")
	@RequestMapping(value = "/api/company/list", method = RequestMethod.GET)
	public List<Company> list() {
		return service.list();
	}
	
	@ApiOperation(value = "get company by name RESTFUL interface, only support GET method", notes = "test4")
	@RequestMapping(value = "/api/company/get/{companyName}", method = RequestMethod.GET)
	public List<Company> loadCompanyByName(@ApiParam(value = "company name", required = true) @PathVariable("companyName") String companyName) {
		return service.loadCompanyByName(companyName);
	}
	
	@ApiOperation(value = "service discovery RESTFUL interface, only support GET method", notes = "test5")
	@RequestMapping(value = "/api/company/discovery", method = RequestMethod.GET)
	public Applications discovery() {
		return eurekaClient.getApplications();
	}

}
