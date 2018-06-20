/**
 * SmestajService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xmlWeb_smestaj.wsdl;

public interface SmestajService extends javax.xml.rpc.Service {
    public java.lang.String getSmestajPortAddress();

    public xmlWeb_smestaj.wsdl.SmestajSoap getSmestajPort() throws javax.xml.rpc.ServiceException;

    public xmlWeb_smestaj.wsdl.SmestajSoap getSmestajPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
