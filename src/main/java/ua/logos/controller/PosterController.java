package ua.logos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.logos.domain.PosterDTO;
import ua.logos.domain.filter.SimpleFilter;
import ua.logos.entity.Action;
import ua.logos.entity.Type;
import ua.logos.service.PosterService;

@RestController
@RequestMapping("poster")
public class PosterController {
	@Autowired
	private PosterService posterService;

	/*
	 * { "action": "SELLING", "realty": { "id": 1 },
	 * 
	 * "location": { "id": 3 }, "realtors": { "id": 1 } }
	 */
	@PostMapping
	public ResponseEntity<Void> addPoster(@RequestBody PosterDTO posterDTO) {
		posterService.savePoster(posterDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{posterId}")
	public ResponseEntity<PosterDTO> getPoster(@PathVariable("posterId") Long id) {
		PosterDTO posterDTO = posterService.findById(id);
		return new ResponseEntity<PosterDTO>(posterDTO, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<PosterDTO>> getPosters() {
		List<PosterDTO> posterDTOs = posterService.findAllPoster();
		return new ResponseEntity<List<PosterDTO>>(posterDTOs, HttpStatus.OK);
	}

	@PutMapping("/{posterId}")
	public ResponseEntity<Void> updatePoster(@PathVariable("posterId") Long id, @RequestBody PosterDTO posterDTO) {
		PosterDTO poster = posterService.findById(id);
		if (poster != null) {
			posterDTO.setId(id);
			posterService.savePoster(posterDTO);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{posterId}")
	public ResponseEntity<Void> deletePoster(@PathVariable("posterId") Long id) {
		PosterDTO poster = posterService.findById(id);
		if (poster != null) {
			posterService.deletePoster(poster.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	

	@GetMapping("/poster2/location/{locationId}")
	public ResponseEntity<List<PosterDTO>> findPostersByLocationId(@PathVariable("locationId") Long id) {
		List<PosterDTO> posterDTOs = posterService.findPosterByLocationId(id);
		return new ResponseEntity<List<PosterDTO>>(posterDTOs, HttpStatus.OK);
	}

	@GetMapping("/poster3/realtors/{realtorsId}")
	public ResponseEntity<List<PosterDTO>> findPostersByRealtorsId(@PathVariable("realtorsId") Long id) {
		List<PosterDTO> posterDTOs = posterService.findPosterByRealtorsId(id);
		return new ResponseEntity<List<PosterDTO>>(posterDTOs, HttpStatus.OK);
	}

	@GetMapping("/poster1/{action}")
	public ResponseEntity<List<PosterDTO>> findPostersByAction(@PathVariable("action") Action action) {
		List<PosterDTO> posterDTOs = posterService.findByAction(action);
		return new ResponseEntity<List<PosterDTO>>(posterDTOs, HttpStatus.OK);
	}

	@GetMapping("/location/{region}")
	public ResponseEntity<List<PosterDTO>> findByLocationRegion(@PathVariable("locationId") Long id,
			@RequestParam("region") String region) {
		List<PosterDTO> posterDTOs = posterService.findByLocationRegion(region);
		return new ResponseEntity<List<PosterDTO>>(posterDTOs, HttpStatus.OK);
	}

	@GetMapping("/realtors/{agencyName}")
	public ResponseEntity<List<PosterDTO>> findByRealtorsAgencyName(@PathVariable("realtorsId") Long id,
			@RequestParam("agencyName") String agencyName) {
		List<PosterDTO> posterDTOs = posterService.findByRealtorsAgencyName(agencyName);
		return new ResponseEntity<List<PosterDTO>>(posterDTOs, HttpStatus.OK);
	}

	@GetMapping("/realty/{type}")
	public ResponseEntity<List<PosterDTO>> findByRealtyType(@PathVariable("type") Type type) {
		List<PosterDTO> posterDTOs = posterService.findByRealtyType(type);
		return new ResponseEntity<List<PosterDTO>>(posterDTOs, HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<List<PosterDTO>> findPostersByPage(@PageableDefault Pageable pageable) {
		List<PosterDTO> posterDTOs = posterService.findAllPostersByPages(pageable);
		return new ResponseEntity<List<PosterDTO>>(posterDTOs, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<PosterDTO>> findAllPostersBySearch(
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "fullName", required = false) String fullName
			) {
		System.out.println("->>" + city + " " + fullName);
		List<PosterDTO> posterDTOs = posterService.findByLocationCityOrRealtorsFullName(city, fullName);
		return new ResponseEntity<List<PosterDTO>>(posterDTOs, HttpStatus.OK);
	}

}
