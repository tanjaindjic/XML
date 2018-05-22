package XmlWeb.repository;

import XmlWeb.model.Enums.StatusRezevacije;
import XmlWeb.model.Rezervacija;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {
    List<Rezervacija> findByRezervisaoId(Long id);
    List<Rezervacija> findByRezervisaoIdAndStatus(Long osoba, StatusRezevacije status);
    List<Rezervacija> findByRezervisaoIdAndSobaId(Long osoba, List soba);
    List<Rezervacija> findBySobaId(Long id);
}
