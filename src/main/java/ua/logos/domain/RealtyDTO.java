package ua.logos.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.logos.entity.NumberOfRooms;
import ua.logos.entity.State;
import ua.logos.entity.Type;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RealtyDTO {
	private Long id;
	private Type type;
    private NumberOfRooms numberOfRooms; 
    private State state;
	private String description;
    private String imageUrl;
    private BigDecimal price;
	
}
