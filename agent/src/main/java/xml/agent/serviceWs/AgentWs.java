package xml.agent.serviceWs;

import javax.jws.*;

import xml.agent.model.Smestaj;

@WebService(targetNamespace = "http://agent-xml/wsdl")
public interface AgentWs {
	@WebMethod
	public Smestaj addSmestaj(Smestaj s);
}
