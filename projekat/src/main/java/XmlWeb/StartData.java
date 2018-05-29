package XmlWeb;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import XmlWeb.model.Enums.Role;
import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.model.Korisnik;
import XmlWeb.model.Poruka;
import XmlWeb.repository.KorisnikRepository;
import XmlWeb.repository.PorukaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StartData {
	
	@Autowired
	private KorisnikRepository korisnikRepo;

	@Autowired
    private PorukaRepository porukaRepo;

	 @PostConstruct
	 public void initIt(){
		 Korisnik k = new Korisnik();
		 k.setUsername("admin");		
		 k.setPassword("admin");
         k.setFirstName("Pera");
         k.setLastName("Peric");
         k.setAktiviran(true);
         k.setRole(Role.ADMIN);
         k.setStatusNaloga(StatusKorisnika.AKTIVAN);
         k.setEmail("");
         k.setPib("");
         k.setIzdaje(new ArrayList<>());
         k.setRezervacije(new ArrayList<>());
         korisnikRepo.save(k);
         System.out.println("dodao admira");
         addUser("test", "test", "Minja", "Car", "test@gmail.com" , Role.USER);
         addUser("Mirko", "mirko", "Mirko", "Mirkovic", "mirko@gmail.com" , Role.AGENT);
         addUser("Slavko", "slavko", "Slavko", "Slavic", "slavko@gmail.com" , Role.AGENT);

         addMessage(2L, 3L, "Testiram poruke",1);
         addMessage(3L, 2L, "Obrnut redosled",2);
         addMessage(2L, 3L, "Evo poslao sam ti",3);
         addMessage(2L, 4L, "Ziv si li Slavko ? ", 4);


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
         k.setPib("");
         k.setIzdaje(new ArrayList<>());
         k.setRezervacije(new ArrayList<>());
         korisnikRepo.save(k);


     }
}
