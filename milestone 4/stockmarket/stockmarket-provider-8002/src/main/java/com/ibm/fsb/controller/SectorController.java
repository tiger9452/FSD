package com.ibm.fsb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsb.entities.Sector;
import com.ibm.fsb.service.SectorService;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "Sector management controller")
public class SectorController {
	
	@Autowired
	private SectorService service;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@ApiOperation(value = "adding new sector RESTFUL interface, only support POST method", notes = "test1")
	@RequestMapping(value = "/api/sector/add", method = RequestMethod.POST)
	public Sector add(@ApiParam(value = "name and brief", required = true) @RequestBody Sector sector) {
		return service.add(sector);
	}
	
	@ApiOperation(value = "get sector by identity RESTFUL interface, only support GET method", notes = "test2")
	@RequestMapping(value = "/api/sector/get/{id}", method = RequestMethod.GET)
	public Optional<Sector> get(@ApiParam(value = "sector id", required = true) @PathVariable("id") Long id) {
		return service.get(id);
	}
	
	@ApiOperation(value = "get all sectors RESTFUL interface, only support GET method", notes = "test3")
	@RequestMapping(value = "/api/sector/list", method = RequestMethod.GET)
	public List<Sector> list() {
		return service.list();
	}
	
	@ApiOperation(value = "service discovery RESTFUL interface, only support GET method", notes = "test4")
	@RequestMapping(value = "/api/sector/discovery", method = RequestMethod.GET)
	public Applications discovery() {
		return eurekaClient.getApplications();
	}

}
