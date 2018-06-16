package XmlWeb.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XmlWeb.model.Soba;
import XmlWeb.repository.SobaRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;
	
	public Collection<Soba> getAllSoba(){
		return sobaRepository.findAll();
	}
}
