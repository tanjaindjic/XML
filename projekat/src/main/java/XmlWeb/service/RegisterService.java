package XmlWeb.service;

import java.io.IOException;
import java.net.ConnectException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import XmlWeb.model.AgentRequest;
import XmlWeb.model.Korisnik;

@Service
public class RegisterService {

	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("http://localhost:8096/register.html");
	}

	public void sendToAdminModule(Korisnik novi, AgentRequest ar) {

		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<AgentRequest> request = new HttpEntity<>(ar);
			ResponseEntity<AgentRequest> response = restTemplate.exchange("https://localhost:8090/requests",
					HttpMethod.POST, request, AgentRequest.class);

			RestTemplate restTemplate2 = new RestTemplate();
			HttpEntity<Korisnik> request2 = new HttpEntity<>(novi);
			ResponseEntity<Korisnik> response2 = restTemplate2.exchange("https://localhost:8090/user", HttpMethod.POST,
					request2, Korisnik.class);
		} catch (Exception ex) {
			System.out.println("Admin baza nije dostupna, zahtevi ce biti preuzeti po ukljucenju baze.");
		}

	}

}
