package XmlWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.dto.KorisnikDTO;
import XmlWeb.model.Korisnik;
import XmlWeb.service.KorisnikService;


@RestController
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public List<Korisnik> getKorisnici(){
		System.out.println("usao po sve korisnike: " + korisnikService.getAllKorisnik().size());
		return korisnikService.getAllKorisnik();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public Korisnik getKorisnikByID(@PathVariable Long id){
		return korisnikService.getKorisnik(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/username/{id}")
	public Korisnik getKorisnikByUsername(@RequestBody String id){
		return korisnikService.getKorisnik(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/user")
	public void updateKorisnik(@RequestBody KorisnikDTO k){ korisnikService.updateKorisnik(k); }

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public void addKorisnik(@RequestBody KorisnikDTO k){
		korisnikService.addKorisnik(k);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	public void deleteKorisnik(@PathVariable Long id){
		 korisnikService.deleteKorisnik(id);
	}

}
