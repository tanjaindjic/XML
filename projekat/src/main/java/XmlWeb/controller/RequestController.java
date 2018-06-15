package XmlWeb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.dto.AgentRequestDTO;
import XmlWeb.model.AgentRequest;
import XmlWeb.model.Korisnik;
import XmlWeb.service.AgentRequestService;
import XmlWeb.service.AuthorityService;
import XmlWeb.service.KorisnikService;

@RestController
public class RequestController {
	
	@Autowired
	private AuthorityService authService;
	
	@Autowired
	private AgentRequestService agentReqService;
	
	@Autowired
	private KorisnikService korisnikService;
	

	@RequestMapping(method = RequestMethod.GET, value = "/requests")
	public List<AgentRequest> getRequests() {
		
		return agentReqService.getAllRequests();
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/dtorequests")
	public List<AgentRequestDTO> getDTORequests() {
		
		return agentReqService.makeDTORequests();
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/requests/{reqId}/user/{userId}")
	public void deleteReq(@PathVariable Long reqId, @PathVariable Long userId){
		try {
			Korisnik k = korisnikService.getKorisnik(userId);
			
			authService.removeUser(k.getAuthorities().get(0).getId(), userId);
			korisnikService.deleteKorisnik(userId);
			agentReqService.deleteRequest(reqId);
		}catch(Exception ex) {
			System.out.println("Opet org.hibernate.HibernateException: Unable to access lob stream i IO Exception: \"Missing lob entry");
		}
	}
	
	


}
