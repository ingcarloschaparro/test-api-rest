package cachaparro.test.rest.api;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cachaparro.test.rest.dto.GreetingRequestDTO;
import cachaparro.test.rest.dto.GreetingResponseDTO;

@RestController()
@RequestMapping("/api/greeting")
public class GreetingApi {

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
			value = "/{name}")
	public ResponseEntity<GreetingResponseDTO> greeting(
			@PathVariable(name = "name") String name
			){
		GreetingResponseDTO dto = new GreetingResponseDTO();
		dto.setGreeting("Hello " + name);
		dto.setGreetingDate(LocalDate.now());
		dto.setGreetinType(1L);
		
		return new ResponseEntity<GreetingResponseDTO>(dto, HttpStatus.OK);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE},
			value = "/bye")
	public ResponseEntity<GreetingResponseDTO> bye(
			@RequestBody(required = true) GreetingRequestDTO request 
			){
		GreetingResponseDTO dto = new GreetingResponseDTO();
		dto.setGreeting("Bye " + request.getName());
		dto.setGreetingDate(LocalDate.now());
		dto.setGreetinType(2L);
		
		return new ResponseEntity<GreetingResponseDTO>(dto, HttpStatus.OK);
	}
}
