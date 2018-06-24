package xml.agent.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.agent.dto.IznajmljivanjeDTO;
import xml.agent.dto.SobaDTO;
import xml.agent.model.Iznajmljivanje;
import xml.agent.model.Smestaj;
import xml.agent.model.Soba;
import xml.agent.repository.IznajmljivanjeRepository;
import xml.agent.repository.SmestajRepository;
import xml.agent.repository.SobaRepository;


@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;
	
	@Autowired
	private IznajmljivanjeRepository iznajmljivanjeRepo;
	
	public Collection<Soba> getAllSoba(){
		return sobaRepository.findAll();
	}

	public Soba dodajSoba(SobaDTO soba) {
		Soba s = new Soba();
		s.setMyDTO(soba);
		Optional<Smestaj> smestaj = smestajRepository.findById(soba.getSmestaj().getId());
		if(smestaj.isPresent()){
			smestaj.get().getSobe().add(s);
			smestajRepository.save(smestaj.get());
		}
		return sobaRepository.save(s);
	}

	public Soba getSoba(Long id) {
		Optional<Soba> s = sobaRepository.findById(id);
		if(s.isPresent())
			return s.get();
		return null;
	}

	public Iznajmljivanje dodajIznajmljivanje(Long id, IznajmljivanjeDTO izn) {		
		Optional<Soba> s = sobaRepository.findById(id);
		Iznajmljivanje i = new Iznajmljivanje();
		if(s.isPresent()){
			Soba ss = s.get();
			if(ss.checkDates(izn)){
				i.setMyDTO(izn);
				i.setSoba(ss);
				ss.getIznajmljivanja().add(i);
				sobaRepository.save(ss);
			}
			else{
				return null;
			}
		}
			
		return iznajmljivanjeRepo.save(i);
	}
}
