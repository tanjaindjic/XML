package XmlWeb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import XmlWeb.model.Soba;
import XmlWeb.model.Enums.TipSmestaja;
import XmlWeb.service.TipSmestajaService;

@RestController
public class TipSmestajaController {
	
	@Autowired
	private TipSmestajaService tipService;
	
	@RequestMapping(
			value = "/api/tipService",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<TipSmestaja>> getAllSoba(){
		return new ResponseEntity<Collection<TipSmestaja>>(tipService.getTipSmestajaAll(), HttpStatus.OK);
	}

	@RequestMapping(
			value = "/api/tipService/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteTip(@PathVariable Long id){
		tipService.deleteTip(id);
	}

	@RequestMapping(
			value = "/api/tipService",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void addKategorija(@RequestBody String tekst){
		System.out.println(tekst);
		if(tekst.charAt(tekst.length() - 1)=='=')
			tekst =tekst.substring(0, tekst.length() - 1);
		System.out.println(tekst);
		tipService.addService(tekst);
	}

}
