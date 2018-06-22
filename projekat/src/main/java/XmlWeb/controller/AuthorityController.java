package XmlWeb.controller;

import java.util.ArrayList;
import java.util.List;

import XmlWeb.config.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.model.security.Authority;
import XmlWeb.repository.AuthorityRepository;

@RestController
public class AuthorityController {

	@Autowired
	private AuthorityRepository authorityRepo;
	@PermitAll
	@RequestMapping(value = "/authority", method = RequestMethod.GET)
	public List<Authority> getAuthorities() {
		ArrayList ret = new ArrayList();
		authorityRepo.findAll().forEach(ret::add);
		return ret;
	}
}
