package com.eg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomeRestController {
	@Autowired
	private GreetClient greetClient;
	
	@GetMapping("/welcome")
	public WelcomeResponse getWelcomeMsg() {
		String welcomeMsg="Welcome to Engineers Gesture ..!!";
		//inter-service communication
		String greetMsg = greetClient.invokeGreetApi();
		//External service communication
		RestTemplate rt=new RestTemplate();
		
		String petEndpointUrl="https://fbqm3v39o8.execute-api.ap-south-1.amazonaws.com/dev/pets/1";
		ResponseEntity<Pet> entity = rt.getForEntity(petEndpointUrl, Pet.class);
		Pet petData = entity.getBody();
		WelcomeResponse finalResponse=new WelcomeResponse();
		finalResponse.setGreetMsg(greetMsg);
		finalResponse.setWelcomeMsg(welcomeMsg);
		finalResponse.setPet(petData);
		
		
		return finalResponse;
		
	}
	

}
