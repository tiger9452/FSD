package com.ibm.fsb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.fsb.entities.Sector;

@RestController
public class SectorControllerClient {
	
//	private static final String REST_URL_PREFIX = "http://localhost:8001";
	private static final String REST_URL_PREFIX = "http://STOCKMARKET-CLIENT";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/client/sector/add")
	public void add(Sector sector) {
		restTemplate.postForObject(REST_URL_PREFIX + "/sector/add", sector, Boolean.class);
	}
	
	@RequestMapping(value = "/client/sector/get/{id}")
	public Sector get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/sector/get/" + id, Sector.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/client/sector/list")
	public List<Sector> list() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/sector/list/", List.class);
	}
	
	@RequestMapping(value = "/client/sector/discovery")
	public Object discovery() {
		return restTemplate.getForObject(REST_URL_PREFIX + "sector/discovery", Object.class);
	}


}
