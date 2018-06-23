package xml.agent.services;



import java.util.Collection;

import org.springframework.stereotype.Service;

import xml.agent.model.Iznajmljivanje;
import xml.agent.repository.IznajmljivanjeRepository;

@Service
public class IznajmljivanjeService {
	
	private IznajmljivanjeRepository iznRepository;
	
	public Collection<Iznajmljivanje> getIznajmljivanje(){
		return iznRepository.findAll();
	}

}
