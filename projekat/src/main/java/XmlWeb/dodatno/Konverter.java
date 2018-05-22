package XmlWeb.dodatno;

import XmlWeb.dto.KomentarDTO;
import XmlWeb.dto.KorisnikDTO;
import XmlWeb.dto.PorukaDTO;
import XmlWeb.dto.RezervacijaDTO;
import XmlWeb.model.*;
import XmlWeb.model.Enums.Role;
import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.model.Enums.StatusRezevacije;
import XmlWeb.repository.KorisnikRepository;
import XmlWeb.repository.RezervacijaRepository;
import XmlWeb.repository.SobaRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Konverter {

    @Autowired
    private RezervacijaRepository rezRepo;

    @Autowired
    private KorisnikRepository korRepo;

    @Autowired
    private SobaRepository sobaRepo;


    private boolean proveraKomentara(KomentarDTO kom){

        boolean b = false;
        if(kom.getRezervacijaId()!=null)
            if(kom.getTekst()!=null)
                if(kom.getRezervacijaId()>0)
                    if(kom.getTekst().trim().length()>0)
                        b= rezRepo.existsById(kom.getRezervacijaId());

        return b;
    }


    public Komentar convertKomentara(KomentarDTO kom){
        Komentar k =null;
        if(proveraKomentara(kom)){
            k= new Komentar();
            k.setTekst(kom.getTekst());
            k.setOdobreno(false);
            Rezervacija r = new Rezervacija();
            r.setId(kom.getRezervacijaId());
            k.setRezervacija(r);
        }
        return k;
    }


    private boolean proveraPoruke(PorukaDTO por){
        boolean b = false;
        if(por.getPosiljalacId()!=null)
            if(por.getPrimalacId()!=null)
                if(por.getText()!=null)
                    if(por.getPrimalacId()>0)
                        if(por.getPosiljalacId()>0)
                            if(por.getText().trim().length()>0)
                                if(korRepo.existsById(por.getPosiljalacId()))
                                    b= korRepo.existsById(por.getPrimalacId());
        return b;

    }

    public Poruka converterPoruke(PorukaDTO por){
        Poruka p = null;
        if(proveraPoruke(por)){
            p= new Poruka();
            p.setTekst(por.getText());
            p.setVremeKreiranja(new Date());
            Korisnik k1 = new Korisnik();
            k1.setId(por.getPrimalacId());
            p.setPrimalac(k1);
            Korisnik k2 = new Korisnik();
            k2.setId(por.getPosiljalacId());
            p.setPosiljalac(k2);
        }
        return p;
    }


    private boolean proveraKorisnika(KorisnikDTO kor, boolean update){

        boolean b= false;
        if(kor.getEmail()!=null)
            if(kor.getFirstName()!=null)
                if(kor.getLastName()!=null)
                    if(kor.getPassword()!=null)
                        if(kor.getRole()!=null)
                            if(kor.getEmail().trim().length()>0)
                                if(EmailValidator.getInstance().isValid(kor.getEmail()))
                                    if(kor.getFirstName().trim().length()>0)
                                        if(kor.getLastName().trim().length()>0)
                                            if(kor.getPassword().trim().length()>0)
                                                if(kor.getRole().trim().length()>0)
                                                        b=true;
        if(b==true){
            if(update){
                if(korRepo.findByUsername(kor.getUsername()) ==null)
                    b=false;
            }else{
                if(korRepo.findByUsername(kor.getUsername()) !=null)
                    b=false;

            }
        }

        return b;
    }

    public Korisnik converterKorisnika(KorisnikDTO kor, boolean update){

        Korisnik k = null;
        if(proveraKorisnika(kor, update)){
            k = new Korisnik();
            k.setAktiviran(false);
            k.setFirstName(kor.getFirstName());
            List<Smestaj> iz = new ArrayList<>();
            k.setIzdaje(iz);
            k.setLastName(kor.getLastName());
            k.setPassword(kor.getPassword());
            k.setPib(k.getPib());
            k.setRole(Role.valueOf(kor.getRole()));
            k.setUsername(kor.getUsername());
            List<Rezervacija> rez = new ArrayList<>();
            k.setRezervacije(rez);
            k.setEmail(kor.getEmail());
            k.setStatusNaloga(StatusKorisnika.NEPOTVRDJEN);
        }
        return k;
    }

    private boolean proveraRezervacije(RezervacijaDTO rez){
        boolean b= false;
        if(rez.getIdKorisnika()!=null)
            if(rez.getIdSobe()!=null)
                if(rez.getKrajnjeVreme()!=null)
                    if(rez.getPocetnoVreme()!=null)
                        if(rez.getIdKorisnika()>0)
                            if(rez.getIdSobe()>0)
                                if(rez.getKrajnjeVreme().compareTo(rez.getPocetnoVreme())>0)
                                    if(korRepo.existsById(rez.getIdKorisnika()))
                                        b= sobaRepo.existsById(rez.getIdSobe());
        return b;

    }

    public Rezervacija converterRezervacije(RezervacijaDTO rez){
        Rezervacija r = null;

        if(proveraRezervacije(rez)){
            r= new Rezervacija();
            r.setDatumOd(rez.getPocetnoVreme());
            r.setDatumDo(rez.getKrajnjeVreme());
            r.setOcena(-1);
            Korisnik k = new Korisnik();
            k.setId(rez.getIdKorisnika());
            r.setRezervisao(k);
            Soba s = new Soba();
            s.setId(rez.getIdSobe());
            r.setSoba(s);
            r.setStatus(StatusRezevacije.NAPRAVLJENO);
            r.setOcenio(false);
        }

        return r;

    }

    public Konverter(){}
}
