package ua.logos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ua.logos.domain.PosterDTO;

import ua.logos.entity.Poster;
import ua.logos.repository.PosterRepository;
import ua.logos.service.PosterService;
import ua.logos.service.utils.ObjectMapperUtils;
@Service
public class PosterServiceImpl implements PosterService {

	@Autowired
	private  PosterRepository  posterRepository;
	
	@Autowired
	private ObjectMapperUtils modelMapper;

	@Override
	public void savePoster(PosterDTO posterDTO) {
		Poster poster = modelMapper.map(posterDTO, Poster.class);
		posterRepository.save(poster);
		
	}

	@Override
	public PosterDTO findById(Long id) {
		Poster poster = posterRepository.findById(id).get();
	    PosterDTO posterDTO = modelMapper.map(poster,  PosterDTO.class);
		return posterDTO;
		
	}

	@Override
	public List<PosterDTO> findAllPoster() {
		List<Poster>  poster =  posterRepository.findAll();
		List<PosterDTO>  posterDTOs = modelMapper.mapAll( poster, PosterDTO.class);
		return  posterDTOs;
		
	}

	@Override
	public void deletePoster(Long id) {
		posterRepository.deleteById(id);
	}

	@Override
	public List<PosterDTO> findPosterByLocationId(Long id) {
		List<Poster> posters = posterRepository.findByLocationId(id);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}

	@Override
	public List<PosterDTO> findPosterByRealtorsId(Long id) {
		List<Poster> posters = posterRepository.findByRealtorsId(id);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}

	@Override
	public List<PosterDTO> findByRegion(String region) {
		List<Poster> posters = posterRepository.findByRegion(region);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}

	@Override
	public List<PosterDTO> findByAgencyName(String agencyName) {
		List<Poster> posters = posterRepository.findByAgencyName(agencyName);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}

	@Override
	public List<PosterDTO> findByType(String type) {
		List<Poster> posters = posterRepository.findByType(type);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}

	@Override
	public List<PosterDTO> findByAction(String action) {
		List<Poster> posters = posterRepository.findByAction(action);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}
	

	
}
