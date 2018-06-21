package xmlWeb_smestaj.smestajSoap;

public class SmestajSoapProxy implements xmlWeb_smestaj.smestajSoap.SmestajSoap {
  private String _endpoint = null;
  private xmlWeb_smestaj.smestajSoap.SmestajSoap smestajSoap = null;
  
  public SmestajSoapProxy() {
    _initSmestajSoapProxy();
  }
  
  public SmestajSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSmestajSoapProxy();
  }
  
  private void _initSmestajSoapProxy() {
    try {
      smestajSoap = (new xmlWeb_smestaj.smestajSoap.SmestajServiceLocator()).getSmestajPort();
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
  
  public xmlWeb_smestaj.smestajSoap.SmestajSoap getSmestajSoap() {
    if (smestajSoap == null)
      _initSmestajSoapProxy();
    return smestajSoap;
  }
  
  public java.lang.Object[] getSmestajs() throws java.rmi.RemoteException{
    if (smestajSoap == null)
      _initSmestajSoapProxy();
    return smestajSoap.getSmestajs();
  }
  
  
}