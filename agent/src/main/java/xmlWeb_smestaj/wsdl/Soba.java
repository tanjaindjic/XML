/**
 * Soba.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xmlWeb_smestaj.wsdl;

public class Soba  implements java.io.Serializable {
    private int brojLezaja;

    private xmlWeb_smestaj.wsdl.Cenovnik[] cene;

    private java.lang.Long id;

    private xmlWeb_smestaj.wsdl.Iznajmljivanje[] iznajmljivanja;

    private xmlWeb_smestaj.wsdl.KategorijaSmestaja kategorija;

    private xmlWeb_smestaj.wsdl.DodatneUsluge[] opcija;

    private xmlWeb_smestaj.wsdl.Rezervacija[] rezervisano;

    public Soba() {
    }

    public Soba(
           int brojLezaja,
           xmlWeb_smestaj.wsdl.Cenovnik[] cene,
           java.lang.Long id,
           xmlWeb_smestaj.wsdl.Iznajmljivanje[] iznajmljivanja,
           xmlWeb_smestaj.wsdl.KategorijaSmestaja kategorija,
           xmlWeb_smestaj.wsdl.DodatneUsluge[] opcija,
           xmlWeb_smestaj.wsdl.Rezervacija[] rezervisano) {
           this.brojLezaja = brojLezaja;
           this.cene = cene;
           this.id = id;
           this.iznajmljivanja = iznajmljivanja;
           this.kategorija = kategorija;
           this.opcija = opcija;
           this.rezervisano = rezervisano;
    }


    /**
     * Gets the brojLezaja value for this Soba.
     * 
     * @return brojLezaja
     */
    public int getBrojLezaja() {
        return brojLezaja;
    }


    /**
     * Sets the brojLezaja value for this Soba.
     * 
     * @param brojLezaja
     */
    public void setBrojLezaja(int brojLezaja) {
        this.brojLezaja = brojLezaja;
    }


    /**
     * Gets the cene value for this Soba.
     * 
     * @return cene
     */
    public xmlWeb_smestaj.wsdl.Cenovnik[] getCene() {
        return cene;
    }


    /**
     * Sets the cene value for this Soba.
     * 
     * @param cene
     */
    public void setCene(xmlWeb_smestaj.wsdl.Cenovnik[] cene) {
        this.cene = cene;
    }

    public xmlWeb_smestaj.wsdl.Cenovnik getCene(int i) {
        return this.cene[i];
    }

    public void setCene(int i, xmlWeb_smestaj.wsdl.Cenovnik _value) {
        this.cene[i] = _value;
    }


    /**
     * Gets the id value for this Soba.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this Soba.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the iznajmljivanja value for this Soba.
     * 
     * @return iznajmljivanja
     */
    public xmlWeb_smestaj.wsdl.Iznajmljivanje[] getIznajmljivanja() {
        return iznajmljivanja;
    }


    /**
     * Sets the iznajmljivanja value for this Soba.
     * 
     * @param iznajmljivanja
     */
    public void setIznajmljivanja(xmlWeb_smestaj.wsdl.Iznajmljivanje[] iznajmljivanja) {
        this.iznajmljivanja = iznajmljivanja;
    }

    public xmlWeb_smestaj.wsdl.Iznajmljivanje getIznajmljivanja(int i) {
        return this.iznajmljivanja[i];
    }

    public void setIznajmljivanja(int i, xmlWeb_smestaj.wsdl.Iznajmljivanje _value) {
        this.iznajmljivanja[i] = _value;
    }


    /**
     * Gets the kategorija value for this Soba.
     * 
     * @return kategorija
     */
    public xmlWeb_smestaj.wsdl.KategorijaSmestaja getKategorija() {
        return kategorija;
    }


    /**
     * Sets the kategorija value for this Soba.
     * 
     * @param kategorija
     */
    public void setKategorija(xmlWeb_smestaj.wsdl.KategorijaSmestaja kategorija) {
        this.kategorija = kategorija;
    }


    /**
     * Gets the opcija value for this Soba.
     * 
     * @return opcija
     */
    public xmlWeb_smestaj.wsdl.DodatneUsluge[] getOpcija() {
        return opcija;
    }


    /**
     * Sets the opcija value for this Soba.
     * 
     * @param opcija
     */
    public void setOpcija(xmlWeb_smestaj.wsdl.DodatneUsluge[] opcija) {
        this.opcija = opcija;
    }

    public xmlWeb_smestaj.wsdl.DodatneUsluge getOpcija(int i) {
        return this.opcija[i];
    }

    public void setOpcija(int i, xmlWeb_smestaj.wsdl.DodatneUsluge _value) {
        this.opcija[i] = _value;
    }


    /**
     * Gets the rezervisano value for this Soba.
     * 
     * @return rezervisano
     */
    public xmlWeb_smestaj.wsdl.Rezervacija[] getRezervisano() {
        return rezervisano;
    }


    /**
     * Sets the rezervisano value for this Soba.
     * 
     * @param rezervisano
     */
    public void setRezervisano(xmlWeb_smestaj.wsdl.Rezervacija[] rezervisano) {
        this.rezervisano = rezervisano;
    }

    public xmlWeb_smestaj.wsdl.Rezervacija getRezervisano(int i) {
        return this.rezervisano[i];
    }

    public void setRezervisano(int i, xmlWeb_smestaj.wsdl.Rezervacija _value) {
        this.rezervisano[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Soba)) return false;
        Soba other = (Soba) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.brojLezaja == other.getBrojLezaja() &&
            ((this.cene==null && other.getCene()==null) || 
             (this.cene!=null &&
              java.util.Arrays.equals(this.cene, other.getCene()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.iznajmljivanja==null && other.getIznajmljivanja()==null) || 
             (this.iznajmljivanja!=null &&
              java.util.Arrays.equals(this.iznajmljivanja, other.getIznajmljivanja()))) &&
            ((this.kategorija==null && other.getKategorija()==null) || 
             (this.kategorija!=null &&
              this.kategorija.equals(other.getKategorija()))) &&
            ((this.opcija==null && other.getOpcija()==null) || 
             (this.opcija!=null &&
              java.util.Arrays.equals(this.opcija, other.getOpcija()))) &&
            ((this.rezervisano==null && other.getRezervisano()==null) || 
             (this.rezervisano!=null &&
              java.util.Arrays.equals(this.rezervisano, other.getRezervisano())));
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
        _hashCode += getBrojLezaja();
        if (getCene() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCene());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCene(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIznajmljivanja() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIznajmljivanja());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIznajmljivanja(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getKategorija() != null) {
            _hashCode += getKategorija().hashCode();
        }
        if (getOpcija() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOpcija());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOpcija(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRezervisano() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRezervisano());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRezervisano(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Soba.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "soba"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("brojLezaja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "brojLezaja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cene");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cene"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "cenovnik"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iznajmljivanja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iznajmljivanja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "iznajmljivanje"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kategorija");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kategorija"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "kategorijaSmestaja"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opcija");
        elemField.setXmlName(new javax.xml.namespace.QName("", "opcija"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "dodatneUsluge"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rezervisano");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rezervisano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "rezervacija"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
