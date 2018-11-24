package ua.logos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.logos.domain.LocationDTO;
import ua.logos.domain.RealtorsDTO;
import ua.logos.entity.Location;

import ua.logos.service.LocationService;


@RestController
@RequestMapping("location")
public class LocationController {
	@Autowired
	private LocationService locationService;
	@PostMapping
	public ResponseEntity<Void> addLocation(@RequestBody LocationDTO locationDTO){
		locationService.saveLocation(locationDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@GetMapping("/{locationId}")
	public ResponseEntity<LocationDTO> getLocation(@PathVariable("locationId") Long id) {
		LocationDTO locationDTO = locationService.findById(id);
		return new ResponseEntity<LocationDTO>(locationDTO, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<LocationDTO>> getLocations() {
		List<LocationDTO> locationDTOs = locationService.findAllLocation();
		return new ResponseEntity<List<LocationDTO>>(locationDTOs, HttpStatus.OK);
	}
	@PutMapping("/{locationId}")
	public ResponseEntity<Void> updateLocation(@PathVariable("locationId") Long id, @RequestBody LocationDTO locationDTO) {
		LocationDTO location = locationService.findById(id);
		if(location != null) {
			locationDTO.setId(id);
			locationService.saveLocation(locationDTO);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
		return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
}
	@DeleteMapping("/{locationId}")
	public ResponseEntity<Void> deleteLocation(@PathVariable("locationId") Long id) {
		LocationDTO location = locationService.findById(id);
		if(location != null) {
			locationService.deleteLocation(location.getId());
			return new ResponseEntity<Void> (HttpStatus.OK);
		}
		return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
	}
	
}
