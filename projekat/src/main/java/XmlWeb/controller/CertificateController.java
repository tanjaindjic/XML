package XmlWeb.controller;

import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.model.Korisnik;
import XmlWeb.service.CertificateService;
import XmlWeb.service.KeyStoreService;
import XmlWeb.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;

@RestController
public class CertificateController {

    @Autowired
    private CertificateService cs;

    @Autowired
    private KeyStoreService kss;

    @Autowired
    private KorisnikService ks;

    @RequestMapping(value="/certificates/upload/{username}", method= RequestMethod.POST)
    public ResponseEntity<HashMap> uploadAndCreateSF(@RequestBody MultipartFile file, @PathVariable String username) throws IOException, CertificateException {
        HashMap<String, String> map = new HashMap<>();
        String savedFileName = null;
        System.out.println("usao u upload");
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

        if (!file.isEmpty()) {

            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);

            InputStream targetStream = new ByteArrayInputStream(completeData.getBytes());
            X509Certificate cert = (X509Certificate)certFactory.generateCertificate(targetStream);
            X509Certificate original = kss.getCertificate(username);

            String originalData = new String(cs.x509CertificateToPem(original));

            if (Arrays.equals(bytes, originalData.getBytes()))
            {
                System.out.println("Yup, they're the same!");
                Korisnik k = ks.getKorisnik(username);
                k.setStatusNaloga(StatusKorisnika.AKTIVAN);
                ks.saveKorisnik(k);
                String location = "https://localhost:8096/#!/success/" + 4;
                map.put("Location", location);
                return new ResponseEntity<>(map, HttpStatus.OK);

            }else {
                System.out.println("Oh noes:(");
                map.put("text", "Bad certificate.");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }



        }
    return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}
