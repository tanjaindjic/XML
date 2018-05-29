package XmlWeb.controller;


import XmlWeb.dto.RezStatusUpdateDTO;
import XmlWeb.dto.RezervacijaDTO;
import XmlWeb.model.Rezervacija;
import XmlWeb.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RezervacijaController {

    @Autowired
    RezervacijaService rs;

    @RequestMapping(method = RequestMethod.GET, value = "/reservation/getAll")
    public List<Rezervacija> getAll(){
        return rs.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reservation/{id}")
    public List<Rezervacija> getByUser(@PathVariable Long id){ return rs.getRezervacije(id); }

    @RequestMapping(method = RequestMethod.POST, value = "/reservation/make")
    public boolean reserve(@RequestBody RezervacijaDTO r){
        return rs.rezervisi(r);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/reservation/update")
    public void update(@RequestBody RezStatusUpdateDTO rsud){
        rs.updateStatus(rsud);
    }


}
