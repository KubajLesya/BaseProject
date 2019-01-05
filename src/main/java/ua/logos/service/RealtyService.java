package ua.logos.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ua.logos.domain.RealtyDTO;

public interface RealtyService {

void saveRealty(RealtyDTO realty);
	
	RealtyDTO findById(Long id);
	
	List<RealtyDTO> findAllRealty();
	
	void deleteRealty(Long id);
	void saveFile(MultipartFile file);
	String getFile(String fileName);
//	void uploadImage(MultipartFile file, String realtyId);
}

