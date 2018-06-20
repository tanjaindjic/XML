package xml.agent.serviceWs;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import xml.agent.model.Smestaj;

@WebService(targetNamespace = "http://agent-xml/wsdl")
public interface AgentWs {
	@WebMethod
	public List<Smestaj> getSmestajs();
}
