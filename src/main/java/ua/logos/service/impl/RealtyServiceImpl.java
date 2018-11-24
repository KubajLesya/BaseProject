package ua.logos.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.RealtorsDTO;
import ua.logos.domain.RealtyDTO;
import ua.logos.entity.Realtors;
import ua.logos.entity.Realty;

import ua.logos.repository.RealtyRepository;
import ua.logos.service.RealtyService;
import ua.logos.service.utils.ObjectMapperUtils;

@Service
public class RealtyServiceImpl implements  RealtyService {
	@Autowired
	private RealtyRepository realtyRepository;
	
	@Autowired
	private ObjectMapperUtils modelMapper;


	@Override
	public void saveRealty(RealtyDTO realtyDTO) {
		 Realty realty = modelMapper.map(realtyDTO, Realty.class);
		 realtyRepository.save(realty);
		
	}

	@Override
	public RealtyDTO findById(Long id) {
		Realty realty = realtyRepository.findById(id).get();
		RealtyDTO realtyDTO = modelMapper.map(realty, RealtyDTO.class);
		return realtyDTO;
	}

	@Override
	public List<RealtyDTO> findAllRealty() {
		List<Realty> realty = realtyRepository.findAll();
		List<RealtyDTO> realtyDTOs = modelMapper.mapAll(realty, RealtyDTO.class);
		return realtyDTOs;
	}

	@Override
	public void deleteRealty(Long id) {
		realtyRepository.deleteById(id);
		
	}
	

}
