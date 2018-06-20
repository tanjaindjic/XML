package XmlWeb.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import XmlWeb.config.Read;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import XmlWeb.dto.AgentRequestDTO;
import XmlWeb.model.AgentRequest;
import XmlWeb.model.Korisnik;
import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.repository.AgentRequestRepository;
import XmlWeb.repository.KorisnikRepository;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;


@Service
public class AgentRequestService {
	
	@Autowired
	private AgentRequestRepository agentRequestRepository;
		
	@Autowired
	private CSRService csrService;
	
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	@Autowired
	private AgentRequestService agentReqService;
	
	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private EmailService emailService;
	
	
	public void populateRepository(List<AgentRequest> allRequests) {
		for (AgentRequest agentRequest : allRequests) {
			agentRequestRepository.save(agentRequest);
		}
	}
	
	public void addRequest(AgentRequest req) {
		System.out.println("sacuvao novi req");
		agentRequestRepository.save(req);
	}

	@Read
	public List<AgentRequestDTO> makeDTORequests(){
		ArrayList<AgentRequestDTO> list = new ArrayList<>();
		for (AgentRequest ar : agentRequestRepository.findAll()) {
			AgentRequestDTO dto = createDTO(ar);
			list.add(dto);
		}
		return list;
	}
	

	
	public AgentRequestDTO createDTO(AgentRequest req) {
		AgentRequestDTO dto = new AgentRequestDTO();
		InputStream csrStream = new ByteArrayInputStream(req.getCsr().getBytes());
		String username = csrService.readCertificateSigningRequest(csrStream);
		
		Korisnik k = korisnikRepo.findByUsernameIgnoreCase(username);
		if(k!=null) {
			dto.setKorisnikId(k.getId());
			dto.setFirstName(k.getFirstName());
			dto.setLastName(k.getLastName());
			dto.setEmail(k.getEmail());
			dto.setUsername(username);
			dto.setPassword(k.getPassword());
			dto.setLastPasswordResetDate(k.getLastPasswordResetDate());
			dto.setEnabled(k.isAktiviran());
			dto.setStatusNaloga(k.getStatusNaloga());
			dto.setAuthorities(k.getAuthorities());
			dto.setCsr(req.getCsr());
			dto.setAdresa(k.getAdresa());
			dto.setPIB(k.getPIB());
			dto.setCsrId(req.getId());
		}
		return dto;
	}

	public void deleteRequest(Long id) {
		
	
		agentRequestRepository.deleteById(id);
		
	}


	public List<AgentRequest> getAllRequests() {
		// TODO Auto-generated method stub

		ArrayList<AgentRequest> ret = new ArrayList<>();
		
		agentRequestRepository.findAll().forEach(ret::add);
		
		return ret;
	}

	public void approveRequest(String crt, Long reqId, Long userId) {
		System.out.println("dobijeni crt:");
		System.out.println(crt);
		// TODO Auto-generated method stub
		Korisnik k = korisnikRepo.findById(userId).get();
		k.setAktiviran(true);
		k.setConfirmationToken("");
		k.setStatusNaloga(StatusKorisnika.AKTIVAN);
		korisnikRepo.save(k);
		String subject = "Account Activation";
		String text = "Your registration request was approved and your account is active now. Visit us on: https://localhost:8096";

		EmailAttachment attachment = new EmailAttachment();
		try {
			FileUtils.writeStringToFile(new File("resources/PigIncCertificate.crt"), crt);
		} catch (IOException e) {
			e.printStackTrace();
		}
		attachment.setPath("resources/PigIncCertificate.crt");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Certificate for " + k.getFirstName() + " " + k.getLastName());
		attachment.setName("PigIncCertificate");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		try {
			email.setAuthentication("pig.inc.ns@gmail.com","tanjaindjic");
			email.setSmtpPort(587);
			email.setStartTLSRequired(true);
			email.addTo(k.getEmail(), k.getFirstName() + " " + k.getLastName());
			email.setFrom("noreply@domain.com", "Pig Inc BOOKING");
			email.setSubject(subject);
			email.setMsg(text);
			email.attach(attachment);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}



/*

		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(k.getEmail());
		registrationEmail.setSubject(subject);
		registrationEmail.setText(text);
		registrationEmail.setFrom("noreply@domain.com");

		registrationEmail.
		emailService.sendEmail(registrationEmail);
		*/
		agentReqService.deleteRequest(reqId);
	}
	
}
