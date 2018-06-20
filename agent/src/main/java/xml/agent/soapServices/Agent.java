package xml.agent.soapServices;

import java.util.List;

import javax.jws.*;

import org.springframework.beans.factory.annotation.Autowired;

import xml.agent.model.Smestaj;
import xml.agent.repository.SmestajRepository;
import xml.agent.serviceWs.AgentWs;

@WebService(
        portName = "AdminPort",
        serviceName = "AdminService",
        targetNamespace = "http://agent-xml/wsdl",
        endpointInterface = "xml.agent.serviceWs.AgentWs")
public class Agent implements AgentWs{
	@Autowired
    private SmestajRepository smestajRepo;
	
	@WebMethod
	public Smestaj addSmestaj(Smestaj s) {
		smestajRepo.save(s);
		return s;
	}

	@Override
	public List<Smestaj> getSmestajs() {
		// TODO Auto-generated method stub
		return null;
	}

}
