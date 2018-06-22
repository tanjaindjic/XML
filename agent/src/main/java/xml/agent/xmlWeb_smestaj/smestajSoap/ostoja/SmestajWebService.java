package xml.agent.xmlWeb_smestaj.smestajSoap.ostoja;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;

@WebServiceClient(name = "SmestajWebService", targetNamespace = "http://agent.xml.soap/smestaj", wsdlLocation = "https://localhost:8096/services/smestajService?wsdl")
public class SmestajWebService extends Service
{

    private final static URL SmestajWebService_WSDL_LOCATION;
    private final static WebServiceException SmestajWebService_EXCEPTION;									
    private final static QName SmestajWebService_QNAME = new QName("http://xmlWeb-smestaj/smestajSoap", "smestajService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://localhost:8096/services/smestajService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SmestajWebService_WSDL_LOCATION = url;
        SmestajWebService_EXCEPTION = e;
    }

    public SmestajWebService() {
        super(__getWsdlLocation(), SmestajWebService_QNAME);
    }

    public SmestajWebService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SmestajWebService_QNAME, features);
    }

    public SmestajWebService(URL wsdlLocation) {
        super(wsdlLocation, SmestajWebService_QNAME);
    }

    public SmestajWebService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SmestajWebService_QNAME, features);
    }

    public SmestajWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SmestajWebService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "SmestajServicePort")
    public SmestajService getSmestajServicePort() {
    	SmestajService port = super.getPort(new QName("http://xmlWeb-smestaj/smestajSoap", "SmestajServicePort"), SmestajService.class);
    	HTTPConduit httpConduit = (HTTPConduit) ClientProxy.getClient(port).getConduit();

    	TLSClientParameters tlsCP = new TLSClientParameters();
    	// other TLS/SSL configuration like setting up TrustManagers
    	tlsCP.setDisableCNCheck(true);
    	httpConduit.setTlsClientParameters(tlsCP);
        return port;
    }


    @WebEndpoint(name = "SmestajServicePort")
    public SmestajService getSmestajServicePort(WebServiceFeature... features) {
    	SmestajService port = super.getPort(new QName("http://xmlWeb-smestaj/smestajSoap", "SmestajServicePort"), SmestajService.class, features);
    	HTTPConduit httpConduit = (HTTPConduit) ClientProxy.getClient(port).getConduit();

    	TLSClientParameters tlsCP = new TLSClientParameters();
    	// other TLS/SSL configuration like setting up TrustManagers
    	tlsCP.setDisableCNCheck(true);
    	httpConduit.setTlsClientParameters(tlsCP);
        return port;
    }

    private static URL __getWsdlLocation() {
        if (SmestajWebService_EXCEPTION!= null) {
            throw SmestajWebService_EXCEPTION;
        }
        return SmestajWebService_WSDL_LOCATION;
    }

}