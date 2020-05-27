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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class SectorController {
	
	@Autowired
	private SectorService service;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@RequestMapping(value = "/sector/add", method = RequestMethod.POST)
	public void add(@RequestBody Sector sector) {
		service.add(sector);
	}
	
	@RequestMapping(value = "/sector/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Optional<Sector> get(@PathVariable("id") Long id) {
		Optional<Sector> sector = service.get(id);
		if (!sector.isPresent()) {
			throw new RuntimeException("this ID: " + id + " don't have any data.");
		}
		return sector;
	}
	
	@SuppressWarnings("unused")
	private Sector processHystrix_Get(@PathVariable("id") Long id) {
		return (Sector)new Sector().setName("this ID:" + id + " don't have any data, null --@HystrixCommand").setDbSource("no datasource").setId(id);
	}
	
	@RequestMapping(value = "/sector/list", method = RequestMethod.GET)
	public List<Sector> list() {
		return service.list();
	}
	
	@RequestMapping(value = "/sector/discovery", method = RequestMethod.GET)
	public Applications discovery() {
		return eurekaClient.getApplications();
	}

}
