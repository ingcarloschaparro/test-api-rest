package cachaparro.test.rest.api;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
		return this.greeting(name, false);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
			value = "/{name}/{delay}")
	public ResponseEntity<GreetingResponseDTO> greeting(
			@PathVariable(name = "name") String name,
			@PathVariable(name = "delay") boolean delay
			){
		
		GreetingResponseDTO dto = new GreetingResponseDTO();
		dto.setGreeting("Hello " + name);
		dto.setGreetingDate(LocalDateTime.now());
		dto.setGreetinType(1L);
		dto.setDelay(this.delay(delay));
		
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
		dto.setGreetingDate(LocalDateTime.now());
		dto.setGreetinType(2L);
		dto.setDelay(this.delay(request.isDelay()));
		
		return new ResponseEntity<GreetingResponseDTO>(dto, HttpStatus.OK);
	}
	
	private int delay(boolean delay) {
		if(delay) {
			try {
				int delaySec = new Random(System.currentTimeMillis()).nextInt(20);
				TimeUnit.SECONDS.sleep(delaySec);
				return delaySec;
			} catch (InterruptedException ie) {
			    ie.printStackTrace();
			}
		}
		
		return 0;
	}
}
