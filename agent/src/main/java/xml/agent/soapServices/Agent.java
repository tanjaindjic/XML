package xml.agent.soapServices;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import xml.agent.model.Smestaj;
import xml.agent.repository.SmestajRepository;
import xml.agent.serviceWs.AgentWs;

@WebService(
        portName = "AdminPort",
        serviceName = "AdminService",
        targetNamespace = "http://agent-xml/wsdl",
        endpointInterface = "org.xml.agent.ws.AgentWs")
public class Agent implements AgentWs{
	@Autowired
    private SmestajRepository smestajRepo;
	public Smestaj addSmestaj(Smestaj s) {
		
		return s;
	}

}
