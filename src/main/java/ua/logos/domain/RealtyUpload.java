package ua.logos.domain;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.logos.entity.NumberOfRooms;
import ua.logos.entity.State;
import ua.logos.entity.Type;
@Data
@NoArgsConstructor
public class RealtyUpload {
	private Type type;
    private NumberOfRooms numberOfRooms; 
    private State state;
	private String description;
    private BigDecimal price;
	private MultipartFile file;
}
