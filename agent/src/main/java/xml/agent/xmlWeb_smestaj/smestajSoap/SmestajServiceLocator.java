/**
 * SmestajServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xml.agent.xmlWeb_smestaj.smestajSoap;

import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.WebServiceClient;

import org.apache.axis.client.Service;

@WebServiceClient(name = "SmestajServiceLocator", targetNamespace = "https://agent.soap.smestaj", wsdlLocation = "https://localhost:8096/services/smestajService?wsdl")
public class SmestajServiceLocator extends Service implements SmestajService {

    public SmestajServiceLocator() {
    }


    public SmestajServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SmestajServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SmestajPort
    private String SmestajPort_address = "https://localhost:8096/services/smestajService?wsdl";

    public String getSmestajPortAddress() {
        return SmestajPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String SmestajPortWSDDServiceName = "SmestajPort";

    public String getSmestajPortWSDDServiceName() {
        return SmestajPortWSDDServiceName;
    }

    public void setSmestajPortWSDDServiceName(String name) {
        SmestajPortWSDDServiceName = name;
    }

    public SmestajSoap getSmestajPort() throws ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SmestajPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getSmestajPort(endpoint);
    }

    public SmestajSoap getSmestajPort(java.net.URL portAddress) throws ServiceException {
        try {
            SmestajServiceSoapBindingStub _stub = new SmestajServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSmestajPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSmestajPortEndpointAddress(String address) {
        SmestajPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
        try {
            if (SmestajSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                SmestajServiceSoapBindingStub _stub = new SmestajServiceSoapBindingStub(new URL(SmestajPort_address), this);
                _stub.setPortName(getSmestajPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new ServiceException(t);
        }
        throw new ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public Remote getPort(QName portName, Class serviceEndpointInterface) throws ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("SmestajPort".equals(inputPortName)) {
            return getSmestajPort();
        }
        else  {
            Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public QName getServiceName() {
        return new QName("http://xmlWeb-smestaj/smestajSoap", "SmestajService");
    }

    private HashSet ports = null;

    public Iterator getPorts() {
        if (ports == null) {
            ports = new HashSet();
            ports.add(new QName("http://xmlWeb-smestaj/smestajSoap", "SmestajPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws ServiceException {
        
if ("SmestajPort".equals(portName)) {
            setSmestajPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(QName portName, String address) throws ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
