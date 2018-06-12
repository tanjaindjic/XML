package XmlWeb.repository;

import java.util.Optional;

import XmlWeb.model.Korisnik;
import org.springframework.data.repository.CrudRepository;


public interface KorisnikRepository extends CrudRepository<Korisnik, Long>{
	Korisnik findByUsername(String username);
	Optional<Korisnik> findById(Long id);
	Korisnik findByEmail(String email);
	Korisnik findByConfirmationToken(String confirmationToken);

}
