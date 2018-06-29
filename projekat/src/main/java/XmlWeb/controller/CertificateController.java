package XmlWeb.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import XmlWeb.config.AdminRead;
import XmlWeb.config.AdminWrite;
import XmlWeb.config.AgentWrite;
import XmlWeb.model.Korisnik;
import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.security.CertificateDTO;
import XmlWeb.service.CertificateService;
import XmlWeb.service.KeyStoreService;
import XmlWeb.service.KorisnikService;

import com.sun.research.ws.wadl.Resource;

@RestController
public class CertificateController {

    @Autowired
    private CertificateService cs;

    @Autowired
    private KeyStoreService kss;

    @Autowired
    private KorisnikService ks;

    @AdminRead
    @RequestMapping(value="/certificates", method= RequestMethod.GET)
    public List<CertificateDTO> getCertificates(){
            System.out.println(kss.getCertificatesDTO().size());
        return kss.getCertificatesDTO();
    }

    @AdminRead
    @RequestMapping(value="/certificates/type/admin", method= RequestMethod.GET)
    public List<CertificateDTO> getAdminCertificates(){
        List<CertificateDTO> dtos =  kss.getAdminCertificatesDTO();
        return dtos;
    }

    @AdminRead
    @RequestMapping(value = "/certificates/check/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    public ResponseEntity<String> checkCertificate(@PathVariable String id) {
        String respond = cs.check(id);
        return new ResponseEntity<>(respond, HttpStatus.OK);
    }

    @AdminWrite
    @RequestMapping(value = "/certificates/revokeCert/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    public ResponseEntity<String> revoke(@PathVariable String id) {
        String respond = "failed";
        if(cs.revoke(id)){
            respond = "good";
        }

        return new ResponseEntity<>(respond, HttpStatus.OK);
    }
    @AdminRead
    @RequestMapping(value = "/certificates/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM)
    public ResponseEntity<FileOutputStream> download(@PathVariable String id) {
        String crt = cs.download(id);
        String filename = "certificate_"+id;
        byte[] b = crt.getBytes();
        FileOutputStream out;
		try {
			out = new FileOutputStream("certificate_download.crt");
	        try {
				out.write(b);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				out.close();
			//	System.out.println("DOWNLOAD: uspeo da dodje do statusa ok");
		        return new ResponseEntity<>(out, HttpStatus.OK);              // .build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @AdminWrite
    @AgentWrite
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
