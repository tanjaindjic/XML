package xml.agent.serviceWs;

import javax.jws.WebService;

import xml.agent.model.Smestaj;

@WebService(targetNamespace = "http://superbiz.org/wsdl")
public interface AgentWs {
	public Smestaj addSmestaj(Smestaj s);
}
