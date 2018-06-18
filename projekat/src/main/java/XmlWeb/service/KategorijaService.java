package XmlWeb.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XmlWeb.model.Enums.KategorijaSmestaja;
import XmlWeb.repository.KategorijaRepository;

@Service
public class KategorijaService {
	
	@Autowired
	private KategorijaRepository kategorijaRepository;
	
	public Collection<KategorijaSmestaja> getAllKategorija(){
		return kategorijaRepository.findAll();
	}

    public void deleteKategorija(Long id) {
		kategorijaRepository.deleteById(id);
    }
}
