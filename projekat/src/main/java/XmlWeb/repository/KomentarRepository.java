package XmlWeb.repository;

import XmlWeb.model.Komentar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KomentarRepository extends CrudRepository<Komentar, Long> {
    List<Komentar> findByRezervacijaSobaId(Long id);
    List<Komentar> findByRezervacijaRezervisao(Long id);
    List<Komentar> findByRezervacijaRezervisaoIdAndRezervacijaSobaId(Long osoba, Long smestaj);
    List<Komentar> findByOdobreno(Boolean b);

}
