package xml.agent.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.agent.dto.IznajmljivanjeDTO;
import xml.agent.dto.SobaDTO;
import xml.agent.model.Iznajmljivanje;
import xml.agent.model.Soba;
import xml.agent.services.SobaService;


@RestController
public class SobaController {
	
	@Autowired
	private SobaService sobaService;

	@RequestMapping(
			value = "/api/sobe",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Soba>> getAllSoba(){
		return new ResponseEntity<Collection<Soba>>(sobaService.getAllSoba(), HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/sobe/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Soba> getSoba(@PathVariable Long id){
		return new ResponseEntity<Soba>(sobaService.getSoba(id), HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/sobe",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Soba> dodajSoba(@RequestBody SobaDTO soba){
		return new ResponseEntity<Soba>(sobaService.dodajSoba(soba), HttpStatus.OK);
	}
	//iznajmljivanje sobe - dodaj
	@RequestMapping(
			value = "/api/sobe/{id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iznajmljivanje> dodajIznajmljivanje(@PathVariable Long id, @RequestBody IznajmljivanjeDTO izn){
		return new ResponseEntity<Iznajmljivanje>(sobaService.dodajIznajmljivanje(id, izn), HttpStatus.OK);
	}
}
