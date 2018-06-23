package xml.agent.controller;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.agent.dto.SmestajDTO;
import xml.agent.model.Slika;
import xml.agent.model.Smestaj;
import xml.agent.services.KorisnikService;
import xml.agent.services.SlikaService;
import xml.agent.services.SmestajService;
import XmlWeb.dto.SearchDTO;

@RestController
public class SmestajController {

	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private SmestajService smestajService;
	
	@Autowired
	private SlikaService slikaService;
	
	@RequestMapping(
			value = "/api/smestaj",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Smestaj> dodajSmestaj(@RequestBody SmestajDTO sdto){
		Smestaj smestaj = new Smestaj();
		smestaj.generateFromDTO(sdto);
		smestaj.setVlasnik(korisnikService.findByUsername(sdto.getUsername()));
		for(String s : sdto.getSlike()){
			Slika s0 = slikaService.addSlika(new Slika(s));
			smestaj.getSlike().add(s0);
		}
		return new ResponseEntity<Smestaj>(smestajService.dodajSmestaj(smestaj), HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/smestaj/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Smestaj> getSmestajByID(@PathVariable Long id){
		return new ResponseEntity<Smestaj>(smestajService.getSmestajByID(id), HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/smestaj",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Smestaj>> getAllSmestaj(){
		return new ResponseEntity<Collection<Smestaj>>(smestajService.getAllSmestaj(), HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/smestaj/simplesearch",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Smestaj>> getAllSmestajSimpleS(@RequestBody SearchDTO search){
		return new ResponseEntity<Collection<Smestaj>>(smestajService.getAllSmestajSimple(search), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/smestaj/advancedsearch",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Smestaj>> getAllSmestajAdvS(@RequestBody SearchDTO search){
		return new ResponseEntity<Collection<Smestaj>>(smestajService.getAllSmestajAdv(search), HttpStatus.OK);
	}
}
