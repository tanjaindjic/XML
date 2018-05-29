package XmlWeb.service;

import XmlWeb.dodatno.Konverter;
import XmlWeb.dto.RezStatusUpdateDTO;
import XmlWeb.dto.RezervacijaDTO;
import XmlWeb.model.Enums.StatusRezevacije;
import XmlWeb.model.Rezervacija;
import XmlWeb.model.Soba;
import XmlWeb.repository.RezervacijaRepository;
import XmlWeb.repository.SobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RezervacijaService {

    @Autowired
    private RezervacijaRepository rezRepo;

    @Autowired
    private SobaRepository sobaRepo;

    public List<Rezervacija> getRezervacije(Long korisnikId){
        return rezRepo.findByRezervisaoId(korisnikId);
    }

    public List<Rezervacija> getRezervacijeAgent(Long idSmestaja){
        return rezRepo.findBySobaId(idSmestaja);
    }

    @Transactional( readOnly = false, propagation = Propagation.REQUIRED)
    public boolean rezervisi(RezervacijaDTO rez){

        boolean b = false;
        Konverter k = new Konverter();
        boolean uzeto = false;
        Rezervacija r = k.converterRezervacije(rez);
        if(r!=null){
            Optional<Soba> sobaOp = sobaRepo.findById(r.getSoba().getId());
            if (sobaOp.isPresent()){
                Soba soba = sobaOp.get();

                List<Rezervacija> iznajmljeno = soba.getRezervisano();
                for (Rezervacija zauzeto: iznajmljeno) {
                    if (((r.getDatumOd().compareTo(zauzeto.getDatumDo()) <0) && (r.getDatumDo().compareTo(zauzeto.getDatumDo())>0)) ||  ((zauzeto.getDatumOd().compareTo(r.getDatumDo()) <0) && zauzeto.getDatumDo().compareTo(r.getDatumDo())>0 ))
                        uzeto=true;
                }

                if(!uzeto){
                    soba.getRezervisano().add(r);
                    sobaRepo.save(soba);
                    rezRepo.save(r);
                    b=true;

                }


            }


        }
        return b;
    }

    public void updateStatus(RezStatusUpdateDTO rsud ){

        Optional<Rezervacija> rezOp = rezRepo.findById(rsud.getId());
        if (rezOp.isPresent()){
            Rezervacija r = rezOp.get();
            r.setStatus(StatusRezevacije.valueOf(rsud.getStatus()));
            rezRepo.save(r);
        }

    }


    public List<Rezervacija> getAll(){
        List<Rezervacija> rezervacije = new ArrayList<>();
        rezRepo.findAll().forEach(rezervacije::add);
        return rezervacije;
    }


}
