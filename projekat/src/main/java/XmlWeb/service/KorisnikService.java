package XmlWeb.service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import XmlWeb.dodatno.Konverter;
import XmlWeb.dto.KorisnikDTO;
import XmlWeb.dto.RegisterDTO;
import XmlWeb.model.Korisnik;
import XmlWeb.model.Enums.Role;
import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.model.security.Authority;
import XmlWeb.model.security.AuthorityName;
import XmlWeb.repository.AuthorityRepository;
import XmlWeb.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AuthorityRepository autoRepo;
	

	public List<Korisnik> getAllKorisnik() {
		List<Korisnik> allKorisnik = new ArrayList<>();
		korisnikRepo.findAll().forEach(allKorisnik::add);
		// System.out.println(allKorisniks.size());
		return allKorisnik;
	}

	public void addKorisnik(KorisnikDTO k) {

		Konverter kon = new Konverter();
		Korisnik kor = kon.converterKorisnika(k, false);
		if (kor != null)
			korisnikRepo.save(kor);

	}

	public Korisnik getKorisnik(String username) {

		return korisnikRepo.findByUsername(username);
	}

	public Korisnik getKorisnik(Long id) {
		return korisnikRepo.findById(id).get();
	}

	public void updateKorisnik(KorisnikDTO k) {
		Konverter kon = new Konverter();
		Korisnik kor = kon.converterKorisnika(k, true);
		if (kor != null) {
			Korisnik id = korisnikRepo.findByUsername(kor.getUsername());

			if (id != null) {
				kor.setId(id.getId());
				if (id.getStatusNaloga() != null)
					kor.setStatusNaloga(id.getStatusNaloga());
				kor.setRezervacije(id.getRezervacije());
				kor.setPib(id.getPib());
				kor.setIzdaje(id.getIzdaje());
				kor.setAktiviran(id.isAktiviran());
				kor.setRole(id.getRole());
			}

			korisnikRepo.save(kor);
		}
	}

	public void updatePassword(Korisnik a) {
		korisnikRepo.save(a);
	}

	public void deleteKorisnik(Long id) {
		Korisnik a = korisnikRepo.findById(id).get();
		korisnikRepo.delete(a);
	}

	public Korisnik findByEmail(String email) {
		return korisnikRepo.findByEmail(email);
	}

	public Korisnik findByConfirmationToken(String confirmationToken) {
		return korisnikRepo.findByConfirmationToken(confirmationToken);
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity<HashMap> registerKorisnik(RegisterDTO regDetails) throws URISyntaxException, MalformedURLException, InterruptedException {
		HashMap<String, String> map = new HashMap<>();
		Korisnik k = korisnikRepo.findByUsername(regDetails.getUsername());
		if(k!=null) {
			map.put("text", "Username is already taken.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}
			
			
		k = korisnikRepo.findByEmail(regDetails.getEmail());
		if(k!=null) {
			map.put("text", "Username is already taken.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}
			
			
		if(!regDetails.getPassword1().equals(regDetails.getPassword2()))
			{
			map.put("text", "Passwords don't match.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
			}
		
		/*Zxcvbn passwordCheck = new Zxcvbn();		
		Strength strength = passwordCheck.measure(regDetails.getPassword1());
		if (strength.getScore() < 1) {
			map.put("text","Your password is too weak. Please choose a stronger one.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}
			*/
		Korisnik novi = new Korisnik();
		novi.setAktiviran(false);
		List<Authority> l = new ArrayList<>();
		Authority a = new Authority();
		
		if(regDetails.getIsAgent()!=null)
			a.setName(AuthorityName.ROLE_AGENT);
		else a.setName(AuthorityName.ROLE_USER);
		autoRepo.save(a);
		l.add(a);
		novi.setAuthorities(l);
		novi.setEmail(regDetails.getEmail());
		novi.setFirstName(regDetails.getFirstname());
		novi.setLastName(regDetails.getLastname());
		novi.setPassword(bCryptPasswordEncoder.encode(regDetails.getPassword1()));
		novi.setIzdaje(new ArrayList<>());
		novi.setRezervacije(new ArrayList<>());
		novi.setStatusNaloga(StatusKorisnika.NEPOTVRDJEN);
		novi.setLastPasswordResetDate(new Date());
		novi.setUsername(regDetails.getUsername());
		if(novi.getAuthorities().get(0).getName().toString().equals(AuthorityName.ROLE_AGENT.toString()))
			novi.setRole(Role.AGENT);
		else novi.setRole(Role.USER);
		novi.setConfirmationToken(UUID.randomUUID().toString());
		korisnikRepo.save(novi);
		
		
	    String link =  "Please go to following link to activate your account: https://localhost:8096/confirm?token=";
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(novi.getEmail());
		registrationEmail.setSubject("Registration Confirmation");
		registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
				+ link + "/confirm?token=" + novi.getConfirmationToken());
		registrationEmail.setFrom("noreply@domain.com");
		
		emailService.sendEmail(registrationEmail);
		//return new ResponseEntity<String>("Almost there! Please finish your registration via link we sent on your email.", HttpStatus.OK);
		
		map.put("text", "Almost there! Please finish your registration via link we sent on your email.");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
