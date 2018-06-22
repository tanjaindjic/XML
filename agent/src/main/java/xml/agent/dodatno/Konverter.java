package xml.agent.dodatno;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

import XmlWeb.dto.KomentarDTO;
import xml.agent.dto.KorisnikDTO;
import xml.agent.dto.PorukaDTO;
import xml.agent.dto.RezervacijaDTO;
import xml.agent.model.Komentar;
import xml.agent.model.Korisnik;
import xml.agent.model.Poruka;
import xml.agent.model.Rezervacija;
import xml.agent.model.Smestaj;
import xml.agent.model.Soba;
import xml.agent.model.Enums.Role;
import xml.agent.model.Enums.StatusKorisnika;
import xml.agent.model.Enums.StatusRezevacije;
public class Konverter {

   /* @Autowired
    private RezervacijaRepository rezRepo;

    @Autowired
    private KorisnikRepository korRepo;

    @Autowired
    private SobaRepository sobaRepo;

    @Autowired
    private Konverter madjija;
    */


    private boolean proveraKomentara(KomentarDTO kom){

        boolean b = false;
        if(kom.getRezervacijaId()!=null)
            if(kom.getTekst()!=null)
                if(kom.getRezervacijaId()>0)
                    if(kom.getTekst().trim().length()>0)
                        b=true;  //rezRepo.existsById(kom.getRezervacijaId());

        return b;
    }


    public Komentar convertKomentara(KomentarDTO kom){
        Komentar k =null;
        if(proveraKomentara(kom)){
            System.out.println("NE PROLAZI KONVERTER");
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

        System.out.println("Posiljalac : " +por.getPosiljalacId());
        System.out.println("Primalac : " + por.getPrimalacId());
        System.out.println("Text : "+por.getText() );



        boolean b = false;
        if(por.getPosiljalacId()!=null)
            if(por.getPrimalacId()!=null)
                if(por.getText()!=null)
                    if(por.getPrimalacId()>0)
                        if(por.getPosiljalacId()>0)
                            if(por.getText().trim().length()>0)
                                //if(korRepo.findById(por.getPosiljalacId()).get() !=null)
                                   // if(korRepo.findById(por.getPrimalacId()).get() !=null)
                                        b=true;

        System.out.println("b je : ");
        if(b)
            System.out.println("TRUE");
        else
            System.out.println("FALSE");


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

      /*  System.out.println("PROVERAVA KORISNIKA");
        System.out.println("EMAIL : " + kor.getEmail());
        System.out.println("FIRST NAME : " + kor.getFirstName());
        System.out.println("LAST NAME : " +kor.getLastName());
        System.out.println("PASSWORD : " +kor.getPassword());
        System.out.println("ROLE : " +kor.getRole());
        if(EmailValidator.getInstance().isValid(kor.getEmail())){
            System.out.println("EMAIL JE OK");
        }
      */
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
     /*   if(b==true){
            if(update){
                if(korRepo.findByUsername(kor.getUsername()) ==null)
                    b=false;
            }else{
                if(korRepo.findByUsername(kor.getUsername()) !=null)
                    b=false;

            }
        }
        */

        return b;
    }

    public Korisnik converterKorisnika(KorisnikDTO kor, boolean update){

        Korisnik k = null;
        if(proveraKorisnika(kor, update)){
        //    System.out.println("KONVETUJE KORISNIKA");
            k = new Korisnik();
            k.setAktiviran(false);
            k.setFirstName(kor.getFirstName());
            List<Smestaj> iz = new ArrayList<>();
            k.setIzdaje(iz);
            k.setLastName(kor.getLastName());
            k.setPassword(kor.getPassword());
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
                                    if(rez.getIdSmestaja() != null)
                                        if(rez.getIdSmestaja()>0)
                                        //  if(korRepo.existsById(rez.getIdKorisnika()))
                                            b= true; // sobaRepo.existsById(rez.getIdSobe());
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
            Smestaj sm = new Smestaj();
            sm.setId(rez.getIdSmestaja());
            s.setId(rez.getIdSobe());
            r.setSoba(s);
            r.setSmestaj(sm);
            r.setStatus(StatusRezevacije.PENDING);
            r.setOcenio(false);
        }

        return r;

    }

    public Konverter(){}
}
