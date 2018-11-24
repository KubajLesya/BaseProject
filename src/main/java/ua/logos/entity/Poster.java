package ua.logos.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "poster")
public class Poster extends BaseEntity {
	
	@Enumerated(EnumType.STRING)
	private Action action;
	@ManyToOne
	@JoinColumn(name = "realty_id")
	private Realty realty;
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	@ManyToOne
	@JoinColumn(name = "realtors_id")
	private Realtors realtors;
	
}
