package XmlWeb.controller;

import org.springframework.web.bind.annotation.RestController;
//ne koristi se, radimo preko clouda
@Deprecated
@RestController
public class KomentarController {

  /*  @Autowired
    private KomentarService ks;

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

    @RequestMapping(method = RequestMethod.DELETE, value = "/comments/{id}")
    public void deleteOne(@PathVariable Long id) {
         ks.deleteKomentar(id);
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
    public List<Komentar>  getCommentsSoba(@PathVariable Long id) {
        return ks.getKomentariBySoba(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comments/unpublished")
    public List<Komentar> getUnpublished(){
        return ks.getUnpublished();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/comments/publish/{id}")
    public void publish(@PathVariable Long id){
        ks.publishComment(id);
    }
*/



}
