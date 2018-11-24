package ua.logos.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.LocationDTO;
import ua.logos.entity.Location;
import ua.logos.repository.LocationRepository;
import ua.logos.service.LocationService;
import ua.logos.service.utils.ObjectMapperUtils;
@Service
public class LocationServiceImpl implements LocationService {
	/*{
		"country": "Ukraine",
		"region": "Lvivska",
		"city": "Lviv",
		"street": "Gorodotska"
	}*/
	@Autowired
	private LocationRepository locationRepository;
	
	//@Autowired
	//private ModelMapper modelMapper;
	@Autowired
	private ObjectMapperUtils modelMapper;
	@Override
	public void saveLocation(LocationDTO locationDTO) {
		Location location = modelMapper.map(locationDTO, Location.class);
		locationRepository.save(location);
	}

	@Override
	public LocationDTO findById(Long id) {
		Location location = locationRepository.findById(id).get();
		LocationDTO locationDTO = modelMapper.map(location, LocationDTO.class);
		return locationDTO;
	}

	@Override
	public List<LocationDTO> findAllLocation() {
		List<Location> location = locationRepository.findAll();
		List<LocationDTO> locationDTOs = modelMapper.mapAll(location, LocationDTO.class);
		return locationDTOs;
	}

	@Override
	public void deleteLocation(Long id) {
		locationRepository.deleteById(id);
		
	}
	
}
