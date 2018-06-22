/**
 * SmestajService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xml.agent.xmlWeb_smestaj.smestajSoap;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public interface SmestajService extends Service {
    public String getSmestajPortAddress();

    public SmestajSoap getSmestajPort() throws ServiceException;

    public SmestajSoap getSmestajPort(java.net.URL portAddress) throws ServiceException;
}
