package XmlWeb.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import XmlWeb.dto.AgentRequestDTO;
import XmlWeb.model.AgentRequest;
import XmlWeb.model.Korisnik;
import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.repository.AgentRequestRepository;
import XmlWeb.repository.KorisnikRepository;


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

	public void approveRequest(Long reqId, Long userId) {
		// TODO Auto-generated method stub
		Korisnik k = korisnikRepo.findById(userId).get();
		k.setAktiviran(true);
		k.setConfirmationToken("");
		k.setStatusNaloga(StatusKorisnika.AKTIVAN);
		korisnikRepo.save(k);
		String subject = "Account Activation";
		String text = "Your registration request was approved and your account is active now. Visit us on: https://localhost:8096";
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(k.getEmail());
		registrationEmail.setSubject(subject);
		registrationEmail.setText(text);
		registrationEmail.setFrom("noreply@domain.com");

		emailService.sendEmail(registrationEmail);
		
		agentReqService.deleteRequest(reqId);
	}
	
}
