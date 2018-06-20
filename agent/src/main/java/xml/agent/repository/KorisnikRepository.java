package xml.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xml.agent.model.Korisnik;

public interface KorisnikRepository  extends JpaRepository<Korisnik, Long> {

	Korisnik findByUsernameIgnoreCase(String username);

}
