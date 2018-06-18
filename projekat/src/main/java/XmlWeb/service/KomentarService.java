package XmlWeb.service;

import XmlWeb.dodatno.Konverter;
import XmlWeb.dto.KomentarDTO;
import XmlWeb.model.Komentar;
import XmlWeb.model.Rezervacija;
import XmlWeb.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KomentarService {

    @Autowired
    private KomentarRepository komRepo;

    public void addKomentar(KomentarDTO k){
        Konverter con = new Konverter();
        Komentar kom = con.convertKomentara(k);
        if(kom!=null)
            komRepo.save(kom);
    }

    public void promeniStatus(Long id, boolean b){

        Optional<Komentar> komOp = komRepo.findById(id);
        if (komOp.isPresent()){
            Komentar foo = komOp.get();
            foo.setOdobreno(b);
            komRepo.save(foo);
        }
        else{
            System.out.println("NEMA KOMENTARA GA U BAZI");
        }
    }

    public Komentar getKomentar(Long id){

        Optional<Komentar> komOp = komRepo.findById(id);
        if (komOp.isPresent()){
            Komentar foo = komOp.get();
            return foo;
        }
        else{
            System.out.println("NEMA KOMENTARA GA U BAZI");
            return null;
        }

    }

    public List<Komentar> getAllKomentari(){
        List<Komentar> kom = new ArrayList<>();
        komRepo.findAll().forEach(kom::add);
        return kom;
    }

    public List<Komentar> getKomentariByOsoba(Long id){
        return komRepo.findByRezervacijaRezervisao(id);
    }

    public List<Komentar> getKomentariBySoba(Long id){
        return komRepo.findByRezervacijaSobaId(id);
    }

    public List<Komentar> getKomentariByOsobaAndSoba(Long osoba, Long soba){
        return komRepo.findByRezervacijaRezervisaoIdAndRezervacijaSobaId(osoba, soba);
    }

    public List<Komentar> getUnpublished(){
        return komRepo.findByOdobreno(false);
    }


    public void publishComment(Long id) {
        Komentar k = komRepo.findById(id).get();
        k.setOdobreno(true);
        komRepo.save(k);
    }

    public void deleteKomentar(Long id) {
        komRepo.deleteById(id);
    }
}
