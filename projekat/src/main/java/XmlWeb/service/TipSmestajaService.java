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

}
