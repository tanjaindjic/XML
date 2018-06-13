package XmlWeb.repository;

import org.springframework.data.repository.CrudRepository;

import XmlWeb.model.AgentRequest;

public interface AgentRequestRepository extends CrudRepository<AgentRequest, Long> {
	AgentRequest findByKorisnikId(Long id);
	AgentRequest findByKorisnikEmailIgnoreCase(String email);
	AgentRequest findByKorisnikUsernameIgnoreCase(String username);

}
