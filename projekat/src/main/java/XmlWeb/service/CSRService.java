package XmlWeb.service;

import java.io.IOException;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.springframework.stereotype.Service;

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

}
