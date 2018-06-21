package XmlWeb.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import XmlWeb.soapServices.SmestajSoap;
import XmlWeb.soapServices.impl.SmestajSoapImpl;

@Configuration
public class SoapConfig {
	@Autowired
	private SmestajSoap smestajWebService;
	@Bean
    public SmestajSoap smestajSoap() {
        return new SmestajSoapImpl();
    }

	@Bean 
	public Endpoint smestajSoapEndpoint(){
		EndpointImpl endpoint = new EndpointImpl(springBus(), smestajWebService);
		endpoint.publish("/smestajService");
		return endpoint;
	}
	@Bean(name=Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		SpringBus sb = new SpringBus();
		sb.getInInterceptors().add(getWSS4JInInterceptor());
		sb.getOutInterceptors().add(getWSS4JOutInterceptor());
		return sb;
	}
	
	private WSS4JInInterceptor getWSS4JInInterceptor() {
		Map<String, Object> inProps = new HashMap<String, Object>();
		inProps.put(WSHandlerConstants.ACTION,
				WSHandlerConstants.TIMESTAMP + " " + WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
		inProps.put(WSHandlerConstants.SIG_PROP_FILE, "server-crypto.properties");
		inProps.put(WSHandlerConstants.DEC_PROP_FILE, "server-crypto.properties");
	//	inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerPasswordCallback.class.getName());

		return new WSS4JInInterceptor(inProps);
	}

	private WSS4JOutInterceptor getWSS4JOutInterceptor() {
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, 
				WSHandlerConstants.TIMESTAMP + " " + WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
		outProps.put(WSHandlerConstants.USER, "server");
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "server-crypto.properties");
		outProps.put(WSHandlerConstants.ENC_PROP_FILE, "server-crypto.properties");
		outProps.put(WSHandlerConstants.ENCRYPTION_USER, WSHandlerConstants.USE_REQ_SIG_CERT);
		outProps.put(WSHandlerConstants.SIGNATURE_PARTS,
				"{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		outProps.put(WSHandlerConstants.ENCRYPTION_PARTS,
				"{Element}{http://www.w3.org/2000/09/xmldsig#}Signature;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		outProps.put(WSHandlerConstants.ENC_SYM_ALGO, WSConstants.TRIPLE_DES);
	//	outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerPasswordCallback.class.getName());

		return new WSS4JOutInterceptor(outProps);
	}
}
