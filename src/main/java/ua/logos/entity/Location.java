package ua.logos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "location")
public class Location extends BaseEntity{
	@Column(name = "country", nullable = false)
	private String country;
	@Column(name = "region", nullable = false)
	private String region;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "street", nullable = false)
	private String street;
}
