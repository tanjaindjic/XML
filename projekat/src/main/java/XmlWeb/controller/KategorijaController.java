package XmlWeb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import XmlWeb.model.Enums.DodatneUsluge;
import XmlWeb.model.Enums.KategorijaSmestaja;
import XmlWeb.service.KategorijaService;

import javax.ws.rs.Path;

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


	@RequestMapping(
			value = "/api/kategorija/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteKategorija(@PathVariable Long id){
		kategorijaService.deleteKategorija(id);
	}

	@RequestMapping(
				value = "/api/kategorija",
				method = RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public void addKategorija(@RequestBody String tekst){

			if(tekst.charAt(tekst.length() - 1)=='=')
				tekst =tekst.substring(0, tekst.length() - 1);
			kategorijaService.addKateg(tekst);
	}
}
