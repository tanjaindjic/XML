package XmlWeb.service;

import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.security.auth.x500.X500Principal;

import XmlWeb.security.CertificateDTO;
import XmlWeb.security.IssuerData;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.springframework.stereotype.Service;



@Service
public class KeyStoreService {
    private static final String PATH_CA = "ksCa.jks";
    private static final String PASSWORD_CA = "passwordCa";
    private static final String PATH_NONCA = "ksNonCa.jks";
    private static final String PASSWORD_NONCA = "passwordNonCa";
    private KeyStore keyStore;
    private String keyStoreName = "";
    private String keyStorePassword = "";

    public KeyStoreService() {
        try {
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch (KeyStoreException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    
    public List<X509Certificate> getCertificates() {
        List<X509Certificate> certificates = new ArrayList<>();

        try {
            for (int i = 0; i < 2; i++) {
                loadKeyStore(i);
                Enumeration<String> aliases = keyStore.aliases();

                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    certificates.add(getCertificate(alias, i).get());

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return certificates;
    }

    public List<CertificateDTO> getCertificatesDTO() {

        List<X509Certificate> certificates = getCertificates();

        List<CertificateDTO> certificateDTOS = new ArrayList<>();
        for (Certificate cert : certificates) {
            certificateDTOS.add(new CertificateDTO(cert));
        }
        return certificateDTOS;
    }

    
    public Optional<X509Certificate> getCertificate(String alias, int i) {
        try {
            loadKeyStore(i);
            X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
            return Optional.ofNullable(cert);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    
    public X509Certificate getCertificate(String alias) {
        for (int i = 0; i < 2; i++) {
            loadKeyStore(i);
            try {
                X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
                if (cert != null) {
                    return cert;
                }
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    
    public ArrayList<String> getCertficatesSN() {
        List<X509Certificate> certificates = getCertificates();
        ArrayList<String> serialNumbers = new ArrayList<>();
        for (X509Certificate cert : certificates) {
            serialNumbers.add(cert.getSerialNumber().toString());
        }
        return serialNumbers;
    }

    
    public CertificateDTO getCertificateDTO(String alias) {
        for (int i = 0; i < 2; i++) {
            loadKeyStore(i);
            try {
                X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
                if (cert != null) {
                    return new CertificateDTO(cert);
                }
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    
    public void writeCertificate(boolean isCa, Certificate certificate, String alias, PrivateKey pk) {
        if (isCa) {
        	System.out.println("isCaCertificate");
            this.keyStoreName = PATH_CA;
            this.keyStorePassword = PASSWORD_CA;
            loadKeyStore(0);
        } else {
            loadKeyStore(1);
            this.keyStoreName = PATH_NONCA;
            this.keyStorePassword = PASSWORD_NONCA;
        }
        try {
            keyStore.setCertificateEntry(alias, certificate);
            keyStore.setKeyEntry(alias, pk, keyStorePassword.toCharArray(), new Certificate[]{certificate});
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        saveKeyStore(keyStoreName, keyStorePassword.toCharArray());
    }

    
    public IssuerData readIssuerFromStore(String alias) {
    	System.out.println("alias: " + alias);
        loadKeyStore(0);
        try {
            Certificate cert = keyStore.getCertificate(alias);
            
            PrivateKey privKey = (PrivateKey) keyStore.getKey(alias, PASSWORD_CA.toCharArray());
            X500Principal subjectX500Principal = ((X509Certificate) cert).getSubjectX500Principal();
            System.out.println(subjectX500Principal);
            X500Name x500name = new X500Name( subjectX500Principal.getName() );
            //X500Name issuerName = new JcaX509CertificateHolder((X509Certificate) cert).getSubject();
            return new IssuerData(privKey, x500name);
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }



    
    public ArrayList<String> getIssuers() {
        loadKeyStore(0);
        ArrayList<String> issuers = null;
        try {
            Enumeration<String> aliases = keyStore.aliases();
            issuers = Collections.list(aliases);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issuers;
    }

    
    public boolean saveKeyStore(String fileName, char[] password) {
        try {
            keyStore.store(new FileOutputStream(fileName), password);
            return true;
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public void createKeyStores() {
        try {
            keyStore.load(null, PASSWORD_CA.toCharArray());
            saveKeyStore(PATH_CA, PASSWORD_CA.toCharArray());
            keyStore.load(null, PASSWORD_NONCA.toCharArray());
            saveKeyStore(PATH_NONCA, PASSWORD_NONCA.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }

    
    public void delete(String alias) {
        for (int i = 0; i < 2; i++) {
            loadKeyStore(i);
            try {
                ArrayList<String> aliases = Collections.list(keyStore.aliases());
                if (aliases.contains(alias)) {
                    keyStore.deleteEntry(alias);
                    if (i == 0) {
                        saveKeyStore(PATH_CA, PASSWORD_CA.toCharArray());
                    } else if (i == 1) {
                        saveKeyStore(PATH_NONCA, PASSWORD_NONCA.toCharArray());
                    }
                    return;
                }
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
        }
    }

    
    public void deleteList(List<X509Certificate> certificates) {
        for (X509Certificate cert : certificates) {
            delete(cert.getSerialNumber().toString());
        }
    }

    
    public PrivateKey readPrivateKey(String alias) {
        loadKeyStore(1);
        try {
            PrivateKey privKey = (PrivateKey) keyStore.getKey(alias, PASSWORD_NONCA.toCharArray());
            return  privKey;
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Certificate readCertificate(String alias) {
        loadKeyStore(1);
        try {
            Certificate cert = keyStore.getCertificate(alias);
            return  cert;
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return null;
    }




    
    public void loadKeyStore(String keyStoreFile, String keyStorePassword) {
     
        try {
            keyStore.load(new FileInputStream(keyStoreFile), keyStorePassword.toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }
    }

    // i = 0 pristupa se CA keystoru
    // i = 1 pristupa se NonCA keystoru
    // i = 2 pristupa se Root keystoru
    public void loadKeyStore(int i) {
        if (i == 0) {
            loadKeyStore(PATH_CA, PASSWORD_CA);

        } else if (i == 1) {
            loadKeyStore(PATH_NONCA, PASSWORD_NONCA);

        }
    }

    public void saveAgentCert(X509Certificate x509Certificate, String username) {
        loadKeyStore(1);
        try {

            keyStore.setCertificateEntry(username, x509Certificate);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        saveKeyStore(PATH_NONCA, PASSWORD_NONCA.toCharArray());
        /*try {
            OutputStream outputStream = new FileOutputStream(PATH_CA);
            keyStore.store(outputStream, PASSWORD_NONCA.toCharArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public List<CertificateDTO> getAdminCertificatesDTO() {
        List<X509Certificate> certificates = new ArrayList<>();

        try {
                loadKeyStore(0);
                Enumeration<String> aliases = keyStore.aliases();

                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    certificates.add(getCertificate(alias, 0).get());

                }


        } catch (Exception e) {
            e.printStackTrace();
        }

        List<CertificateDTO> certificateDTOS = new ArrayList<>();
        for (Certificate cert : certificates) {
            certificateDTOS.add(new CertificateDTO(cert));
        }
        System.out.println("Admin certificates size: " + certificateDTOS.size());
        return certificateDTOS;
    }
}

