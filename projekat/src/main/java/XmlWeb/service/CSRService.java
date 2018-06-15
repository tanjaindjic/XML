package XmlWeb.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;

import XmlWeb.model.Korisnik;

@Service
public class CSRService {
	
	public PKCS10CertificationRequest createCSR(Korisnik kor) throws NoSuchAlgorithmException, NoSuchProviderException, OperatorCreationException{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		KeyPair pair = keyGen.generateKeyPair();
		X500NameBuilder x500NameBld = new X500NameBuilder(BCStyle.INSTANCE);

	    x500NameBld.addRDN(BCStyle.UNIQUE_IDENTIFIER, kor.getUsername());

	    X500Name subject = x500NameBld.build();
		PKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(
				subject, pair.getPublic());
		JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder("SHA256withRSA");
		ContentSigner signer = csBuilder.build(pair.getPrivate());
		PKCS10CertificationRequest csr = p10Builder.build(signer);
		return csr;
	}
	
	public String generatePem(Korisnik kor) throws NoSuchAlgorithmException, NoSuchProviderException, OperatorCreationException, IOException {
		
		 StringWriter sw = new StringWriter();
		    try (JcaPEMWriter pw = new JcaPEMWriter(sw)) {
		        pw.writeObject(createCSR(kor));
		        pw.flush();
		    }
		    System.out.println(sw.toString());
		    return sw.toString();
		
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
	
	public String readCertificateSigningRequest(InputStream pem) {

	    PKCS10CertificationRequest csr = convertPemToPKCS10CertificationRequest(pem);
	    String username = null;

	    if (csr == null) {
	        System.out.println("FAIL! conversion of Pem To PKCS10 Certification Request");
	    } else {
	       X500Name x500Name = csr.getSubject();

	     

	       username = getX500Field(BCStyle.UNIQUE_IDENTIFIER, x500Name);
	    }
	    return username;
	}
	
	private String getX500Field(ASN1ObjectIdentifier asn1ObjectIdentifier, X500Name x500Name) {
	    RDN[] rdnArray = x500Name.getRDNs(asn1ObjectIdentifier);

	    String retVal = null;
	    for (RDN item : rdnArray) {
	        retVal = item.getFirst().getValue().toString();
	    }
	    return retVal;
	}
	
	
	

}
