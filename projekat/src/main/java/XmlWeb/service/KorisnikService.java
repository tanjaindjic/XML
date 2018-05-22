package XmlWeb.service;

import java.util.ArrayList;
import java.util.List;

import XmlWeb.dodatno.Konverter;
import XmlWeb.dto.KorisnikDTO;
import XmlWeb.model.Korisnik;
import XmlWeb.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	public List<Korisnik> getAllKorisnik(){
        List<Korisnik> allKorisnik = new ArrayList<>();
        korisnikRepo.findAll().forEach(allKorisnik::add);
       // System.out.println(allKorisniks.size());
        return allKorisnik;
    }
	
	public void addKorisnik(KorisnikDTO k){

        Konverter kon = new Konverter();
        Korisnik kor = kon.converterKorisnika(k, false);
        if(kor!=null)
            korisnikRepo.save(kor);

    }

    public Korisnik getKorisnik(String username){

        return korisnikRepo.findByUsername(username);
    }

    public Korisnik getKorisnik(Long id){
        return korisnikRepo.findById(id).get();
    }

    public void updateKorisnik(KorisnikDTO k){
        Konverter kon = new Konverter();
        Korisnik kor = kon.converterKorisnika(k, true);
        if(kor!=null)
            korisnikRepo.save(kor);

    }

    public void updatePassword(Korisnik a){
        korisnikRepo.save(a);
    }

    public void deleteKorisnik(Long id){
    	Korisnik a = korisnikRepo.findById(id).get();
        korisnikRepo.delete(a);
    }


}
