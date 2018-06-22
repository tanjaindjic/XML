/**
 * Rezervacija.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xml.agent.xmlWeb_smestaj.smestajSoap.model;

import java.util.Calendar;


public class Rezervacija  implements java.io.Serializable {
    private Calendar datumDo;

    private Calendar datumOd;

    private java.lang.Long id;

    private int ocena;

    private boolean ocenio;

    private Korisnik rezervisao;

    private java.lang.Object smestaj;

    private Soba soba;

    private StatusRezevacije status;

    private int version;

    public Rezervacija() {
    }

    public Rezervacija(
           Calendar datumDo,
           Calendar datumOd,
           java.lang.Long id,
           int ocena,
           boolean ocenio,
           Korisnik rezervisao,
           java.lang.Object smestaj,
           Soba soba,
           StatusRezevacije status,
           int version) {
           this.datumDo = datumDo;
           this.datumOd = datumOd;
           this.id = id;
           this.ocena = ocena;
           this.ocenio = ocenio;
           this.rezervisao = rezervisao;
           this.smestaj = smestaj;
           this.soba = soba;
           this.status = status;
           this.version = version;
    }


    /**
     * Gets the datumDo value for this Rezervacija.
     * 
     * @return datumDo
     */
    public Calendar getDatumDo() {
        return datumDo;
    }


    /**
     * Sets the datumDo value for this Rezervacija.
     * 
     * @param datumDo
     */
    public void setDatumDo(Calendar datumDo) {
        this.datumDo = datumDo;
    }


    /**
     * Gets the datumOd value for this Rezervacija.
     * 
     * @return datumOd
     */
    public Calendar getDatumOd() {
        return datumOd;
    }


    /**
     * Sets the datumOd value for this Rezervacija.
     * 
     * @param datumOd
     */
    public void setDatumOd(Calendar datumOd) {
        this.datumOd = datumOd;
    }


    /**
     * Gets the id value for this Rezervacija.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this Rezervacija.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the ocena value for this Rezervacija.
     * 
     * @return ocena
     */
    public int getOcena() {
        return ocena;
    }


    /**
     * Sets the ocena value for this Rezervacija.
     * 
     * @param ocena
     */
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }


    /**
     * Gets the ocenio value for this Rezervacija.
     * 
     * @return ocenio
     */
    public boolean isOcenio() {
        return ocenio;
    }


    /**
     * Sets the ocenio value for this Rezervacija.
     * 
     * @param ocenio
     */
    public void setOcenio(boolean ocenio) {
        this.ocenio = ocenio;
    }


    /**
     * Gets the rezervisao value for this Rezervacija.
     * 
     * @return rezervisao
     */
    public Korisnik getRezervisao() {
        return rezervisao;
    }


    /**
     * Sets the rezervisao value for this Rezervacija.
     * 
     * @param rezervisao
     */
    public void setRezervisao(Korisnik rezervisao) {
        this.rezervisao = rezervisao;
    }


    /**
     * Gets the smestaj value for this Rezervacija.
     * 
     * @return smestaj
     */
    public java.lang.Object getSmestaj() {
        return smestaj;
    }


    /**
     * Sets the smestaj value for this Rezervacija.
     * 
     * @param smestaj
     */
    public void setSmestaj(java.lang.Object smestaj) {
        this.smestaj = smestaj;
    }


    /**
     * Gets the soba value for this Rezervacija.
     * 
     * @return soba
     */
    public Soba getSoba() {
        return soba;
    }


    /**
     * Sets the soba value for this Rezervacija.
     * 
     * @param soba
     */
    public void setSoba(Soba soba) {
        this.soba = soba;
    }


    /**
     * Gets the status value for this Rezervacija.
     * 
     * @return status
     */
    public StatusRezevacije getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Rezervacija.
     * 
     * @param status
     */
    public void setStatus(StatusRezevacije status) {
        this.status = status;
    }


    /**
     * Gets the version value for this Rezervacija.
     * 
     * @return version
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version value for this Rezervacija.
     * 
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Rezervacija)) return false;
        Rezervacija other = (Rezervacija) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datumDo==null && other.getDatumDo()==null) || 
             (this.datumDo!=null &&
              this.datumDo.equals(other.getDatumDo()))) &&
            ((this.datumOd==null && other.getDatumOd()==null) || 
             (this.datumOd!=null &&
              this.datumOd.equals(other.getDatumOd()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            this.ocena == other.getOcena() &&
            this.ocenio == other.isOcenio() &&
            ((this.rezervisao==null && other.getRezervisao()==null) || 
             (this.rezervisao!=null &&
              this.rezervisao.equals(other.getRezervisao()))) &&
            ((this.smestaj==null && other.getSmestaj()==null) || 
             (this.smestaj!=null &&
              this.smestaj.equals(other.getSmestaj()))) &&
            ((this.soba==null && other.getSoba()==null) || 
             (this.soba!=null &&
              this.soba.equals(other.getSoba()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            this.version == other.getVersion();
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
        if (getDatumDo() != null) {
            _hashCode += getDatumDo().hashCode();
        }
        if (getDatumOd() != null) {
            _hashCode += getDatumOd().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        _hashCode += getOcena();
        _hashCode += (isOcenio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getRezervisao() != null) {
            _hashCode += getRezervisao().hashCode();
        }
        if (getSmestaj() != null) {
            _hashCode += getSmestaj().hashCode();
        }
        if (getSoba() != null) {
            _hashCode += getSoba().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        _hashCode += getVersion();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Rezervacija.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "rezervacija"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("ocena");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ocena"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ocenio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ocenio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rezervisao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rezervisao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "korisnik"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smestaj");
        elemField.setXmlName(new javax.xml.namespace.QName("", "smestaj"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soba");
        elemField.setXmlName(new javax.xml.namespace.QName("", "soba"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "soba"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "statusRezevacije"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
