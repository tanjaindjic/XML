package xml.agent;





import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import xml.agent.model.Iznajmljivanje;
import xml.agent.model.Komentar;
import xml.agent.model.Korisnik;
import xml.agent.model.Poruka;
import xml.agent.model.Rezervacija;
import xml.agent.model.Slika;
import xml.agent.model.Smestaj;
import xml.agent.model.Soba;
import xml.agent.model.Enums.DodatneUsluge;
import xml.agent.model.Enums.KategorijaSmestaja;
import xml.agent.model.Enums.Role;
import xml.agent.model.Enums.StatusKorisnika;
import xml.agent.model.Enums.StatusRezevacije;
import xml.agent.model.Enums.TipSmestaja;
import xml.agent.model.security.Authority;
import xml.agent.model.security.AuthorityName;
import xml.agent.repository.AuthorityRepository;
import xml.agent.repository.DodatneUslugeRepository;
import xml.agent.repository.KategorijaRepository;
import xml.agent.repository.KorisnikRepository;
import xml.agent.repository.PorukaRepository;
import xml.agent.repository.RezervacijaRepository;
import xml.agent.repository.SmestajRepository;
import xml.agent.repository.SobaRepository;
import xml.agent.repository.TipSmestajaRepository;


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

    @Autowired
    private TipSmestajaRepository tipSmestajaRepository;

    @Autowired
    private KategorijaRepository kategorijaRepository;
    
    @Autowired
    private DodatneUslugeRepository dodatneRepository;


	 @PostConstruct
	 public void initIt(){
      

         //creating permissions and roles
 
        //za sad samo ove 2 permisije
         Authority adminAuthority = new Authority(AuthorityName.ROLE_ADMIN, new ArrayList<>(),  new ArrayList<>());
         Authority agentAuthority = new Authority(AuthorityName.ROLE_AGENT, new ArrayList<>(),  new ArrayList<>());
         Authority userAuthority = new Authority(AuthorityName.ROLE_USER, new ArrayList<>(),  new ArrayList<>());
         authorityRepository.save(adminAuthority);
         authorityRepository.save(agentAuthority);
         authorityRepository.save(userAuthority);

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
         l.add(adminAuthority);
         k.setAuthorities(l);
         korisnikRepo.save(k);
         adminAuthority.getUsers().add(k);
         authorityRepository.save(adminAuthority);
         System.out.println("dodao admina");



        Korisnik k1 =  addUser("test", "test", "Minja", "Car1", "test@gmail.com" , Role.USER);
        Korisnik k2 = addUser("agent", "agent", "Mirko", "Mirkovic", "mirko@gmail.com" , Role.AGENT);
        Korisnik k3 = addUser("Slavko", "slavko", "Slavko", "Slavic", "slavko@gmail.com" , Role.AGENT);
//
        addMessage(k1.getId(), k2.getId(), "Testiram poruke",1);
         addMessage(k2.getId(), k1.getId(), "Obrnut redosled",2);
         addMessage(k1.getId(), k2.getId(), "Evo poslao sam ti",3);
         addMessage(k1.getId(), k3.getId(), "Ziv si li Slavko ? ", 4);
//
         addRezervacija(k1.getId(), k2.getId(), 0);
         addRezervacija(k1.getId(), k2.getId(), 12);

         TipSmestaja tipSmestaja = new TipSmestaja();
         tipSmestaja.setTip("Kuca");
         tipSmestajaRepository.save(tipSmestaja);

         /*KategorijaSmestaja kategorijaSmestaja = new KategorijaSmestaja();
         kategorijaSmestaja.setKategorija("Vrh");
         kategorijaRepository.save(kategorijaSmestaja);*/
         
         addSmestaj("Smestaj1", "Adresa1", "Grad1", "Drzava1", 1, 0, 1, 2, new Date(118, 1, 1), new Date(119 , 1, 1),100L, (float)3.5);
         addSmestaj("Smestaj1", "Adresa2", "Grad1", "Drzava2", 2, 2, 4, 1, new Date(118, 1, 1), new Date(119 , 1, 1),200L, (float)2.6);
         addSmestaj("Smestaj1", "Adresa3", "Grad3", "Drzava3", 5, 0, 4, 3, new Date(118, 1, 1), new Date(119 , 1, 1),2000L, (float)4.5);
	 }

	 public void addRezervacija(Long idKorisnika, Long idVlasnika, int i){ // Nije potpuna, samo meni za testiranje
         Rezervacija r = new Rezervacija();
         Soba s = new Soba();

         s= sobaRepository.save(s);

         Korisnik k1 = new Korisnik();
         k1.setId(idVlasnika);
         /*Dodajem deo za dodavanje slike, nemo jme ubiti stankovicu*/
         
         ArrayList<Soba> sobe = new ArrayList<>();
         ArrayList<Slika> slike = new ArrayList<>();
         slike.add(new Slika("slika1"));
         slike.add(new Slika("slika2"));
         Soba temp = new Soba(1, new ArrayList<>(), new ArrayList<>());
         Soba temp1 = new Soba(2, new ArrayList<>(), new ArrayList<>());
         Soba temp2 = new Soba(3, new ArrayList<>(), new ArrayList<>());
         ArrayList<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
         iznajmljivanja.add(new Iznajmljivanje(new Date(118, 1, 1), new Date(119 , 1, 1), 1000L, true));
         ArrayList<Iznajmljivanje> iznajmljivanja1 = new ArrayList<>();
         iznajmljivanja1.add(new Iznajmljivanje(new Date(118, 1, 1), new Date(119 , 1, 1), 1100L, true));
         ArrayList<Iznajmljivanje> iznajmljivanja2 = new ArrayList<>();
         iznajmljivanja2.add(new Iznajmljivanje(new Date(118, 1, 1), new Date(119 , 1, 1), 1200L, true));
         
         temp.setIznajmljivanja(iznajmljivanja);
         temp1.setIznajmljivanja(iznajmljivanja1);
         temp2.setIznajmljivanja(iznajmljivanja2);
         
         sobe.add(temp);
         sobe.add(temp1);
         sobe.add(temp2);
         
         /*Dodajem deo za dodavanje slike, nemo jme ubiti stankovicu*/
         Smestaj sm = new Smestaj();
         sm.setNaziv("Maldivi VIP");
         sm.setOpis("Hotel Slavija je smeÅ¡ten u centru Beograda, u neposrednoj blizini raznovrsnih prodavnica i restorana. U ponudi ima besplatan beÅ¾iÄ�ni internet u zajedniÄ�kim prostorijama i svim smeÅ¡tajnim jedinicama. Nalazi se na svega par koraka od Hrama Sv. Save, koji je jedna od najvaÅ¾nijih gradskih znamenitosti.\n" + 
         		"\n" + 
         		"Sve smeÅ¡tajne jedinice poseduju sopstveno kupatilo sa kadom ili tuÅ¡em. VeÄ‡ina smeÅ¡tajnih jedinica sadrÅ¾i TV. KoriÅ¡Ä‡enje hotelske garaÅ¾e na raspolaganju je uz doplatu.\n" + 
         		"\n" + 
         		"Å etaliÅ¡te u Knez Mihailovoj ulici udaljeno je 15 minuta hoda. Drevna Kalemegdanska tvrÄ‘ava i boemska Ä�etvrt Skadarlija udaljene su 2,5 km. Jezero Ada Ciganlija, poznato po mestima za noÄ‡ni provod, udaljeno je 4 km od objekta.\n" + 
         		"\n" + 
         		"Stanica autobusa koji saobraÄ‡a do MeÄ‘unarodnog aerodroma Beograd, udaljenog 16 km, nalazi se dirketno ispred objekta. Tramvajska stanica na Trgu Slavija udaljena je 200 metara. Autobuska i Å½elezniÄ�ka stanica u Beogradu udaljene su 1,2 km od hotela Slavija.\n" + 
         		"\n" + 
         		"Prema nezavisnim recenzijama, naÅ¡i gosti oboÅ¾avaju ovaj deo destinacije Beograd.\n" + 
         		"\n" + 
         		"Gosti koji su ovde boravili priÄ�aju o sledeÄ‡im poznatim znamenitostima: Ada Ciganlija, Hram Sv. Save i Trg Republike.\n" + 
         		"\n" + 
         		"Govorimo vaÅ¡ jezik! ");
         sm.setAdresa("Negde daleko");
         sm.setGrad("Class Grad");
         sm.setDrzava("Class Drzava");
         ArrayList<KategorijaSmestaja> kats = (ArrayList<KategorijaSmestaja>) kategorijaRepository.findAll();
         sm.setKategorija(kats.get(1));
         sm.setVlasnik(k1);
         
         
         /*Dodajem deo za dodavanje slike, nemo jme ubiti stankovicu*/
         sm.setSlike(slike);
         sm.setSobe(sobe);
         /*Dodajem deo za dodavanje slike, nemo jme ubiti stankovicu*/


         sm = smestajRepository.save(sm);

         Korisnik k = new Korisnik();
         k.setId(idKorisnika);

         r.setSmestaj(sm);
         r.setStatus(StatusRezevacije.PENDING);
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

         r.setSoba(temp2);

         rezervacijaRepository.save(r);
        

     }
	 
	 public void addSmestaj(String naziv, String adresa, String grad, String drzava, Integer zvezdice, int dodatne1, int dodatne2, int tipint, Date pocetak, Date kraj, Long cena, float rejting) {
		 Smestaj temp = new Smestaj();
		 temp.setNaziv(naziv);
		 temp.setAdresa(adresa);
		 temp.setGrad(grad);
		 temp.setDrzava(drzava);
		 temp.setRejting(rejting);
		 ArrayList<KategorijaSmestaja> kats = (ArrayList<KategorijaSmestaja>) kategorijaRepository.findAll();
		 temp.setKategorija(kats.get(zvezdice));
		 ArrayList<Slika> slike = new ArrayList<>();
         slike.add(new Slika("slika1"));
         slike.add(new Slika("slika2"));
         temp.setSlike(slike);
         temp.setOpis("Hotel Slavija je smeÅ¡ten u centru Beograda, u neposrednoj blizini raznovrsnih prodavnica i restorana. U ponudi ima besplatan beÅ¾iÄ�ni internet u zajedniÄ�kim prostorijama i svim smeÅ¡tajnim jedinicama. Nalazi se na svega par koraka od Hrama Sv. Save, koji je jedna od najvaÅ¾nijih gradskih znamenitosti.\n" + 
          		"\n" + 
          		"Sve smeÅ¡tajne jedinice poseduju sopstveno kupatilo sa kadom ili tuÅ¡em. VeÄ‡ina smeÅ¡tajnih jedinica sadrÅ¾i TV. KoriÅ¡Ä‡enje hotelske garaÅ¾e na raspolaganju je uz doplatu.\n" + 
          		"\n" + 
          		"Å etaliÅ¡te u Knez Mihailovoj ulici udaljeno je 15 minuta hoda. Drevna Kalemegdanska tvrÄ‘ava i boemska Ä�etvrt Skadarlija udaljene su 2,5 km. Jezero Ada Ciganlija, poznato po mestima za noÄ‡ni provod, udaljeno je 4 km od objekta.\n" + 
          		"\n" + 
          		"Stanica autobusa koji saobraÄ‡a do MeÄ‘unarodnog aerodroma Beograd, udaljenog 16 km, nalazi se dirketno ispred objekta. Tramvajska stanica na Trgu Slavija udaljena je 200 metara. Autobuska i Å½elezniÄ�ka stanica u Beogradu udaljene su 1,2 km od hotela Slavija.\n" + 
          		"\n" + 
          		"Prema nezavisnim recenzijama, naÅ¡i gosti oboÅ¾avaju ovaj deo destinacije Beograd.\n" + 
          		"\n" + 
          		"Gosti koji su ovde boravili priÄ�aju o sledeÄ‡im poznatim znamenitostima: Ada Ciganlija, Hram Sv. Save i Trg Republike.\n" + 
          		"\n" + 
          		"Govorimo vaÅ¡ jezik! ");
		 ArrayList<DodatneUsluge> dodatneSve = (ArrayList<DodatneUsluge>) dodatneRepository.findAll();
		 temp.setDodatneUsluge(dodatneSve.subList(dodatne1, dodatne2));
		 ArrayList<TipSmestaja> tipSve = (ArrayList<TipSmestaja>) tipSmestajaRepository.findAll();
		 temp.setTip(tipSve.get(tipint));
		 
		 ArrayList<Soba> sobe = new ArrayList<>();
		 Soba temps = new Soba(5, new ArrayList<>(), new ArrayList<>());
		 Soba temps1 = new Soba(4, new ArrayList<>(), new ArrayList<>());
         ArrayList<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
         iznajmljivanja.add(new Iznajmljivanje(pocetak, kraj, cena, true));
         ArrayList<Iznajmljivanje> iznajmljivanja1 = new ArrayList<>();
         iznajmljivanja1.add(new Iznajmljivanje(new Date(120, 1, 1), new Date(121, 1, 1), cena, true));
         
         temps.setIznajmljivanja(iznajmljivanja);
         temps1.setIznajmljivanja(iznajmljivanja1);
         sobe.add(temps);
         sobe.add(temps1);
         temp.setSobe(sobe);
         
         smestajRepository.save(temp);

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

	 public Korisnik addUser(String username, String password, String name, String last, String email, Role r){

         Korisnik k = new Korisnik();
         k.setUsername(username);
         k.setPassword(bCryptPasswordEncoder.encode(password));
         k.setFirstName(name);
         k.setLastName(last);
         k.setAktiviran(true);
         k.setRole(r);
         k.setStatusNaloga(StatusKorisnika.AKTIVAN);
         k.setEmail(email);
         k.setConfirmationToken("");
         k.setLastPasswordResetDate(new Date());
         k.setIzdaje(new ArrayList<>());
         k.setRezervacije(new ArrayList<>());
         List l = new ArrayList<>();
         Authority a;
         //OVO SAM IZMENILA JER CE BITI ZAKUCANE ROLE NEMA STA DA SE DODAJE SAMO DA NADJE ODGOVARAJUCU IZ REPOSITORY
         if(r == Role.USER)
            a = authorityRepository.findByName(AuthorityName.ROLE_USER);
         else if (r == Role.AGENT)
             a = authorityRepository.findByName(AuthorityName.ROLE_AGENT);
         else
             a = authorityRepository.findByName(AuthorityName.ROLE_ADMIN);


         l.add(a);
         k.setAuthorities(l);
         k = korisnikRepo.save(k);
         a.getUsers().add(k);
         authorityRepository.save(a);
         System.out.println("Dodao korisnika");

         return k;


     }

     
}
