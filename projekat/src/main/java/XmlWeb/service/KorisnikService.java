package XmlWeb.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

import XmlWeb.dodatno.Konverter;
import XmlWeb.dto.KorisnikDTO;
import XmlWeb.dto.RegisterDTO;
import XmlWeb.model.AgentRequest;
import XmlWeb.model.Korisnik;
import XmlWeb.model.Enums.Role;
import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.model.security.Authority;
import XmlWeb.model.security.AuthorityName;
import XmlWeb.repository.AgentRequestRepository;
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

	@Autowired
	private AgentRequestRepository agentRequestRepository;

	@Autowired
	private CSRService csrService;

	@Autowired
	private RegisterService regService;

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

		return korisnikRepo.findByUsernameIgnoreCase(username);
	}

	public Korisnik getKorisnik(Long id) {
		return korisnikRepo.findById(id).get();
	}

	public void updateKorisnik(KorisnikDTO k) {
		Konverter kon = new Konverter();
		Korisnik kor = kon.converterKorisnika(k, true);
		if (kor != null) {
			Korisnik id = korisnikRepo.findByUsernameIgnoreCase(kor.getUsername());

			if (id != null) {
				kor.setId(id.getId());
				if (id.getStatusNaloga() != null)
					kor.setStatusNaloga(id.getStatusNaloga());
				kor.setRezervacije(id.getRezervacije());
				kor.setIzdaje(id.getIzdaje());
				kor.setAktiviran(id.isAktiviran());
				kor.setRole(id.getRole());
				kor.setAuthorities(id.getAuthorities());
				kor.setLastPasswordResetDate(id.getLastPasswordResetDate());
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
		return korisnikRepo.findByEmailIgnoreCase(email);
	}

	public Korisnik findByConfirmationToken(String confirmationToken) {
		return korisnikRepo.findByConfirmationToken(confirmationToken);
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity<HashMap> registerKorisnik(HttpServletResponse response, RegisterDTO regDetails)
			throws URISyntaxException, InterruptedException, IOException, NoSuchAlgorithmException,
			NoSuchProviderException, OperatorCreationException {
		HashMap<String, String> map = new HashMap<>();
		Korisnik k = korisnikRepo.findByUsernameIgnoreCase(regDetails.getUsername());
		if (k != null) {
			map.put("text", "Username is already taken.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}

		k = korisnikRepo.findByEmailIgnoreCase(regDetails.getEmail());
		if (k != null) {
			map.put("text", "Email is already taken.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}

		if(regDetails.getUsername().trim().length()<4) {
			map.put("text", "Username is too short.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}
		if (!regDetails.getPassword1().equals(regDetails.getPassword2())) {
			map.put("text", "Passwords don't match.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}
		if(regDetails.getPassword1().trim().length()<8) {
			map.put("text", "Password is too short.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}

		
		  Zxcvbn passwordCheck = new Zxcvbn(); 
		 Strength strength =  passwordCheck.measure(regDetails.getPassword1()); if (strength.getScore() <  1) {
		  map.put("text","Your password is too weak. Please choose a stronger one.");
		  return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED); }
		 
		Korisnik novi = new Korisnik();
		novi.setAktiviran(false);
		List<Authority> l = new ArrayList<>();
		Authority a;
		if (regDetails.isAgent()) {
			a = autoRepo.findByName(AuthorityName.ROLE_AGENT);
			if (a != null)
				l.add(a);
			else {
				a = new Authority();
				a.setName(AuthorityName.ROLE_AGENT);
				a.setUsers(new ArrayList<>());
				autoRepo.save(a);
				l.add(a);
			}
		} else {
			a = autoRepo.findByName(AuthorityName.ROLE_USER);
			if (a != null)
				l.add(a);
			else {
				a = new Authority();
				a.setName(AuthorityName.ROLE_USER);
				a.setUsers(new ArrayList<>());
				autoRepo.save(a);
				l.add(a);
			}
		}

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
		novi.setPIB(regDetails.getPib());
		novi.setAdresa(regDetails.getAdresa());
		if (novi.getAuthorities().get(0).getName().toString().equals(AuthorityName.ROLE_AGENT.toString()))
			novi.setRole(Role.AGENT);
		else
			novi.setRole(Role.USER);
		novi.setConfirmationToken(UUID.randomUUID().toString());
		korisnikRepo.save(novi);
		a.getUsers().add(novi);
		autoRepo.save(a);
		if (regDetails.isAgent()) {
			AgentRequest ar = new AgentRequest();
			ar.setCsr(csrService.generatePem(novi));
			agentRequestRepository.save(ar);
			regService.sendToAdminModule(novi, ar);
		}

		String subject = "Registration Confirmation";
		String link = "Please go to following link to activate your account: https://localhost:8096/confirm/";
		String text = "To confirm your e-mail address, please click the link below:\n" + link
				+ novi.getConfirmationToken();
		String redirect = "1";
		if (novi.getRole().toString().equals("AGENT")) {
			System.out.println("AGENT!");
			subject = "Registration Details";
			text = "Your request is being processed by our administrators. Activation link will be sent to your email address after approval.";
			redirect = "3";
		}

		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(novi.getEmail());
		registrationEmail.setSubject(subject);
		registrationEmail.setText(text);
		registrationEmail.setFrom("noreply@domain.com");

		emailService.sendEmail(registrationEmail);
		// return new ResponseEntity<String>("Almost there! Please finish your
		// registration via link we sent on your email.", HttpStatus.OK);

		String location = "https://localhost:8096/#!/success/" + redirect;
		map.put("Location", location);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	public ResponseEntity<HashMap> confirmReg(HttpServletResponse response, String token) throws IOException {
		System.out.println("JEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEJ ♥");
		HashMap<String, String> map = new HashMap<>();
		Korisnik k = korisnikRepo.findByConfirmationToken(token);
		if (k == null) {
			map.put("text", "Bad token. Registration failed.");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

		k.setAktiviran(true);
		k.setStatusNaloga(StatusKorisnika.AKTIVAN);
		k.setConfirmationToken("");
		korisnikRepo.save(k);
		map.put("text", "Success! Your account is active now.");
		response.sendRedirect("https://localhost:8096/#!/success/2");
		return new ResponseEntity<>(map, HttpStatus.OK);

		// TODO Auto-generated method stub

	}

	public void saveKorisnik(Korisnik k) {
		// TODO Auto-generated method stub
		korisnikRepo.save(k);
	}

	public ResponseEntity<HashMap> registerKorisnikByAdmin(HttpServletResponse response, RegisterDTO regDetails) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<>();
		Korisnik k = korisnikRepo.findByUsernameIgnoreCase(regDetails.getUsername());
		if (k != null) {
			map.put("text", "Username is already taken.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}

		k = korisnikRepo.findByEmailIgnoreCase(regDetails.getEmail());
		if (k != null) {
			map.put("text", "Email is already taken.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}

		if (!regDetails.getPassword1().equals(regDetails.getPassword2())) {
			map.put("text", "Passwords don't match.");
			return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
		}

		/*
		 * Zxcvbn passwordCheck = new Zxcvbn(); Strength strength =
		 * passwordCheck.measure(regDetails.getPassword1()); if (strength.getScore() <
		 * 1) {
		 * map.put("text","Your password is too weak. Please choose a stronger one.");
		 * return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED); }
		 */
		Korisnik novi = new Korisnik();
		novi.setAktiviran(false);
		List<Authority> l = new ArrayList<>();
		Authority a;
		a = autoRepo.findByName(AuthorityName.ROLE_AGENT);
		if (a != null)
			l.add(a);
		else {
			a = new Authority();
			a.setName(AuthorityName.ROLE_AGENT);
			a.setUsers(new ArrayList<>());
			autoRepo.save(a);
			l.add(a);
		}
		novi.setAuthorities(l);
		novi.setEmail(regDetails.getEmail());
		novi.setFirstName(regDetails.getFirstname());
		novi.setLastName(regDetails.getLastname());
		novi.setPassword(bCryptPasswordEncoder.encode(regDetails.getPassword1()));
		novi.setIzdaje(null);
		novi.setRezervacije(null);
		novi.setStatusNaloga(StatusKorisnika.AKTIVAN);
		novi.setLastPasswordResetDate(new Date());
		novi.setUsername(regDetails.getUsername());
		novi.setPIB(regDetails.getPib());
		novi.setAktiviran(true);
		novi.setAdresa(regDetails.getAdresa());
		novi.setRole(Role.AGENT);
		novi.setConfirmationToken("");
		korisnikRepo.save(novi);
		a.getUsers().add(novi);
		autoRepo.save(a);
		String subject = "Registration Details";
		String text = "Your account on PigInc. BOOKING is created.\n" + "Username: " + novi.getUsername()
				+ " \n Password: " + regDetails.getPassword1() + "\n Visit us on https://localhost:8096";
		String redirect = "1";
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(novi.getEmail());
		registrationEmail.setSubject(subject);
		registrationEmail.setText(text);
		registrationEmail.setFrom("noreply@domain.com");

		emailService.sendEmail(registrationEmail);
		// return new ResponseEntity<String>("Almost there! Please finish your
		// registration via link we sent on your email.", HttpStatus.OK);

		String location = "https://localhost:8090/#!/success/" + redirect;
		map.put("Location", location);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	public void blockKorisnik(Long id) {
		// TODO Auto-generated method stub
		Korisnik k = korisnikRepo.findById(id).get();
		
		if (k.getStatusNaloga().toString().equals("BLOKIRAN")) {
			if (k.getConfirmationToken().equals("")) {
				k.setStatusNaloga(StatusKorisnika.AKTIVAN);
				k.setAktiviran(true);
			}

			else {
				k.setStatusNaloga(StatusKorisnika.NEPOTVRDJEN);
				k.setAktiviran(false);
			}

		} else {
			k.setStatusNaloga(StatusKorisnika.BLOKIRAN);
			k.setAktiviran(false);
		}

		korisnikRepo.save(k);
	}

}
