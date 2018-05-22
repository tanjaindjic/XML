package XmlWeb.controller;

import XmlWeb.dto.KomentarDTO;
import XmlWeb.model.Komentar;
import XmlWeb.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KomentarController {

    @Autowired
    KomentarService ks;

    @RequestMapping(method = RequestMethod.GET, value = "/comments/getAll")
    public List<Komentar> getAll() {
        return ks.getAllKomentari();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/comments/add")
    public void addComment(@RequestBody KomentarDTO k){
        ks.addKomentar(k);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/comments/update/{id}")
    public void update(@RequestBody boolean b, @PathVariable Long id){
        ks.promeniStatus(id, b);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comments/{id}")
    public Komentar getOne(@PathVariable Long id) {
        return ks.getKomentar(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/{id}/comments")
    public List<Komentar>  getCommentsPerson(@PathVariable Long id) {
        return ks.getKomentariByOsoba(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "accommodation/{id}/comments")
    public List<Komentar>  getCommentsSmestaj(@PathVariable Long id) {
        return ks.getKomentariBySmestaj(id);
    }




}
