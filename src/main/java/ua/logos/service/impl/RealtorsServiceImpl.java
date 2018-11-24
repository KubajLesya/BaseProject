package ua.logos.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.RealtorsDTO;

import ua.logos.entity.Realtors;
import ua.logos.repository.RealtorsRepository;
import ua.logos.service.RealtorsService;
import ua.logos.service.utils.ObjectMapperUtils;

@Service
public class RealtorsServiceImpl implements  RealtorsService{
	/*{
		"fullName": "Ivanov Vasil",
		"agencyName": "Halych Expert",
		"telephone": "+30934884500"
	}*/
	
	@Autowired
	private RealtorsRepository realtorsRepository;
	
	@Autowired
	private ObjectMapperUtils modelMapper;

	@Override
	public void saveRealtors(RealtorsDTO realtorsDTO) {
		 Realtors realtors = modelMapper.map(realtorsDTO, Realtors.class);
		 realtorsRepository.save(realtors);
	}

	@Override
	public RealtorsDTO findById(Long id) {
		Realtors realtors = realtorsRepository.findById(id).get();
		RealtorsDTO realtorsDTO = modelMapper.map(realtors, RealtorsDTO.class);
		return realtorsDTO;
	}

	@Override
	public List<RealtorsDTO> findAllRealtors() {
		List<Realtors> realtors = realtorsRepository.findAll();
		List<RealtorsDTO> realtorsDTOs = modelMapper.mapAll(realtors, RealtorsDTO.class);
		return realtorsDTOs;
	}

	@Override
	public void deleteRealtors(Long id) {
		realtorsRepository.deleteById(id);
		
	}

	

}
