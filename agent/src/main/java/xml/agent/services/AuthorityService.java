package xml.agent.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.agent.model.Korisnik;
import xml.agent.model.security.Authority;
import xml.agent.repository.AuthorityRepository;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;
	
	public void removeUser(Long authId, Long userId) {
		Authority a = authorityRepository.findById(authId).get();
		Korisnik zaBrisanje = null;
		for (Korisnik k : a.getUsers()) {
			if(k.getId()==userId) {
				zaBrisanje = k;
				break;
			}
		}
		if(zaBrisanje!=null)
			a.getUsers().remove(zaBrisanje);
		authorityRepository.save(a);
	}
}
