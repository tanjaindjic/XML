package xml.agent.services;


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

import xml.agent.dodatno.Konverter;
import xml.agent.dto.KorisnikDTO;
import xml.agent.dto.RegisterDTO;
import xml.agent.model.AgentRequest;
import xml.agent.model.Korisnik;
import xml.agent.model.Enums.Role;
import xml.agent.model.Enums.StatusKorisnika;
import xml.agent.model.security.Authority;
import xml.agent.model.security.AuthorityName;
import xml.agent.repository.AuthorityRepository;
import xml.agent.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepo;


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

	public Korisnik findByUsername(String username) {
		return korisnikRepo.findByUsernameIgnoreCase(username);
	}

}
