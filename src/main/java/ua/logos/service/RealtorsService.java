package ua.logos.service;

import java.util.List;

import ua.logos.domain.RealtorsDTO;


public interface RealtorsService {
	
	void saveRealtors(RealtorsDTO realtors);
	
	RealtorsDTO findById(Long id);
	
	List<RealtorsDTO> findAllRealtors();
	
	void deleteRealtors(Long id);
}
