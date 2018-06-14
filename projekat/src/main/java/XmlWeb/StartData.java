package XmlWeb;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import XmlWeb.model.Korisnik;
import XmlWeb.model.Poruka;
import XmlWeb.model.Rezervacija;
import XmlWeb.model.Smestaj;
import XmlWeb.model.Soba;
import XmlWeb.model.Enums.Role;
import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.model.Enums.StatusRezevacije;
import XmlWeb.model.security.Authority;
import XmlWeb.model.security.AuthorityName;
import XmlWeb.repository.AuthorityRepository;
import XmlWeb.repository.KorisnikRepository;
import XmlWeb.repository.PorukaRepository;
import XmlWeb.repository.RezervacijaRepository;
import XmlWeb.repository.SmestajRepository;
import XmlWeb.repository.SobaRepository;


@Component
public class StartData {
	
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
    private PorukaRepository porukaRepo;

	@Autowired
    private SmestajRepository smestajRepository;

	@Autowired
    private SobaRepository sobaRepository;

	@Autowired
    private RezervacijaRepository rezervacijaRepository;
	
	@Autowired
    private AuthorityRepository authorityRepository;

	 @PostConstruct
	 public void initIt(){
		 Korisnik k = new Korisnik();
		 k.setUsername("admin");		
		 k.setPassword(bCryptPasswordEncoder.encode("admin"));
         k.setFirstName("Pera");
         k.setLastName("Peric");
         k.setAktiviran(true);
         k.setRole(Role.ADMIN);
         k.setStatusNaloga(StatusKorisnika.AKTIVAN);
         k.setEmail("admir@admir.com");
         k.setLastPasswordResetDate(new Date());
         k.setIzdaje(new ArrayList<>());
         k.setRezervacije(new ArrayList<>());
         List l = new ArrayList<>();
         Authority a = new Authority();
         a.setName(AuthorityName.ROLE_ADMIN);
         authorityRepository.save(a);
         l.add(a);
         k.setAuthorities(l);
         korisnikRepo.save(k);
         System.out.println("dodao admira");
//         addUser("test", "test", "Minja", "Car", "test@gmail.com" , Role.USER);
//         addUser("Mirko", "mirko", "Mirko", "Mirkovic", "mirko@gmail.com" , Role.AGENT);
//         addUser("Slavko", "slavko", "Slavko", "Slavic", "slavko@gmail.com" , Role.AGENT);
//
//         addMessage(2L, 3L, "Testiram poruke",1);
//         addMessage(3L, 2L, "Obrnut redosled",2);
//         addMessage(2L, 3L, "Evo poslao sam ti",3);
//         addMessage(2L, 4L, "Ziv si li Slavko ? ", 4);
//
//         addRezervacija(2l, 3l, 0);
//         addRezervacija(2l, 3l, 12);


	 }

	 public void addRezervacija(Long idKorisnika, Long idVlasnika, int i){ // Nije potpuna, samo meni za testiranje
         Rezervacija r = new Rezervacija();
         Soba s = new Soba();

         s= sobaRepository.save(s);

         Korisnik k1 = new Korisnik();
         k1.setId(idVlasnika);
         Smestaj sm = new Smestaj();
         sm.setNaziv("Maldivi VIP");
         sm.setOpis("Najbolji smestaj");
         sm.setAdresa("Negde daleko");
         sm.setVlasnik(k1);

         sm = smestajRepository.save(sm);

         Korisnik k = new Korisnik();
         k.setId(idKorisnika);

         r.setSmestaj(sm);
         r.setStatus(StatusRezevacije.CONFIRMED);
         r.setOcenio(false);
         r.setRezervisao(k);


         Calendar myCal = Calendar.getInstance();
         myCal.set(Calendar.YEAR, 2018);
         myCal.set(Calendar.MONTH, 4);
         myCal.set(Calendar.DAY_OF_MONTH, 10+i);
         Date theDate = myCal.getTime();


         r.setDatumDo(theDate);

         myCal.set(Calendar.YEAR, 2018);
         myCal.set(Calendar.MONTH, 4);
         myCal.set(Calendar.DAY_OF_MONTH, 1+i);
         Date date2 = myCal.getTime();


         r.setDatumOd(date2);

         r.setSoba(s);

         rezervacijaRepository.save(r);

     }

	 public void addMessage(Long posiljalac, Long primalac, String tekst, int i){


         Calendar myCal = Calendar.getInstance();
         myCal.set(Calendar.YEAR, 2018);
         myCal.set(Calendar.MONTH, 4);
         myCal.set(Calendar.DAY_OF_MONTH, i);
         Date theDate = myCal.getTime();


	     Korisnik k1 = new Korisnik();
	     k1.setId(posiljalac);

	     Korisnik k2 = new Korisnik();
	     k2.setId(primalac);

         Poruka p = new Poruka();
         p.setPosiljalac(k1);
         p.setPrimalac(k2);

       //  Date d = new Date();
         p.setVremeKreiranja(theDate);
         p.setTekst(tekst);

         porukaRepo.save(p);


     }

	 public void addUser(String username, String password, String name, String last, String email, Role r){

         Korisnik k = new Korisnik();
         k.setUsername(username);
         k.setPassword(password);
         k.setFirstName(name);
         k.setLastName(last);
         k.setAktiviran(true);
         k.setRole(r);
         k.setStatusNaloga(StatusKorisnika.AKTIVAN);
         k.setEmail(email);
         k.setIzdaje(new ArrayList<>());
         k.setRezervacije(new ArrayList<>());
         korisnikRepo.save(k);


     }
}
