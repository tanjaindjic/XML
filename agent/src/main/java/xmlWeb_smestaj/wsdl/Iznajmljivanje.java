/**
 * Iznajmljivanje.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xmlWeb_smestaj.wsdl;

public class Iznajmljivanje  implements java.io.Serializable {
    private java.lang.Long cena;

    private java.util.Calendar datumDo;

    private java.util.Calendar datumOd;

    private java.lang.Long id;

    private java.lang.Boolean mozePojedinacno;

    private xmlWeb_smestaj.wsdl.Soba soba;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(
           java.lang.Long cena,
           java.util.Calendar datumDo,
           java.util.Calendar datumOd,
           java.lang.Long id,
           java.lang.Boolean mozePojedinacno,
           xmlWeb_smestaj.wsdl.Soba soba) {
           this.cena = cena;
           this.datumDo = datumDo;
           this.datumOd = datumOd;
           this.id = id;
           this.mozePojedinacno = mozePojedinacno;
           this.soba = soba;
    }


    /**
     * Gets the cena value for this Iznajmljivanje.
     * 
     * @return cena
     */
    public java.lang.Long getCena() {
        return cena;
    }


    /**
     * Sets the cena value for this Iznajmljivanje.
     * 
     * @param cena
     */
    public void setCena(java.lang.Long cena) {
        this.cena = cena;
    }


    /**
     * Gets the datumDo value for this Iznajmljivanje.
     * 
     * @return datumDo
     */
    public java.util.Calendar getDatumDo() {
        return datumDo;
    }


    /**
     * Sets the datumDo value for this Iznajmljivanje.
     * 
     * @param datumDo
     */
    public void setDatumDo(java.util.Calendar datumDo) {
        this.datumDo = datumDo;
    }


    /**
     * Gets the datumOd value for this Iznajmljivanje.
     * 
     * @return datumOd
     */
    public java.util.Calendar getDatumOd() {
        return datumOd;
    }


    /**
     * Sets the datumOd value for this Iznajmljivanje.
     * 
     * @param datumOd
     */
    public void setDatumOd(java.util.Calendar datumOd) {
        this.datumOd = datumOd;
    }


    /**
     * Gets the id value for this Iznajmljivanje.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this Iznajmljivanje.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the mozePojedinacno value for this Iznajmljivanje.
     * 
     * @return mozePojedinacno
     */
    public java.lang.Boolean getMozePojedinacno() {
        return mozePojedinacno;
    }


    /**
     * Sets the mozePojedinacno value for this Iznajmljivanje.
     * 
     * @param mozePojedinacno
     */
    public void setMozePojedinacno(java.lang.Boolean mozePojedinacno) {
        this.mozePojedinacno = mozePojedinacno;
    }


    /**
     * Gets the soba value for this Iznajmljivanje.
     * 
     * @return soba
     */
    public xmlWeb_smestaj.wsdl.Soba getSoba() {
        return soba;
    }


    /**
     * Sets the soba value for this Iznajmljivanje.
     * 
     * @param soba
     */
    public void setSoba(xmlWeb_smestaj.wsdl.Soba soba) {
        this.soba = soba;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Iznajmljivanje)) return false;
        Iznajmljivanje other = (Iznajmljivanje) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cena==null && other.getCena()==null) || 
             (this.cena!=null &&
              this.cena.equals(other.getCena()))) &&
            ((this.datumDo==null && other.getDatumDo()==null) || 
             (this.datumDo!=null &&
              this.datumDo.equals(other.getDatumDo()))) &&
            ((this.datumOd==null && other.getDatumOd()==null) || 
             (this.datumOd!=null &&
              this.datumOd.equals(other.getDatumOd()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.mozePojedinacno==null && other.getMozePojedinacno()==null) || 
             (this.mozePojedinacno!=null &&
              this.mozePojedinacno.equals(other.getMozePojedinacno()))) &&
            ((this.soba==null && other.getSoba()==null) || 
             (this.soba!=null &&
              this.soba.equals(other.getSoba())));
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
        if (getCena() != null) {
            _hashCode += getCena().hashCode();
        }
        if (getDatumDo() != null) {
            _hashCode += getDatumDo().hashCode();
        }
        if (getDatumOd() != null) {
            _hashCode += getDatumOd().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMozePojedinacno() != null) {
            _hashCode += getMozePojedinacno().hashCode();
        }
        if (getSoba() != null) {
            _hashCode += getSoba().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Iznajmljivanje.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "iznajmljivanje"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cena");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cena"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datumDo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datumDo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datumOd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datumOd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("mozePojedinacno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mozePojedinacno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soba");
        elemField.setXmlName(new javax.xml.namespace.QName("", "soba"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "soba"));
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
