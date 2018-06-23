package xml.agent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.agent.model.Slika;
import xml.agent.repository.PorukaRepository;
import xml.agent.repository.SlikaRepository;

@Service
public class SlikaService {
	@Autowired
    private SlikaRepository slikaRepo;

	public Slika addSlika(Slika slika) {
		return slikaRepo.save(slika);
	}
	
	
}
