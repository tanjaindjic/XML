package XmlWeb.service;

import java.io.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import XmlWeb.config.Read;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.bouncycastle.util.io.pem.PemReader;
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

	@Autowired
	private KeyStoreService kss;
	
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
		// TODO Auto-generated method stub
		Korisnik k = korisnikRepo.findById(userId).get();
		k.setAktiviran(true);
		k.setConfirmationToken("");
		//k.setStatusNaloga(StatusKorisnika.AKTIVAN); OVO KAD UPLOADUJE PRVI PUT SVOJ SERTIFIKAT I UPOREDIMO SA NASOM VERZIJOM ;)
		korisnikRepo.save(k);

		kss.loadKeyStore(1);
		try {
			System.out.println("pre cuvanja fff cert: " + kss.getCertificates().size());
			kss.saveAgentCert(convertToX509Certificate(crt), k.getUsername());
			System.out.println("posle cuvanja fff cert: " + kss.getCertificates().size());
			System.out.println(kss.getCertificate(k.getUsername()));
			System.out.println(kss.getCertificate("admin"));
			kss.loadKeyStore(0);
			kss.loadKeyStore(1);
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileUtils.writeStringToFile(new File("PigIncCertificate.crt"), crt);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String subject = "Account Activation";
		String text = "Your registration request was approved and your account is active now. Visit us on: https://localhost:8096";

		EmailAttachment attachment = new EmailAttachment();

		attachment.setPath("PigIncCertificate.crt");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Certificate for " + k.getFirstName() + " " + k.getLastName());
		attachment.setName("PigIncCertificate");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		try {
			//email.setAuthentication("pig.inc.ns@gmail.com","tanjaindjic");
			email.setAuthentication("xmlbesp@gmail.com","Operisedolje!");
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

		agentReqService.deleteRequest(reqId);

	}

	public X509Certificate convertToX509Certificate(String pem) throws CertificateException, IOException {
		InputStream in = null;
		X509Certificate cert = null;
		try {
			byte[] certEntryBytes = pem.getBytes();
			in = new ByteArrayInputStream(certEntryBytes);
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

			cert = (X509Certificate) certFactory.generateCertificate(in);
		} catch (CertificateException ex) {

		} finally {
			if (in != null) {
				in.close();
			}
		}
		return cert;
	}
	
}
