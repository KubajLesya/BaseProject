package ua.logos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.logos.entity.Action;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PosterDTO {

	private Long id;
	
	private Action action;
	
	private RealtyDTO realty;
	
	private LocationDTO location;
	
	private RealtorsDTO realtors;
	
}
