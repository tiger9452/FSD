package com.ibm.fsb.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.fsb.entities.Sector;

import feign.hystrix.FallbackFactory;

@Component
public class SectorClientServiceFallbackFactory implements FallbackFactory<SectorClientService> {
	
	@Override
	public SectorClientService create(Throwable throwable) {
		return new SectorClientService() {
			
			@Override
			public List<Sector> list() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Sector get(Long id) {
				return (Sector)new Sector().setName("this ID:" + id + " don't have any data, client provide degrade info, this moment service is closed.").setDbSource("no datasource").setId(id);
			}
			
			@Override
			public void add(Sector sector) {
				// TODO Auto-generated method stub
			}
		};
		
	}

}
