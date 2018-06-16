package XmlWeb.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import XmlWeb.model.Iznajmljivanje;
import XmlWeb.repository.IznajmljivanjeRepository;

@Service
public class IznajmljivanjeService {
	
	private IznajmljivanjeRepository iznRepository;
	
	public Collection<Iznajmljivanje> getIznajmljivanje(){
		return iznRepository.findAll();
	}

}
