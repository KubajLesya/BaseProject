package ua.logos.service;

import java.util.List;



import ua.logos.domain.LocationDTO;

import ua.logos.domain.filter.SimpleFilter;



public interface LocationService {
	void saveLocation(LocationDTO location);
	
	LocationDTO findById(Long id);
	
	List<LocationDTO> findAllLocation();
	
	void deleteLocation(Long id);
	
	List<LocationDTO> findAllPostersBySpecification(SimpleFilter filter);
}
