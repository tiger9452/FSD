package com.ibm.fsb.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.fsb.entities.Sector;

//@FeignClient(value = "STOCKMARKET-CLIENT")
@FeignClient(value = "STOCKMARKET-CLIENT", fallbackFactory = SectorClientServiceFallbackFactory.class)
public interface SectorClientService {

	@RequestMapping(value = "/sector/add", method = RequestMethod.POST)
	public void add(@RequestBody Sector sector);
	
	@RequestMapping(value = "/sector/get/{id}", method = RequestMethod.GET)
	public Sector get(@PathVariable("id") Long id);
	
	@RequestMapping(value = "/sector/list", method = RequestMethod.GET)
	public List<Sector> list();
}
