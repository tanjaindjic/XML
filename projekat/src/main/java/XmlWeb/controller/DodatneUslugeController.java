package XmlWeb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.model.Soba;
import XmlWeb.model.Enums.DodatneUsluge;
import XmlWeb.service.DodatneUslugeService;

@RestController
public class DodatneUslugeController {
	
	@Autowired
	private DodatneUslugeService dodatneService;

	@RequestMapping(
			value = "/api/dodatneUsluge",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DodatneUsluge>> getAllSoba(){
		return new ResponseEntity<Collection<DodatneUsluge>>(dodatneService.getAllDodatne(), HttpStatus.OK);
	}
	
}
