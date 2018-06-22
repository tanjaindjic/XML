package XmlWeb.controller;

import XmlWeb.config.*;
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

    @UserRead
    @RequestMapping(method = RequestMethod.GET, value = "/messages/getAll")
    public List<Poruka> getAll(){
       return ps.getAllPoruke();
    }

    @UserWrite
    @RequestMapping(method = RequestMethod.POST, value = "/messages/send")
    public void sendMessage(@RequestBody PorukaDTO p){
        System.out.println("UAPO JE NA BACKEND :D");
        ps.sendPoruka(p);
    }

    @UserRead
    @RequestMapping(method = RequestMethod.GET, value = "/messages/inbox/{id}")
    public List<Korisnik> getInbox(@PathVariable Long id){ return ps.getInbox(id);}

    @UserRead
    @RequestMapping(method = RequestMethod.GET, value = "/messages/inbox/{id}/chat/{id2}")
    public List<Poruka> getChat(@PathVariable Long id, @PathVariable Long id2 ){ return ps.getChat(id,id2); }


}
