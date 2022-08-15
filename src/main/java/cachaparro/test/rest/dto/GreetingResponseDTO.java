package cachaparro.test.rest.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GreetingResponseDTO {

	private String greeting;
	private LocalDateTime greetingDate;
	private Long greetinType;
	private Integer delay;
	
}
