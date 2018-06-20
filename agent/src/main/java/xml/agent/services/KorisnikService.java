package xml.agent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.agent.model.Korisnik;
import xml.agent.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepo;
	
	public Korisnik getKorisnik(String username) {
		return korisnikRepo.findByUsernameIgnoreCase(username);
	}

}
