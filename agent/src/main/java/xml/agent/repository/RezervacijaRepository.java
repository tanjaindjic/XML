package xml.agent.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import xml.agent.model.Rezervacija;
import xml.agent.model.Enums.StatusRezevacije;

public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {
    List<Rezervacija> findByRezervisaoId(Long id);
    List<Rezervacija> findByRezervisaoIdAndStatus(Long osoba, StatusRezevacije status);
    List<Rezervacija> findByRezervisaoIdAndSobaId(Long osoba, List soba);
    List<Rezervacija> findBySobaId(Long id);
    Iterable<Rezervacija> findByStatus(StatusRezevacije pending);
}
