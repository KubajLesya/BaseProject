package ua.logos.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.logos.domain.LocationDTO;
import ua.logos.domain.PosterDTO;
import ua.logos.domain.filter.SimpleFilter;
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
	
	
	
	@Override
	public List<LocationDTO> findAllPostersBySpecification(SimpleFilter filter) {
		
		return modelMapper.mapAll(locationRepository.findAll(getSpecificationForLocation(filter)), LocationDTO.class);
	}

	private Specification<Location> getSpecificationForLocation(SimpleFilter filter) {

		return new Specification<Location>() {

			
			@Override
			public Predicate toPredicate(Root<Location> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (filter.getSearch().isEmpty()) {
					return null;
				}
				Expression<Location> searchByCityExp = root.get("city");
				Predicate searchByCityPredicate = criteriaBuilder.equal(searchByCityExp, filter.getSearch());
				
//				Expression<String> searchByFullNameExp = root.get("full_name");
//				Predicate searchByFullNamePredicate = criteriaBuilder.like(searchByFullNameExp, "%" + filter.getSearch() + "%");
				 return criteriaBuilder.and(searchByCityPredicate);//or(searchByCityPredicate, searchByFullNamePredicate);
			}
			
		};
	
	}
}