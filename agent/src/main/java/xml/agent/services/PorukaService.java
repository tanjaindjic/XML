package xml.agent.services;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.agent.dodatno.Konverter;
import xml.agent.dto.PorukaDTO;
import xml.agent.model.Korisnik;
import xml.agent.model.Poruka;
import xml.agent.repository.PorukaRepository;
import xml.agent.repository.SamoPosiljaoci;
import xml.agent.repository.SamoPrimaoci;

@Service
public class PorukaService {

    @Autowired
    private PorukaRepository porRepo;

    public void sendPoruka(PorukaDTO p){
        Konverter k = new Konverter();
        Poruka por = k.converterPoruke(p);
        if(por!=null){
            porRepo.save(por);
        }
    }

    public List<Poruka> getAllPoruke(){
        List<Poruka> poruke = new ArrayList<>();
        porRepo.findAll().forEach(poruke::add);
        return poruke;
    }

    public List<Korisnik> getInbox(Long korisnikId){

        List<Korisnik> inbox = new ArrayList<Korisnik>();
        HashMap<Long, Korisnik> mapa = new HashMap<>();

        List<SamoPosiljaoci> temp1 =  porRepo.findDistinctByPrimalacId(korisnikId);
        List<SamoPrimaoci> temp2 = porRepo.findDistinctByPosiljalacId(korisnikId);

        for (SamoPosiljaoci s1:temp1) {
            mapa.put(s1.getPosiljalac().getId(), s1.getPosiljalac());
        }

        for(SamoPrimaoci s2:temp2){
            mapa.put(s2.getPrimalac().getId(), s2.getPrimalac());
        }
        if(mapa.size()>0){
            inbox = new ArrayList<Korisnik>(mapa.values());
        }


        return inbox;
    }

    public List<Poruka> getChat(Long osoba1, Long osoba2){
        return porRepo.findDistinctByPosiljalacIdAndPrimalacIdOrPrimalacIdAndPosiljalacIdOrderByVremeKreiranjaAsc(osoba1, osoba2, osoba1, osoba2); //Jos malo kul madjije :D  OrderByVremeKreiranjaAsc
    }




}
