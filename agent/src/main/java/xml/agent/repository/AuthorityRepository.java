package xml.agent.repository;


import org.springframework.data.repository.CrudRepository;

import xml.agent.model.security.Authority;
import xml.agent.model.security.AuthorityName;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	Authority findByName(AuthorityName name);

}
