package ua.logos.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import ua.logos.domain.PosterDTO;
import ua.logos.domain.filter.SimpleFilter;
import ua.logos.entity.Action;
import ua.logos.entity.Location;
import ua.logos.entity.Poster;
import ua.logos.entity.Realtors;
import ua.logos.entity.Type;
import ua.logos.repository.PosterRepository;
import ua.logos.service.PosterService;
import ua.logos.service.utils.CustomFileUtils;
import ua.logos.service.utils.ObjectMapperUtils;
@Service
public class PosterServiceImpl implements PosterService {

	@Autowired
	private  PosterRepository  posterRepository;
	
	@Autowired
	private ObjectMapperUtils modelMapper;
	
	@Autowired
	private CustomFileUtils fileUtils;

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
		
		for (int i = 0; i < posterDTOs.size(); i++) {
			if(posterDTOs.get(i).getRealty().getImageUrl() != null) {
				PosterDTO posterDto = posterDTOs.get(i);
				String base64File = fileUtils.getFile(posterDto.getRealty().getImageUrl());
				//System.out.println(base64File);
				posterDto.getRealty().setImageUrl(base64File);
				posterDTOs.set(i, posterDto);
			}
		}
		
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
	public List<PosterDTO> findByLocationRegion(String region) {
		List<Poster> posters = posterRepository.findByLocationRegion(region);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}

	@Override
	public List<PosterDTO> findByRealtorsAgencyName(String agencyName) {
		List<Poster> posters = posterRepository.findByRealtorsAgencyName(agencyName);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}

	@Override
	public List<PosterDTO> findByRealtyType(Type type) {
		List<Poster> posters = posterRepository.findByRealtyType(type);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}

	@Override
	public List<PosterDTO> findByAction(Action action) {
		List<Poster> posters = posterRepository.findByAction(action);
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}

	@Override
	public List<PosterDTO> findAllPostersByPages(Pageable pageable) {
		Page<Poster> posterspage = posterRepository.findAll(
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
		List<Poster> posters = posterspage.getContent();
		List<PosterDTO> posterDTOs = modelMapper.mapAll(posters, PosterDTO.class);
		return posterDTOs;
	}
	
	@Override
	public List<PosterDTO> findAllPostersBySpecification(SimpleFilter filter) {
		return modelMapper.mapAll(posterRepository.findAll(getSpecification(filter)), PosterDTO.class);
	}
	private Specification<Poster> getSpecification(SimpleFilter filter) {

		return new Specification<Poster>() {

			
			@Override
			public Predicate toPredicate(Root<Poster> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (filter.getSearch().isEmpty()) {
					return null;
				}
				Expression<Location> searchByCityExp = root.get("location.city");
				Predicate searchByCityPredicate = criteriaBuilder.equal(searchByCityExp, filter.getSearch());
				
//				Expression<String> searchByFullNameExp = root.get("full_name");
//				Predicate searchByFullNamePredicate = criteriaBuilder.like(searchByFullNameExp, "%" + filter.getSearch() + "%");
				 return criteriaBuilder.and(searchByCityPredicate);//or(searchByCityPredicate, searchByFullNamePredicate);
			}
			
		};
		}
		

	
	
	@Override
	public List<PosterDTO> findByLocationCityOrRealtorsFullName(String city, String fullName) {
		return modelMapper.mapAll(posterRepository.findByLocationCityOrRealtorsFullName(city, fullName), PosterDTO.class);
	}

	@Override
	public void deletePosterById(Long id) {
		Poster poster = posterRepository.findById(id).get();//..orElseThrow(
	//			() -> new ResourceNotFoundException("Record with id [" + id + "] not found")
	//			); 
		if (poster != null) {
			posterRepository.deleteById(id);
		}
		
	}

	
}
