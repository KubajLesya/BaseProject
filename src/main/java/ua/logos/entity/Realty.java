package ua.logos.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "realty")
public class Realty extends BaseEntity {
	   // @Column(nullable = false, unique = true)
	   // private String realtyId;
	    @Enumerated(EnumType.STRING)
		private Type type;
	    @Enumerated(EnumType.STRING)
	    private NumberOfRooms numberOfRooms; 
	    @Enumerated(EnumType.STRING)
	    private State state;
		
	    private String description;
	    @Column(nullable = true)
	    private String imageUrl;
	    @Column(name = "price", columnDefinition = "DECIMAL(10, 2)")
	    private BigDecimal price;
	    
}
