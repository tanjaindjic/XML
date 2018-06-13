package XmlWeb.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.dto.RegisterDTO;
import XmlWeb.service.EmailService;
import XmlWeb.service.KorisnikService;
import XmlWeb.service.RegisterService;

@RestController
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(method = RequestMethod.GET, value = "/confirm/{token}")
	public ResponseEntity<HashMap> confirmRegistration(HttpServletResponse response,
			@PathVariable("token") String token) throws IOException {
		return korisnikService.confirmReg(response, token);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public void redirect(HttpServletResponse response) throws IOException {
		registerService.redirect(response);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap> register(HttpServletResponse response, @RequestBody RegisterDTO regDetails)
			throws URISyntaxException, InterruptedException, IOException, NoSuchAlgorithmException, NoSuchProviderException, OperatorCreationException {
		return korisnikService.registerKorisnik(response, regDetails);
	}
}
