package ua.logos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ua.logos.domain.RealtyDTO;
import ua.logos.domain.RealtyUpload;
import ua.logos.service.RealtyService;

@RestController
@RequestMapping("realty")
public class RealtyController {
//@GetMapping("/test")
//	public String test() {
//		return "Hello sait";
//	}
	/*{
		"type": "FLAT",
		"numberOfRooms": "ONE",
		"state": "EURO_RENOVATION",
		"description": "central part of the city, heating twofunctional boiler, built-in furniture.",
		"imageUrl": "null",
		"price": 30000.00
	}*/
	@Autowired
	private RealtyService realtyService;
	@PostMapping
	public ResponseEntity<Void> addRealty(@RequestBody RealtyDTO realtyDTO){
	realtyService.saveRealty(realtyDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{realtyId}")
	public ResponseEntity<RealtyDTO> getRealty(@PathVariable("realtyId") Long id) {
		RealtyDTO realtyDTO = realtyService.findById(id);
		return new ResponseEntity<RealtyDTO>(realtyDTO, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<RealtyDTO>> getRealty() {
		List<RealtyDTO> realtyDTOs = realtyService.findAllRealty();
		return new ResponseEntity<List<RealtyDTO>>(realtyDTOs, HttpStatus.OK);
	}
	@PutMapping("/{realtyId}")
	public ResponseEntity<Void> updateRealty(@PathVariable("realtyId") Long id, @RequestBody RealtyDTO realtyDTO) {
		RealtyDTO realty = realtyService.findById(id);
		if(realty != null) {
			realtyDTO.setId(id);
		realtyService.saveRealty(realtyDTO);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
		return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
}
	@DeleteMapping("/{realtyId}")
	public ResponseEntity<Void> deleteRealty(@PathVariable("realtyId") Long id) {
		RealtyDTO realty = realtyService.findById(id);
		if(realty != null) {
			realtyService.deleteRealty(realty.getId());
			return new ResponseEntity<Void> (HttpStatus.OK);
		}
		return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		System.out.println("File: " + file.getOriginalFilename());
		realtyService.saveFile(file);
		
		return new ResponseEntity<String>("File upload success.", HttpStatus.ACCEPTED);
	}
	@PostMapping("/upload/object")
	public ResponseEntity<String> uploadObjectFile(@ModelAttribute RealtyUpload realtyUpload) {
		System.out.println("File: " + realtyUpload.getFile().getOriginalFilename());
		realtyService.saveFile(realtyUpload.getFile());
		
		RealtyDTO realty = new RealtyDTO();
		realty.setType(realtyUpload.getType());
		realty.setNumberOfRooms(realtyUpload.getNumberOfRooms());
		realty.setState(realtyUpload.getState());
		realty.setDescription(realtyUpload.getDescription());
		realty.setPrice(realtyUpload.getPrice());
		realty.setImageUrl(realtyUpload.getFile().getOriginalFilename());
		
		realtyService.saveRealty(realty);
		return new ResponseEntity<String>("File upload success.", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/file")
	public ResponseEntity<String> getFile(@RequestParam("fileName") String name) {
		String fileBase64 = realtyService.getFile(name);
		return new ResponseEntity<String>(fileBase64, HttpStatus.OK);
	}
}
