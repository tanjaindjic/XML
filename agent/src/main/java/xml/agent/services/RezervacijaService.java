package xml.agent.services;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xml.agent.dodatno.Konverter;
import xml.agent.dto.RezStatusUpdateDTO;
import xml.agent.dto.RezervacijaDTO;
import xml.agent.model.Rezervacija;
import xml.agent.model.Smestaj;
import xml.agent.model.Soba;
import xml.agent.model.Enums.StatusRezevacije;
import xml.agent.repository.RezervacijaRepository;
import xml.agent.repository.SmestajRepository;
import xml.agent.repository.SobaRepository;

@Service
public class RezervacijaService {

    @Autowired
    private RezervacijaRepository rezRepo;

    @Autowired
    private SobaRepository sobaRepo;

    @Autowired
    private SmestajRepository smRepo;


    public Long getSmestajId(Long rezervacijaId){

        Long l = null;

        Optional<Rezervacija> rezOp = rezRepo.findById(rezervacijaId);
        if (rezOp.isPresent()){
            Rezervacija r = rezOp.get();
            l=r.getSmestaj().getId();
        }

        return l;
    }

    public List<Rezervacija> getRezervacije(Long korisnikId){

        System.out.println("GETUJE SVE REZERVACIJE");

        List<Rezervacija> l = rezRepo.findByRezervisaoId(korisnikId);

        System.out.println("USPEO DA GETUJE : " + l.size());

        return l;
    }

    public List<Rezervacija> getRezervacijeAgent(Long idSmestaja){
        return rezRepo.findBySobaId(idSmestaja);
    }

    @Transactional( readOnly = false, propagation = Propagation.REQUIRED)
    public boolean rezervisi(RezervacijaDTO rez){

        System.out.println("UPAO U REZERVACIJA SERVICE");

        boolean b = false;
        Konverter k = new Konverter();
        boolean uzeto = false;
        Rezervacija r = k.converterRezervacije(rez);
        if(r!=null){
            Optional<Soba> sobaOp = sobaRepo.findById(r.getSoba().getId());

            System.out.println("PROSAO KONVERTER");

            if (sobaOp.isPresent()){

                System.out.println("NASAO SOBU");

                Soba soba = sobaOp.get();

                List<Rezervacija> iznajmljeno = soba.getRezervisano();


                for (Rezervacija zauzeto: iznajmljeno) {

                    System.out.println("NASAO REZERVACIJE");

                    if (((r.getDatumOd().compareTo(zauzeto.getDatumDo()) <0) && (r.getDatumDo().compareTo(zauzeto.getDatumDo())>0)) ||  ((zauzeto.getDatumOd().compareTo(r.getDatumDo()) <0) && zauzeto.getDatumDo().compareTo(r.getDatumDo())>0 ))
                        uzeto=true;
                }

                if(!uzeto){

                    System.out.println("SLOBODNA JE");


                    r =rezRepo.save(r);

                    System.out.println("CUVA REZERVACIJU");


                    soba.getRezervisano().add(r);

                    System.out.println("DODAJE REZERVACIJU U SOBU");

                    sobaRepo.save(soba);

                    System.out.println("CUVA SOBU");


                    b=true;

                }


            }


        }
        return b;
    }

    @Transactional( readOnly = false, propagation = Propagation.REQUIRED)
    public void addOcena(Long id, int ocena){
        if(id >0)
            if(ocena>=0 && ocena<=5){

                Optional<Rezervacija> rezOp = rezRepo.findById(id);
                if (rezOp.isPresent()){
                    Rezervacija r =rezOp.get();
                    r.setOcenio(true);
                    r.setOcena(ocena);
                    rezRepo.save(r);

                    Smestaj s = r.getSmestaj();

                    int broj = s.getBrojOcena();
                    float prosecna = s.getRejting();
                    if(broj ==0){
                        s.setBrojOcena(1);
                        s.setRejting(ocena);
                    }else {
                        float nova = (prosecna * broj + ocena) / (broj + 1);
                        s.setBrojOcena(broj + 1);
                        s.setRejting(nova);
                    }
                    smRepo.save(s);
                }

            }
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

    public Rezervacija getRez(Long id) {
        return rezRepo.findById(id).get();
    }

    public void saveRez(Rezervacija r) {
        rezRepo.save(r);
    }

	public List<Rezervacija> getPending() {
		// TODO Auto-generated method stub
		 List<Rezervacija> rezervacije = new ArrayList<>();
	        rezRepo.findByStatus(StatusRezevacije.PENDING).forEach(rezervacije::add);
	        return rezervacije;
	}
}
