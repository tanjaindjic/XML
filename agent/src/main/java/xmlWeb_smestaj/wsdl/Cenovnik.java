/**
 * Cenovnik.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xmlWeb_smestaj.wsdl;

public class Cenovnik  implements java.io.Serializable {
    private java.lang.Long cena;

    private java.util.Calendar datumDo;

    private java.util.Calendar datumOd;

    private java.lang.Long id;

    public Cenovnik() {
    }

    public Cenovnik(
           java.lang.Long cena,
           java.util.Calendar datumDo,
           java.util.Calendar datumOd,
           java.lang.Long id) {
           this.cena = cena;
           this.datumDo = datumDo;
           this.datumOd = datumOd;
           this.id = id;
    }


    /**
     * Gets the cena value for this Cenovnik.
     * 
     * @return cena
     */
    public java.lang.Long getCena() {
        return cena;
    }


    /**
     * Sets the cena value for this Cenovnik.
     * 
     * @param cena
     */
    public void setCena(java.lang.Long cena) {
        this.cena = cena;
    }


    /**
     * Gets the datumDo value for this Cenovnik.
     * 
     * @return datumDo
     */
    public java.util.Calendar getDatumDo() {
        return datumDo;
    }


    /**
     * Sets the datumDo value for this Cenovnik.
     * 
     * @param datumDo
     */
    public void setDatumDo(java.util.Calendar datumDo) {
        this.datumDo = datumDo;
    }


    /**
     * Gets the datumOd value for this Cenovnik.
     * 
     * @return datumOd
     */
    public java.util.Calendar getDatumOd() {
        return datumOd;
    }


    /**
     * Sets the datumOd value for this Cenovnik.
     * 
     * @param datumOd
     */
    public void setDatumOd(java.util.Calendar datumOd) {
        this.datumOd = datumOd;
    }


    /**
     * Gets the id value for this Cenovnik.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this Cenovnik.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Cenovnik)) return false;
        Cenovnik other = (Cenovnik) obj;
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
              this.id.equals(other.getId())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Cenovnik.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/wsdl", "cenovnik"));
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
