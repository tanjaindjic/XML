package XmlWeb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(
			value = "/api/dodatneUsluge/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUsluga(@PathVariable Long id){
		dodatneService.deleteUsluga(id);
	}

	@RequestMapping(
			value = "/api/dodatneUsluge",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void addKategorija(@RequestBody String tekst){

		if(tekst.charAt(tekst.length() - 1)=='=')
			tekst =tekst.substring(0, tekst.length() - 1);
		dodatneService.addUsluga(tekst);
	}
	
}
