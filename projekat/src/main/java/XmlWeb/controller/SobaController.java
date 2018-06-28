package XmlWeb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.config.PermitAll;
import XmlWeb.model.Smestaj;
import XmlWeb.model.Soba;
import XmlWeb.service.SmestajService;
import XmlWeb.service.SobaService;

@RestController
public class SobaController {
	
	@Autowired
	private SobaService sobaService;
	@PermitAll
	@RequestMapping(
			value = "/api/sobe",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Soba>> getAllSoba(){
		return new ResponseEntity<Collection<Soba>>(sobaService.getAllSoba(), HttpStatus.OK);
	}
}
