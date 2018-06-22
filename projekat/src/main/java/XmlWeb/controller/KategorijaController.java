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

import XmlWeb.model.Enums.DodatneUsluge;
import XmlWeb.model.Enums.KategorijaSmestaja;
import XmlWeb.service.KategorijaService;

import javax.ws.rs.Path;

@RestController
public class KategorijaController {
	
	@Autowired
	private KategorijaService kategorijaService;
	@PermitAll
	@RequestMapping(
			value = "/api/kategorija",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<KategorijaSmestaja>> getAllKategorija(){
		return new ResponseEntity<Collection<KategorijaSmestaja>>(kategorijaService.getAllKategorija(), HttpStatus.OK);
	}


	@AdminWrite
	@RequestMapping(
			value = "/api/kategorija/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map> deleteKategorija(@PathVariable Long id){

		HashMap<String, String> map = new HashMap();
		try {
			kategorijaService.deleteKategorija(id);
		}catch (Exception ex){
			System.out.println("message: " + ex.getMessage());
			if(ex.getMessage().contains("constraint")){
				map.put("text", "Error. This type is in use.");
				return new ResponseEntity<Map>(map, HttpStatus.OK);
			}
		}
		map.put("text", "Success!");
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}

	@AdminWrite
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
