package XmlWeb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.config.PermitAll;
import XmlWeb.dto.SearchDTO;
import XmlWeb.model.Smestaj;
import XmlWeb.service.SmestajService;

@RestController
public class SmestajController {
	
	@Autowired
	private SmestajService smestajService;

	@PermitAll
	@RequestMapping(
			value = "/api/smestaj",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Smestaj>> getAllSmestaj(){
		return new ResponseEntity<Collection<Smestaj>>(smestajService.getAllSmestaj(), HttpStatus.OK);
	}
	@PermitAll
	@RequestMapping(
			value = "/api/smestaj/simplesearch",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Smestaj>> getAllSmestajSimpleS(@RequestBody SearchDTO search){
		return new ResponseEntity<Collection<Smestaj>>(smestajService.getAllSmestajSimple(search), HttpStatus.OK);
	}
	@PermitAll
	@RequestMapping(
			value = "/api/smestaj/advancedsearch",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Smestaj>> getAllSmestajAdvS(@RequestBody SearchDTO search){
		return new ResponseEntity<Collection<Smestaj>>(smestajService.getAllSmestajAdv(search), HttpStatus.OK);
	}
}
