package ua.logos.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ua.logos.domain.LocationDTO;



public interface LocationService {
	void saveLocation(LocationDTO location);
	
	LocationDTO findById(Long id);
	
	List<LocationDTO> findAllLocation();
	
	void deleteLocation(Long id);
}
