package XmlWeb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.config.UserRead;
import XmlWeb.model.Iznajmljivanje;
import XmlWeb.service.IznajmljivanjeService;

@RestController
public class IznajmljivanjeController {
	
	@Autowired
	private IznajmljivanjeService iznService;
	@UserRead //nisam sigurna da li samo on
	@RequestMapping(
			value = "/api/iznajmljivanje",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Iznajmljivanje>> getAllIznajmljivanje(){
		return new ResponseEntity<Collection<Iznajmljivanje>>(iznService.getIznajmljivanje(), HttpStatus.OK);
	}

}
