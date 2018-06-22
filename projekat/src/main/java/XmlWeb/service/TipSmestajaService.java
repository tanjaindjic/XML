package XmlWeb.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XmlWeb.model.Enums.TipSmestaja;
import XmlWeb.repository.TipSmestajaRepository;

@Service
public class TipSmestajaService {
	
	@Autowired
	private TipSmestajaRepository tipRepository;
	
	public Collection<TipSmestaja> getTipSmestajaAll(){
		return tipRepository.findAll();
	}

    public void deleteTip(Long id) {
		tipRepository.deleteById(id);
    }

    public void addService(String tekst) {
		TipSmestaja t = new TipSmestaja();
		t.setTip(tekst);
		tipRepository.save(t);
		System.out.println("id: " + t.getId());
    }
}
