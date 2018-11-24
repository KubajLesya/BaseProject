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
@Table(name = "realtors")
public class Realtors extends BaseEntity {
	@Column(name = "full_name", length = 100, nullable = false)
	private String fullName;
	@Column(name = "agency_name", length = 100, nullable = true)
	private String agencyName;
	@Column(name = "telephone", unique = true)
	private String telephone;
	

}
