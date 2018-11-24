package ua.logos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RealtorsDTO {
	private Long id;
	private String fullName;
	private String agencyName;
	private Long telephone;

}
