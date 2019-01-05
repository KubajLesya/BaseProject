package ua.logos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ua.logos.domain.PosterDTO;
import ua.logos.domain.filter.SimpleFilter;
import ua.logos.entity.Action;
import ua.logos.entity.Type;


public interface PosterService {

void savePoster(PosterDTO poster);
	
	PosterDTO findById(Long id);
	
	List<PosterDTO> findAllPoster();
	
	void deletePoster(Long id);
	
	List<PosterDTO> findPosterByLocationId(Long id);
	List<PosterDTO> findPosterByRealtorsId(Long id);
	
	List<PosterDTO> findByLocationRegion(String region);
	
	List<PosterDTO> findByRealtorsAgencyName(String agencyName);
	List<PosterDTO> findByRealtyType(Type type);
	List<PosterDTO> findByAction(Action action);
	List<PosterDTO> findAllPostersByPages(Pageable pageable);
	List<PosterDTO> findAllPostersBySpecification(SimpleFilter filter);
	
	List<PosterDTO> findByLocationCityOrRealtorsFullName(String city, String fullName);
	
	void deletePosterById(Long id);
}
