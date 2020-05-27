package com.ibm.fsb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.fsb.entities.Sector;

@Repository
public interface SectorDao extends JpaRepository<Sector, Long> {
	
    @SuppressWarnings("unchecked")
	public Sector save(Sector sector);
	
	public Optional<Sector> findById(Long id);
	
	public List<Sector> findAll();
	
	@Query(value = "SELECT * FROM sectors WHERE name=?", nativeQuery = true)
	public Sector findByName(String name);
	
}
