package XmlWeb.repository;

import org.springframework.data.repository.CrudRepository;

import XmlWeb.model.security.Authority;
import XmlWeb.model.security.AuthorityName;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	Authority findByName(AuthorityName name);

}
