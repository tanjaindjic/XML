package XmlWeb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.model.Enums.DodatneUsluge;
import XmlWeb.model.Enums.KategorijaSmestaja;
import XmlWeb.service.KategorijaService;

@RestController
public class KategorijaController {
	
	@Autowired
	private KategorijaService kategorijaService;
	
	@RequestMapping(
			value = "/api/kategorija",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<KategorijaSmestaja>> getAllKategorija(){
		return new ResponseEntity<Collection<KategorijaSmestaja>>(kategorijaService.getAllKategorija(), HttpStatus.OK);
	}

}
