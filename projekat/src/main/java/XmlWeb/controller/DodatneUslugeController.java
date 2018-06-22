package XmlWeb.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import XmlWeb.config.*;
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

	@AdminWrite
	@RequestMapping(
			value = "/api/dodatneUsluge/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map> deleteUsluga(@PathVariable Long id){
		HashMap<String, String> map = new HashMap();
		try {
			dodatneService.deleteUsluga(id);
		}catch (Exception ex){
			System.out.println("message: " + ex.getMessage());
			if(ex.getMessage().contains("constraint")){
				map.put("text", "Error.This type is in use.");
				return new ResponseEntity<Map>(map, HttpStatus.OK);
			}
		}
		map.put("text", "Success!");
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}

	@AdminWrite
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
