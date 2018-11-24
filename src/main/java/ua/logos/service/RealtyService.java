package ua.logos.service;

import java.util.List;

import ua.logos.domain.RealtyDTO;
import ua.logos.entity.Realty;

public interface RealtyService {

void saveRealty(RealtyDTO realty);
	
	RealtyDTO findById(Long id);
	
	List<RealtyDTO> findAllRealty();
	
	void deleteRealty(Long id);
}

