package ua.logos.service;

import java.util.List;


import ua.logos.domain.PosterDTO;


public interface PosterService {

void savePoster(PosterDTO poster);
	
	PosterDTO findById(Long id);
	
	List<PosterDTO> findAllPoster();
	
	void deletePoster(Long id);
	
	List<PosterDTO> findPosterByLocationId(Long id);
	List<PosterDTO> findPosterByRealtorsId(Long id);
	
	List<PosterDTO> findByRegion(String region);
	
	List<PosterDTO> findByAgencyName(String agencyName);
	List<PosterDTO> findByType(String type);
	List<PosterDTO> findByAction(String action);
}
