package XmlWeb.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.model.Korisnik;
import XmlWeb.security.CertificateDTO;
import XmlWeb.security.CertificateGenerator;
import XmlWeb.security.IssuerData;
import XmlWeb.security.SubjectData;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.PEMWriter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class    CertificateService {
    @Autowired
    private KeyStoreService keyStoreService;

    private KeyPair keyPair;

    @Autowired
    private KorisnikService korisnikService;


    public List<CertificateDTO> convertToDTO(List<X509Certificate> certificates) {
        ArrayList<CertificateDTO> certificateDTOS = new ArrayList<>();
        for (X509Certificate cert : certificates) {
            certificateDTOS.add(new CertificateDTO(cert));
        }
        return certificateDTOS;
    }

    //OVDE SE PRAVI SAMO PRVI SERTIFIKAT ZA ADMIRA SVE OSTALE ON PRAVI U ADMIN APLIKACIJI, SALJE AN MEJL I U GLAVNU BAZU
    public X509Certificate generateCertificate(CertificateDTO certificateDTO) {
        // Serijski broj sertifikata
        int randomNum = 0 + (int) (Math.random() * 10000000);
        String sn = String.valueOf(randomNum);
        System.out.println("admin s num: " + sn);
        certificateDTO.setSerialNumber(sn);
        System.out.println("provera issuera");
        System.out.println("issuer serial num: " + certificateDTO.getIssuerSerialNumber());
        keyPair = generateKeyPair();
        SubjectData sd = newSubjectData(certificateDTO);
        IssuerData id = newIssuerData(certificateDTO);

        System.out.println("Napravljeni sub i issuer data ,cuvam cert");
        CertificateGenerator generator = new CertificateGenerator();
        X509Certificate certificate = null;
        try {
            certificate = generator.generateCertificate(sd, id, certificateDTO.getisCa(), certificateDTO.getIssuerSerialNumber());
        } catch (CertIOException e) {
            e.printStackTrace();
        }
        keyStoreService.writeCertificate(certificateDTO.getisCa(), certificate, certificateDTO.getCommonName(), sd.getPrivateKey());

        return certificate;
    }


    public SubjectData newSubjectData(CertificateDTO certificate) {

        //Datumi od kad do kad vazi sertifikat
        Date startDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, 10);
        Date endDate = c.getTime();


        //Serijski broj sertifikata
        String sn = certificate.getSerialNumber();
        //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        
		 builder.addRDN(BCStyle.UNIQUE_IDENTIFIER, certificate.getId());
		 builder.addRDN(BCStyle.CN, certificate.getCommonName());
		 builder.addRDN(BCStyle.E, certificate.getEmail());
		 builder.addRDN(BCStyle.SURNAME, certificate.getSurname());
		 builder.addRDN(BCStyle.GIVENNAME, certificate.getGivenName());
		 builder.addRDN(BCStyle.POSTAL_ADDRESS, certificate.getAdresa());
		 builder.addRDN(BCStyle.SN, certificate.getPIB());
		 builder.addRDN(BCStyle.O, certificate.getOrgName());
		 builder.addRDN(BCStyle.OU, certificate.getOrgNameUnit());
		 builder.addRDN(BCStyle.C, certificate.getCountry());            
		 builder.addRDN(BCStyle.UID, certificate.getSerialNumber());
		    
        //UID (USER ID) je ID korisnika
        builder.addRDN(BCStyle.UID, sn);

        //podaci o sertifikatu  javni kljuc, podaci o vlasniku, serijski broj, od kad do kad vazi
        return new SubjectData(keyPair.getPublic(), keyPair.getPrivate(), builder.build(), sn, startDate, endDate);

    }

    public IssuerData newIssuerData(CertificateDTO certificate) {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);

        String isn = certificate.getIssuerSerialNumber();
        if ((isn=="") || isn == null) { //ako uzmemo u obzir da ce biti prazan string ako zeli issuera da doda
        	System.out.println("nema issuer serial num");
        	 builder.addRDN(BCStyle.UNIQUE_IDENTIFIER, certificate.getId());
    	   	 builder.addRDN(BCStyle.CN, certificate.getCommonName());
    	   	 builder.addRDN(BCStyle.E, certificate.getEmail());
    	   	 builder.addRDN(BCStyle.SURNAME, certificate.getSurname());
    	   	 builder.addRDN(BCStyle.GIVENNAME, certificate.getGivenName());
    	   	 builder.addRDN(BCStyle.POSTAL_ADDRESS, certificate.getAdresa());
    	   	 builder.addRDN(BCStyle.SN, certificate.getPIB());
    	   	 builder.addRDN(BCStyle.O, certificate.getOrgName());
    	   	 builder.addRDN(BCStyle.OU, certificate.getOrgNameUnit());
    	   	 builder.addRDN(BCStyle.C, certificate.getCountry());            
    	   	 builder.addRDN(BCStyle.UID, certificate.getSerialNumber());

            return new IssuerData(keyPair.getPrivate(), builder.build());
        } else {
            IssuerData id = keyStoreService.readIssuerFromStore("admin");
            return id;
        }

    }

    
    public String check(String id) {

        List<X509Certificate> revoked = readRevoked();
        for (X509Certificate cert : revoked) {
            if (cert.getSerialNumber().toString().equals(id)) {
                return "revoked";
            }
        }
        boolean found = false;
        for (X509Certificate certificate:keyStoreService.getCertificates()) {
            if(certificate.getSerialNumber().toString().equals(id))
                found = true;
        }

        if (!found) {
            return "undefined";
        }

        return "good";
    }

    
    public String download(String id) {
    	List<X509Certificate> certs = keyStoreService.getCertificates();
    	X509Certificate cert = null;
    	for (X509Certificate c0 : certs) {
            if (c0.getSerialNumber().toString().equals(id)) {
                cert=c0;
            }
        }
   //     System.out.println("DOWNLOAD: id "+id);
        if (cert == null) {
       //     System.out.println("DOWNLOAD:  vraca null");
            return null;
        }
     //   System.out.println("DOWNLOAD:  nije null");
        StringWriter sw = new StringWriter();

        try {
       //     System.out.println("DOWNLOAD:  vrsi konverziju");
            sw.write(DatatypeConverter.printBase64Binary(cert.getEncoded()).replaceAll("(.{64})", "$1\n"));
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("DOWNLOAD:  povratna vrednost :" + sw.toString());
        return sw.toString();
    }


    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean revoke(String id) {
        System.out.println("usao u revoke");
        X509Certificate certificate = null;

        for (X509Certificate c :keyStoreService.getCertificates()) {
            if (c.getSerialNumber().toString().equals(id)) {
                certificate = c;
                break;
            }
        }
        if(certificate==null){
            System.out.println("Nije nasao cert za revoke :(");
            return false;
        }

        if ( certificate.getBasicConstraints()!=-1) {
            System.out.println("ne moze revoke admina");
            return false;
        }
        X500Name x500name = null;
        try {
            x500name = new JcaX509CertificateHolder(certificate).getSubject();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
        RDN cn = x500name.getRDNs(BCStyle.CN)[0];
        String username = IETFUtils.valueToString(cn.getFirst().getValue());
        Korisnik k = korisnikService.getKorisnik(username);
        k.setStatusNaloga(StatusKorisnika.NEPOTVRDJEN);
        korisnikService.saveKorisnik(k);
        generateCertFromUser(k);
        List<X509Certificate> certificates = new ArrayList<>();

        try {
            File file = new File("revocation.crl");

            if (!file.exists()) {
                saveCRL(certificates, file);
            } else {
                ObjectInputStream iis = new ObjectInputStream(new FileInputStream(file));
                certificates = (List<X509Certificate>) iis.readObject();
                iis.close();
            }

            for (X509Certificate cert : certificates) {
                if (cert.getSerialNumber().equals(certificate.getSerialNumber())) {
                    return true;
                }
            }

            String issuerSN = new CertificateDTO(certificate).getSerialNumber();
            List<X509Certificate> allCertificates = keyStoreService.getCertificates();

            List<X509Certificate> revokeList = new ArrayList<>();
            for (X509Certificate cert : allCertificates) {
                if (new CertificateDTO(cert).getIssuerSerialNumber().equals(issuerSN)) {
                    revokeList.add(cert);
                }
            }

            allCertificates.removeAll(revokeList);
            revokeRecursion(certificates, revokeList, allCertificates);
            certificates.add(certificate);
            certificates.addAll(revokeList);
            keyStoreService.deleteList(certificates);
            saveCRL(certificates, file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveCRL(List<X509Certificate> certificates, File file) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(certificates);
        oos.flush();
        oos.close();
    }

    public void revokeRecursion(List<X509Certificate> certificates, List<X509Certificate> revokeList, List<X509Certificate> allCertificates) {
        List<X509Certificate> childRevokeList = new ArrayList<>();
        for (X509Certificate cert : revokeList) {
            CertificateDTO certDTO = new CertificateDTO(cert);
            for (X509Certificate cert1 : allCertificates) {
                if (new CertificateDTO(cert1).getIssuerSerialNumber().equals(certDTO.getSerialNumber())) {
                    childRevokeList.add(cert1);
                }
            }
        }
        if (childRevokeList.size() == 0) {
            return;
        }
        certificates.addAll(childRevokeList);
        allCertificates.removeAll(childRevokeList);
        revokeRecursion(certificates, childRevokeList, allCertificates);
    }


    
    public List<X509Certificate> readRevoked() {
        List<X509Certificate> certificates = new ArrayList<>();
        File file = new File("revocation.crl");

        if (!file.exists()) {
            try {
                saveCRL(certificates, file);
                return certificates;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            ObjectInputStream iis = null;
        try {
            iis = new ObjectInputStream(new FileInputStream(file));
            certificates = (List<X509Certificate>) iis.readObject();
            iis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return certificates;
    }

    public String generatePem(String csr) throws NoSuchAlgorithmException, NoSuchProviderException, OperatorCreationException, IOException {
		
		 StringWriter sw = new StringWriter();
		    try (JcaPEMWriter pw = new JcaPEMWriter(sw)) {
		        pw.writeObject(csr);
		        pw.flush();
		    }
		    System.out.println(sw.toString());
		    return sw.toString();
		
	}

	public void sendAdminCerts(String token){
        try {
            RestTemplate restTemplate = new RestTemplate();
            List<CertificateDTO> certs = keyStoreService.getAdminCertificatesDTO();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Authorization", token);
            HttpEntity<Object> requestEntity = new HttpEntity<Object>(certs,headers);
            ResponseEntity<List<CertificateDTO>> rateResponse = restTemplate.exchange("https://localhost:8090/getAdminCerts",
                    HttpMethod.POST, requestEntity,new ParameterizedTypeReference<List<CertificateDTO>>() {});


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String getX500Field(ASN1ObjectIdentifier asn1ObjectIdentifier, X500Name x500Name) {
	    RDN[] rdnArray = x500Name.getRDNs(asn1ObjectIdentifier);

	    String retVal = null;
	    for (RDN item : rdnArray) {
	        retVal = item.getFirst().getValue().toString();
	    }
	    return retVal;
	}
	
	public String generateCSR(String csr, Long reqId, Long userId) {
		// TODO Auto-generated method stub
		
		InputStream targetStream = new ByteArrayInputStream(csr.getBytes());
		PKCS10CertificationRequest pkcsr = convertPemToPKCS10CertificationRequest(targetStream);
	    CertificateDTO dto = new CertificateDTO();

	    if (pkcsr == null) {
	        System.out.println("FAIL! conversion of Pem To PKCS10 Certification Request");
	    } else {
	    		    	
	       X500Name x500Name = pkcsr.getSubject();
	       dto.setId(getX500Field(BCStyle.UNIQUE_IDENTIFIER, x500Name));
	       dto.setCommonName(getX500Field(BCStyle.CN, x500Name));
	       dto.setEmail(getX500Field(BCStyle.E, x500Name));
	       dto.setSurname(getX500Field(BCStyle.SURNAME, x500Name));
	       dto.setGivenName(getX500Field(BCStyle.GIVENNAME, x500Name));
	       dto.setAdresa(getX500Field(BCStyle.POSTAL_ADDRESS, x500Name));
	       dto.setPIB(getX500Field(BCStyle.SN, x500Name));
	       dto.setOrgName(getX500Field(BCStyle.O, x500Name));
	       dto.setOrgNameUnit(getX500Field(BCStyle.OU, x500Name));
	       dto.setCountry(getX500Field(BCStyle.C, x500Name));
	       System.out.println(keyStoreService.getCertificates());
	       IssuerData d = keyStoreService.readIssuerFromStore("admin");
	       dto.setIssuerSerialNumber(getX500Field(BCStyle.UID, d.getX500name()));
	       System.out.println("Gotov dto pravim issuer i sub data");
	       X509Certificate certificate =  generateCertificate(dto);
	       if (certificate == null) {
	           throw new IllegalArgumentException("certificate must be defined.");
	       }
	      
	       try {
	    	   StringWriter stringWriter = new StringWriter();
	           PemWriter writer = new PemWriter(stringWriter);
	           writer.writeObject(new PemObject("CERTIFICATE", certificate.getEncoded()));
	           writer.flush();
	           RestTemplate restTemplate = new RestTemplate();
	           String s = stringWriter.toString();
	           System.out.println(reqId + " " + userId);
				HttpEntity<String> request = new HttpEntity<>(s);
				ResponseEntity<String> response = restTemplate.exchange("https://localhost:8096/requests/" + reqId + "/user/" + userId,
						HttpMethod.POST, request, String.class);

	           return stringWriter.toString();
	       } catch (CertificateEncodingException | IOException e) {
	           throw new RuntimeException("Unable to write a certificate in output stream", e);
	       }
	    }
	    return null;
		
	}
	
	private PKCS10CertificationRequest convertPemToPKCS10CertificationRequest(InputStream pem) {
	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	    PKCS10CertificationRequest csr = null;
	    ByteArrayInputStream pemStream = null;

	    pemStream = (ByteArrayInputStream) pem;

	    Reader pemReader = new BufferedReader(new InputStreamReader(pemStream));
	    PEMParser pemParser = null;
	    try {
	        pemParser = new PEMParser(pemReader);
	        Object parsedObj = pemParser.readObject();
	        if (parsedObj instanceof PKCS10CertificationRequest) {
	            csr = (PKCS10CertificationRequest) parsedObj;
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    } finally {
	        if (pemParser != null) {
	            IOUtils.closeQuietly(pemParser);
	        }
	    }
	    return csr;
	}

	public  static String x509CertificateToPem(final X509Certificate cert) throws IOException {
        final StringWriter writer = new StringWriter();
        final JcaPEMWriter pemWriter = new JcaPEMWriter(writer);
        pemWriter.writeObject(cert);
        pemWriter.flush();
        pemWriter.close();
        return writer.toString();
    }

    public void generateCertFromUser(Korisnik k) {
        k.setStatusNaloga(StatusKorisnika.NEPOTVRDJEN);
        korisnikService.saveKorisnik(k);
        CertificateDTO dto = new CertificateDTO(k.getId().toString(),k.getUsername(), k.getLastName(), "Pig Inc BOOKING", "User Section",
                k.getFirstName(),"RS", k.getEmail(), "false", "", "", null, null,
                "", "", k.getAdresa(), k.getPIB());
        X509Certificate c = generateCertificate(dto);

        StringWriter sw = new StringWriter();
        String crtString;
        try (PEMWriter pw = new PEMWriter(sw)) {
            pw.writeObject(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        crtString =  sw.toString();

        try {
            FileUtils.writeStringToFile(new File("PigIncCertificate.crt"), crtString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String subject = "Account Update";
        String text = "Your certificate has expired. Please provide new certificate from attachment to login successfully. Visit us on: https://localhost:8096";

        EmailAttachment attachment = new EmailAttachment();

        attachment.setPath("PigIncCertificate.crt");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Certificate for " + k.getFirstName() + " " + k.getLastName());
        attachment.setName("PigIncCertificate");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        try {
            email.setAuthentication("pig.inc.ns@gmail.com","tanjaindjic");
            //email.setAuthentication("xmlbesp@gmail.com","Operisedolje!");
            email.setSmtpPort(587);
            email.setStartTLSRequired(true);
            email.addTo(k.getEmail(), k.getFirstName() + " " + k.getLastName());
            email.setFrom("noreply@domain.com", "Pig Inc BOOKING");
            email.setSubject(subject);
            email.setMsg(text);
            email.attach(attachment);
            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        }

    }
}
