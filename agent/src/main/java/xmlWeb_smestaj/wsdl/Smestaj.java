/**
 * Smestaj.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xmlWeb_smestaj.wsdl;

public class Smestaj  implements java.io.Serializable {
    private java.lang.String adresa;

    private int brojOcena;

    private xmlWeb_smestaj.wsdl.DodatneUsluge[] dodatneUsluge;

    private java.lang.String drzava;

    private java.lang.String gmapUrl;

    private java.lang.String grad;

    private java.lang.Long id;

    private java.lang.String naziv;

    private java.lang.String opis;

    private float rejting;

    private xmlWeb_smestaj.wsdl.Slika[] slike;

    private xmlWeb_smestaj.wsdl.Soba[] sobe;

    private xmlWeb_smestaj.wsdl.TipSmestaja tip;

    private int version;

    private xmlWeb_smestaj.wsdl.Korisnik vlasnik;

    private java.lang.Integer zvezdice;

    public Smestaj() {
    }

    public Smestaj(
           java.lang.String adresa,
           int brojOcena,
           xmlWeb_smestaj.wsdl.DodatneUsluge[] dodatneUsluge,
           java.lang.String drzava,
           java.lang.String gmapUrl,
           java.lang.String grad,
           java.lang.Long id,
           java.lang.String naziv,
           java.lang.String opis,
           float rejting,
           xmlWeb_smestaj.wsdl.Slika[] slike,
           xmlWeb_smestaj.wsdl.Soba[] sobe,
           xmlWeb_smestaj.wsdl.TipSmestaja tip,
           int version,
           xmlWeb_smestaj.wsdl.Korisnik vlasnik,
           java.lang.Integer zvezdice) {
           this.adresa = adresa;
           this.brojOcena = brojOcena;
           this.dodatneUsluge = dodatneUsluge;
           this.drzava = drzava;
           this.gmapUrl = gmapUrl;
           this.grad = grad;
           this.id = id;
           this.naziv = naziv;
           this.opis = opis;
           this.rejting = rejting;
           this.slike = slike;
           this.sobe = sobe;
           this.tip = tip;
           this.version = version;
           this.vlasnik = vlasnik;
           this.zvezdice = zvezdice;
    }


    /**
     * Gets the adresa value for this Smestaj.
     * 
     * @return adresa
     */
    public java.lang.String getAdresa() {
        return adresa;
    }


    /**
     * Sets the adresa value for this Smestaj.
     * 
     * @param adresa
     */
    public void setAdresa(java.lang.String adresa) {
        this.adresa = adresa;
    }


    /**
     * Gets the brojOcena value for this Smestaj.
     * 
     * @return brojOcena
     */
    public int getBrojOcena() {
        return brojOcena;
    }


    /**
     * Sets the brojOcena value for this Smestaj.
     * 
     * @param brojOcena
     */
    public void setBrojOcena(int brojOcena) {
        this.brojOcena = brojOcena;
    }


    /**
     * Gets the dodatneUsluge value for this Smestaj.
     * 
     * @return dodatneUsluge
     */
    public xmlWeb_smestaj.wsdl.DodatneUsluge[] getDodatneUsluge() {
        return dodatneUsluge;
    }


    /**
     * Sets the dodatneUsluge value for this Smestaj.
     * 
     * @param dodatneUsluge
     */
    public void setDodatneUsluge(xmlWeb_smestaj.wsdl.DodatneUsluge[] dodatneUsluge) {
        this.dodatneUsluge = dodatneUsluge;
    }

    public xmlWeb_smestaj.wsdl.DodatneUsluge getDodatneUsluge(int i) {
        return this.dodatneUsluge[i];
    }

    public void setDodatneUsluge(int i, xmlWeb_smestaj.wsdl.DodatneUsluge _value) {
        this.dodatneUsluge[i] = _value;
    }


    /**
     * Gets the drzava value for this Smestaj.
     * 
     * @return drzava
     */
    public java.lang.String getDrzava() {
        return drzava;
    }


    /**
     * Sets the drzava value for this Smestaj.
     * 
     * @param drzava
     */
    public void setDrzava(java.lang.String drzava) {
        this.drzava = drzava;
    }


    /**
     * Gets the gmapUrl value for this Smestaj.
     * 
     * @return gmapUrl
     */
    public java.lang.String getGmapUrl() {
        return gmapUrl;
    }


    /**
     * Sets the gmapUrl value for this Smestaj.
     * 
     * @param gmapUrl
     */
    public void setGmapUrl(java.lang.String gmapUrl) {
        this.gmapUrl = gmapUrl;
    }


    /**
     * Gets the grad value for this Smestaj.
     * 
     * @return grad
     */
    public java.lang.String getGrad() {
        return grad;
    }


    /**
     * Sets the grad value for this Smestaj.
     * 
     * @param grad
     */
    public void setGrad(java.lang.String grad) {
        this.grad = grad;
    }


    /**
     * Gets the id value for this Smestaj.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this Smestaj.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the naziv value for this Smestaj.
     * 
     * @return naziv
     */
    public java.lang.String getNaziv() {
        return naziv;
    }


    /**
     * Sets the naziv value for this Smestaj.
     * 
     * @param naziv
     */
    public void setNaziv(java.lang.String naziv) {
        this.naziv = naziv;
    }


    /**
     * Gets the opis value for this Smestaj.
     * 
     * @return opis
     */
    public java.lang.String getOpis() {
        return opis;
    }


    /**
     * Sets the opis value for this Smestaj.
     * 
     * @param opis
     */
    public void setOpis(java.lang.String opis) {
        this.opis = opis;
    }


    /**
     * Gets the rejting value for this Smestaj.
     * 
     * @return rejting
     */
    public float getRejting() {
        return rejting;
    }


    /**
     * Sets the rejting value for this Smestaj.
     * 
     * @param rejting
     */
    public void setRejting(float rejting) {
        this.rejting = rejting;
    }


    /**
     * Gets the slike value for this Smestaj.
     * 
     * @return slike
     */
    public xmlWeb_smestaj.wsdl.Slika[] getSlike() {
        return slike;
    }


    /**
     * Sets the slike value for this Smestaj.
     * 
     * @param slike
     */
    public void setSlike(xmlWeb_smestaj.wsdl.Slika[] slike) {
        this.slike = slike;
    }

    public xmlWeb_smestaj.wsdl.Slika getSlike(int i) {
        return this.slike[i];
    }

    public void setSlike(int i, xmlWeb_smestaj.wsdl.Slika _value) {
        this.slike[i] = _value;
    }


    /**
     * Gets the sobe value for this Smestaj.
     * 
     * @return sobe
     */
    public xmlWeb_smestaj.wsdl.Soba[] getSobe() {
        return sobe;
    }


    /**
     * Sets the sobe value for this Smestaj.
     * 
     * @param sobe
     */
    public void setSobe(xmlWeb_smestaj.wsdl.Soba[] sobe) {
        this.sobe = sobe;
    }

    public xmlWeb_smestaj.wsdl.Soba getSobe(int i) {
        return this.sobe[i];
    }

    public void setSobe(int i, xmlWeb_smestaj.wsdl.Soba _value) {
        this.sobe[i] = _value;
    }


    /**
     * Gets the tip value for this Smestaj.
     * 
     * @return tip
     */
    public xmlWeb_smestaj.wsdl.TipSmestaja getTip() {
        return tip;
    }


    /**
     * Sets the tip value for this Smestaj.
     * 
     * @param tip
     */
    public void setTip(xmlWeb_smestaj.wsdl.TipSmestaja tip) {
        this.tip = tip;
    }


    /**
     * Gets the version value for this Smestaj.
     * 
     * @return version
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version value for this Smestaj.
     * 
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Gets the vlasnik value for this Smestaj.
     * 
     * @return vlasnik
     */
    public xmlWeb_smestaj.wsdl.Korisnik getVlasnik() {
        return vlasnik;
    }


    /**
     * Sets the vlasnik value for this Smestaj.
     * 
     * @param vlasnik
     */
    public void setVlasnik(xmlWeb_smestaj.wsdl.Korisnik vlasnik) {
        this.vlasnik = vlasnik;
    }


    /**
     * Gets the zvezdice value for this Smestaj.
     * 
     * @return zvezdice
     */
    public java.lang.Integer getZvezdice() {
        return zvezdice;
    }


    /**
     * Sets the zvezdice value for this Smestaj.
     * 
     * @param zvezdice
     */
    public void setZvezdice(java.lang.Integer zvezdice) {
        this.zvezdice = zvezdice;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Smestaj)) return false;
        Smestaj other = (Smestaj) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.adresa==null && other.getAdresa()==null) || 
             (this.adresa!=null &&
              this.adresa.equals(other.getAdresa()))) &&
            this.brojOcena == other.getBrojOcena() &&
            ((this.dodatneUsluge==null && other.getDodatneUsluge()==null) || 
             (this.dodatneUsluge!=null &&
              java.util.Arrays.equals(this.dodatneUsluge, other.getDodatneUsluge()))) &&
            ((this.drzava==null && other.getDrzava()==null) || 
             (this.drzava!=null &&
              this.drzava.equals(other.getDrzava()))) &&
            ((this.gmapUrl==null && other.getGmapUrl()==null) || 
             (this.gmapUrl!=null &&
              this.gmapUrl.equals(other.getGmapUrl()))) &&
            ((this.grad==null && other.getGrad()==null) || 
             (this.grad!=null &&
              this.grad.equals(other.getGrad()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.naziv==null && other.getNaziv()==null) || 
             (this.naziv!=null &&
              this.naziv.equals(other.getNaziv()))) &&
            ((this.opis==null && other.getOpis()==null) || 
             (this.opis!=null &&
              this.opis.equals(other.getOpis()))) &&
            this.rejting == other.getRejting() &&
            ((this.slike==null && other.getSlike()==null) || 
             (this.slike!=null &&
              java.util.Arrays.equals(this.slike, other.getSlike()))) &&
            ((this.sobe==null && other.getSobe()==null) || 
             (this.sobe!=null &&
              java.util.Arrays.equals(this.sobe, other.getSobe()))) &&
            ((this.tip==null && other.getTip()==null) || 
             (this.tip!=null &&
              this.tip.equals(other.getTip()))) &&
            this.version == other.getVersion() &&
            ((this.vlasnik==null && other.getVlasnik()==null) || 
             (this.vlasnik!=null &&
              this.vlasnik.equals(other.getVlasnik()))) &&
            ((this.zvezdice==null && other.getZvezdice()==null) || 
             (this.zvezdice!=null &&
              this.zvezdice.equals(other.getZvezdice())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAdresa() != null) {
            _hashCode += getAdresa().hashCode();
        }
        _hashCode += getBrojOcena();
        if (getDodatneUsluge() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDodatneUsluge());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDodatneUsluge(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDrzava() != null) {
            _hashCode += getDrzava().hashCode();
        }
        if (getGmapUrl() != null) {
            _hashCode += getGmapUrl().hashCode();
        }
        if (getGrad() != null) {
            _hashCode += getGrad().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNaziv() != null) {
            _hashCode += getNaziv().hashCode();
        }
        if (getOpis() != null) {
            _hashCode += getOpis().hashCode();
        }
        _hashCode += new Float(getRejting()).hashCode();
        if (getSlike() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSlike());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSlike(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSobe() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSobe());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSobe(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTip() != null) {
            _hashCode += getTip().hashCode();
        }
        _hashCode += getVersion();
        if (getVlasnik() != null) {
            _hashCode += getVlasnik().hashCode();
        }
        if (getZvezdice() != null) {
            _hashCode += getZvezdice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Smestaj.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "smestaj"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("brojOcena");
        elemField.setXmlName(new javax.xml.namespace.QName("", "brojOcena"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dodatneUsluge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dodatneUsluge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "dodatneUsluge"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("drzava");
        elemField.setXmlName(new javax.xml.namespace.QName("", "drzava"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gmapUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gmapUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("naziv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "naziv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "opis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rejting");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rejting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("slike");
        elemField.setXmlName(new javax.xml.namespace.QName("", "slike"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "slika"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sobe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sobe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "soba"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tip");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "tipSmestaja"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vlasnik");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vlasnik"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "korisnik"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zvezdice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "zvezdice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
