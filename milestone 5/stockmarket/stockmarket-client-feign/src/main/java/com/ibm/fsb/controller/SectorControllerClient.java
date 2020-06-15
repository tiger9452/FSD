package com.ibm.fsb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsb.entities.Sector;
import com.ibm.fsb.service.SectorClientService;

@RestController
public class SectorControllerClient {
	
	@Autowired
	private SectorClientService service;
	
	@RequestMapping(value = "/client/sector/add", method = RequestMethod.POST)
	public void add(@RequestBody Sector sector) {
		service.add(sector);
	}
	
	@RequestMapping(value = "/client/sector/get/{id}", method = RequestMethod.GET)
	public Sector get(@PathVariable("id") Long id) {
		return service.get(id);
	}
	
	@RequestMapping(value = "/client/sector/list", method = RequestMethod.GET)
	public List<Sector> list() {
		return service.list();
	}


}
