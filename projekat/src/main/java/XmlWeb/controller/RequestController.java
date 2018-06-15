package XmlWeb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.model.AgentRequest;
import XmlWeb.repository.AgentRequestRepository;

@RestController
public class RequestController {
	
	@Autowired
	private AgentRequestRepository agentRequestRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/requests")
	public List<AgentRequest> getRequests() {
		
		ArrayList<AgentRequest> ret = new ArrayList<>();
		agentRequestRepository.findAll().forEach(ret::add);
		return ret;
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/requests/{id}")
	public void deleteReq(@PathVariable Long id){
		agentRequestRepository.deleteById(id);
	}


}
