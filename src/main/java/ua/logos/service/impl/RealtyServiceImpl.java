package ua.logos.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import ua.logos.domain.RealtyDTO;


import ua.logos.entity.Realty;

import ua.logos.repository.RealtyRepository;
import ua.logos.service.RealtyService;

import ua.logos.service.utils.CustomFileUtils;
import ua.logos.service.utils.ObjectMapperUtils;

@Service
public class RealtyServiceImpl implements  RealtyService {
	@Autowired
	private RealtyRepository realtyRepository;
	
	@Autowired
	private ObjectMapperUtils modelMapper;
	
	@Autowired
	private CustomFileUtils fileUtils;
	
	
	//@Autowired
	//private CloudinaryService cloudinaryService;
	

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
		
		for (int i = 0; i < realtyDTOs.size(); i++) {
			if(realtyDTOs.get(i).getImageUrl() != null) {
				RealtyDTO realtyDto = realtyDTOs.get(i);
				String base64File = fileUtils.getFile(realtyDto.getImageUrl());
				//System.out.println(base64File);
				realtyDto.setImageUrl(base64File);
				realtyDTOs.set(i, realtyDto);
			}
	}
		
		return realtyDTOs;
	}

	@Override
	public void deleteRealty(Long id) {
		realtyRepository.deleteById(id);
		
	}

	@Override
	public void saveFile(MultipartFile file) {
		try {
			fileUtils.saveUploadedFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getFile(String fileName) {
		return fileUtils.getFile(fileName);
	}

	/*@Override
	public void uploadImage(MultipartFile file, realtyId) {
		String imageUrl = realtyService.uploadImage(file, realtyId);
		Realty realty = realtyRepository.findByRealtyId(realtyId);
		if(realty == null) {
			System.out.println("Not found realty");
		}
		realty.setImageUrl(imageUrl);
		realtyRepository.save(realty);
		
	}*/
	

}
