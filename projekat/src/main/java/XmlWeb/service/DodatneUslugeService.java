package XmlWeb.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XmlWeb.model.Enums.DodatneUsluge;
import XmlWeb.repository.DodatneUslugeRepository;

@Service
public class DodatneUslugeService {
	
	@Autowired
	private DodatneUslugeRepository dodtaneRepository;
	
	public Collection<DodatneUsluge> getAllDodatne(){
		return dodtaneRepository.findAll();
	}

    public void deleteUsluga(Long id) {
		dodtaneRepository.deleteById(id);
    }

	public void addUsluga(String tekst) {
		DodatneUsluge d= new DodatneUsluge();
		d.setOpcija(tekst);
		dodtaneRepository.save(d);
	}
}
