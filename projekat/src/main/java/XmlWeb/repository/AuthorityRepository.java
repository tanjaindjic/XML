package XmlWeb.repository;

import org.springframework.data.repository.CrudRepository;

import XmlWeb.model.security.Authority;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {

}
