package XmlWeb.controller;

import java.awt.*;
import java.util.List;

import XmlWeb.config.Read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import XmlWeb.dto.AgentRequestDTO;
import XmlWeb.model.AgentRequest;
import XmlWeb.model.Korisnik;
import XmlWeb.service.AgentRequestService;
import XmlWeb.service.AuthorityService;
import XmlWeb.service.KorisnikService;

import javax.ws.rs.core.MediaType;

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
	@Read
	@CrossOrigin(origins = "https://localhost:8090")
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/requests/{reqId}/user/{userId}", produces  = MediaType.TEXT_PLAIN)
	public void approveReq(@RequestBody String crt, @PathVariable Long reqId, @PathVariable Long userId ) {
		//try {
			System.out.println("primio crt:");
			System.out.println(crt);
			agentReqService.approveRequest(  crt , reqId,  userId);
			
		//}catch(Exception ex) {
		//	System.out.println("Opet org.hibernate.HibernateException: Unable to access lob stream i IO Exception: \"Missing lob entry");
		//}
	}
	
	


}
