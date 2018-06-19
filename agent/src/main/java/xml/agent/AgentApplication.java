package xml.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import javax.xml.ws.*;

import xml.agent.soapServices.Agent;

@SpringBootApplication
@EnableAsync
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
	//	Endpoint.publish("http://localhost:4789/main/java/xml/agent/soapServices/agent", new Agent());
	}
}
