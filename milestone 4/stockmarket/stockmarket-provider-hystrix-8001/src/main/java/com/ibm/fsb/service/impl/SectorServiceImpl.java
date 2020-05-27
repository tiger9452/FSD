package com.ibm.fsb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fsb.dao.SectorDao;
import com.ibm.fsb.entities.Sector;
import com.ibm.fsb.service.SectorService;

@Service
public class SectorServiceImpl implements SectorService {
	
	@Autowired
	private SectorDao dao;

	@Override
	public Sector add(Sector sector) {
		return dao.save(sector);
	}

	@Override
	public Optional<Sector> get(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Sector> list() {
		return dao.findAll();
	}

}
