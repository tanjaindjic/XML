package XmlWeb;



import java.util.ArrayList;

import javax.annotation.PostConstruct;

import XmlWeb.model.Enums.Role;
import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.model.Korisnik;
import XmlWeb.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StartData {
	
	@Autowired
	private KorisnikRepository korisnikRepo;

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
         addUser("test", "test", "Minja", "Car", Role.USER);

	 }

	 public void addUser(String username, String password, String name, String last, Role r){

         Korisnik k = new Korisnik();
         k.setUsername(username);
         k.setPassword(password);
         k.setFirstName(name);
         k.setLastName(last);
         k.setAktiviran(true);
         k.setRole(r);
         k.setStatusNaloga(StatusKorisnika.AKTIVAN);
         k.setEmail("radimail@gmail.com");
         k.setPib("");
         k.setIzdaje(new ArrayList<>());
         k.setRezervacije(new ArrayList<>());
         korisnikRepo.save(k);


     }
}
