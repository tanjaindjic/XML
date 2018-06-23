package xml.agent.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.agent.model.Smestaj;
import xml.agent.services.SmestajService;

@RestController
public class AgentController {
	@Autowired
	private SmestajService smestajService;

	@RequestMapping(
			value = "/api/agent/{username}/smestaj",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Smestaj>> getAgentSmestaj(@PathVariable String username){
		return new ResponseEntity<Collection<Smestaj>>(smestajService.getAgentSmestaj(username), HttpStatus.OK);
	}
}
