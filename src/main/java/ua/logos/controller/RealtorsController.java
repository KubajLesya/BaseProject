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
import ua.logos.domain.RealtyDTO;
import ua.logos.entity.Location;
import ua.logos.entity.Realtors;
import ua.logos.service.RealtorsService;
import ua.logos.service.RealtyService;

@RestController
@RequestMapping("realtors")
public class RealtorsController {
	@Autowired
	private RealtorsService realtorsService;
	@PostMapping
	public ResponseEntity<Void> addRealtors(@RequestBody RealtorsDTO realtorsDTO){
		realtorsService.saveRealtors(realtorsDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{realtorsId}")
	public ResponseEntity<RealtorsDTO> getRealtors(@PathVariable("realtorsId") Long id) {
		RealtorsDTO realtorDTO = realtorsService.findById(id);
		return new ResponseEntity<RealtorsDTO>(realtorDTO, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<RealtorsDTO>> getRealtors() {
		List<RealtorsDTO> realtorDTOs = realtorsService.findAllRealtors();
		return new ResponseEntity<List<RealtorsDTO>>(realtorDTOs, HttpStatus.OK);
	}
	@PutMapping("/{realtorsId}")
	public ResponseEntity<Void> updateRealty(@PathVariable("realtorsId") Long id, @RequestBody RealtorsDTO realtorsDTO) {
		RealtorsDTO realtors = realtorsService.findById(id);
		if(realtors != null) {
			realtorsDTO.setId(id);
		realtorsService.saveRealtors(realtorsDTO);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
		return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
}
	@DeleteMapping("/{realtorsId}")
	public ResponseEntity<Void> deleteRealtors(@PathVariable("realtorsId") Long id) {
		RealtorsDTO realtors = realtorsService.findById(id);
		if(realtors != null) {
			realtorsService.deleteRealtors(realtors.getId());
			return new ResponseEntity<Void> (HttpStatus.OK);
		}
		return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
	}
}
