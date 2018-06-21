package xml.agent;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.rpc.ServiceException;

//import javax.xml.rpc.ServiceException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import xml.agent.model.Smestaj;
import xml.agent.repository.SmestajRepository;
import xmlWeb_smestaj.wsdl.SmestajService;
import xmlWeb_smestaj.wsdl.SmestajServiceLocator;
import xmlWeb_smestaj.wsdl.SmestajSoap;

@SpringBootApplication
@EnableAsync
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
	//	Endpoint.publish("http://localhost:4789/main/java/xml/agent/soapServices/agent", new Agent());

		SmestajService smestajService = new SmestajServiceLocator();
		try {
			SmestajSoap smestajSoap = smestajService.getSmestajPort();
			SmestajRepository smestajRepo = null;
			try {
				Object o = smestajSoap.getSmestajs();
				ArrayList<Smestaj> s0 = new ArrayList<Smestaj>();
				/*for(xmlWeb_smestaj.wsdl.Smestaj ss : s){
					s0.add(Smestaj.convert(ss));
				}*/
				smestajRepo.saveAll(s0);
				System.out.println("radiiiii");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
