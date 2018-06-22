package xml.agent.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.agent.model.Soba;
import xml.agent.repository.SobaRepository;


@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;
	
	public Collection<Soba> getAllSoba(){
		return sobaRepository.findAll();
	}
}
