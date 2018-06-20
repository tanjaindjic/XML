package xmlWeb_smestaj.wsdl;

public class SmestajSoapProxy implements xmlWeb_smestaj.wsdl.SmestajSoap {
  private String _endpoint = null;
  private xmlWeb_smestaj.wsdl.SmestajSoap smestajSoap = null;
  
  public SmestajSoapProxy() {
    _initSmestajSoapProxy();
  }
  
  public SmestajSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSmestajSoapProxy();
  }
  
  private void _initSmestajSoapProxy() {
    try {
      smestajSoap = (new xmlWeb_smestaj.wsdl.SmestajServiceLocator()).getSmestajPort();
      if (smestajSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)smestajSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)smestajSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (smestajSoap != null)
      ((javax.xml.rpc.Stub)smestajSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public xmlWeb_smestaj.wsdl.SmestajSoap getSmestajSoap() {
    if (smestajSoap == null)
      _initSmestajSoapProxy();
    return smestajSoap;
  }
  
  public xmlWeb_smestaj.wsdl.Smestaj[] getSmestajs() throws java.rmi.RemoteException{
    if (smestajSoap == null)
      _initSmestajSoapProxy();
    return smestajSoap.getSmestajs();
  }
  
  
}