package XmlWeb.controller;

import XmlWeb.dto.PorukaDTO;
import XmlWeb.model.Korisnik;
import XmlWeb.model.Poruka;
import XmlWeb.service.PorukaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PorukaController {

    @Autowired
    private PorukaService ps;

    @RequestMapping(method = RequestMethod.GET, value = "/messages/getAll")
    public List<Poruka> getAll(){
       return ps.getAllPoruke();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/messages/send")
    public void sendMessage(@RequestBody PorukaDTO p){
        ps.sendPoruka(p);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages/inbox/{id}")
    public List<Korisnik> getInbox(@PathVariable Long id){
        return ps.getInbox(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages/inbox/{id}/chat/{id2}")
    public List<Poruka> getChat(@PathVariable Long id, @PathVariable Long id2 ){
        return ps.getChat(id,id2);
    }


}
