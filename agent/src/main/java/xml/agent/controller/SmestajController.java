package xml.agent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.agent.model.Korisnik;
import xml.agent.security.AuthenticationException;
import xml.agent.security.JwtAuthenticationRequest;
import xml.agent.security.JwtAuthenticationResponse;

@RestController
public class SmestajController {
	
}
