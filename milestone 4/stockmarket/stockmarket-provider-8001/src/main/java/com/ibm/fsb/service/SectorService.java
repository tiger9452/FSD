package com.ibm.fsb.service;

import java.util.List;
import java.util.Optional;

import com.ibm.fsb.entities.Sector;

public interface SectorService {

	public Sector add(Sector sector);

	public Optional<Sector> get(Long id);

	public List<Sector> list();

}
