package ua.logos.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SigninResponse {
	
	private String token;
	
	private String role;
}
