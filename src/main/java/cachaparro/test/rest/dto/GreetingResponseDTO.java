package cachaparro.test.rest.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class GreetingResponseDTO {

	private String greeting;
	private LocalDate greetingDate;
	private Long greetinType;
	
}
