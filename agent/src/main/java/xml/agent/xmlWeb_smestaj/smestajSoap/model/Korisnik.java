/**
 * Korisnik.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xml.agent.xmlWeb_smestaj.smestajSoap.model;

import xml.agent.xmlWeb_smestaj.smestajSoap.security.Authority;

public class Korisnik  implements java.io.Serializable {
    private java.lang.String adresa;

    private boolean aktiviran;

    private Authority[] authorities;

    private java.lang.String confirmationToken;

    private java.lang.String email;

    private java.lang.String firstName;

    private java.lang.Long id;

    private java.lang.Object[] izdaje;

    private java.lang.String lastName;

    private java.util.Calendar lastPasswordResetDate;

    private java.lang.String PIB;

    private java.lang.String password;

    private Rezervacija[] rezervacije;

    private Role role;

    private StatusKorisnika statusNaloga;

    private java.lang.String username;

    public Korisnik() {
    }

    public Korisnik(
           java.lang.String adresa,
           boolean aktiviran,
           Authority[] authorities,
           java.lang.String confirmationToken,
           java.lang.String email,
           java.lang.String firstName,
           java.lang.Long id,
           java.lang.Object[] izdaje,
           java.lang.String lastName,
           java.util.Calendar lastPasswordResetDate,
           java.lang.String PIB,
           java.lang.String password,
           Rezervacija[] rezervacije,
           Role role,
           StatusKorisnika statusNaloga,
           java.lang.String username) {
           this.adresa = adresa;
           this.aktiviran = aktiviran;
           this.authorities = authorities;
           this.confirmationToken = confirmationToken;
           this.email = email;
           this.firstName = firstName;
           this.id = id;
           this.izdaje = izdaje;
           this.lastName = lastName;
           this.lastPasswordResetDate = lastPasswordResetDate;
           this.PIB = PIB;
           this.password = password;
           this.rezervacije = rezervacije;
           this.role = role;
           this.statusNaloga = statusNaloga;
           this.username = username;
    }


    /**
     * Gets the adresa value for this Korisnik.
     * 
     * @return adresa
     */
    public java.lang.String getAdresa() {
        return adresa;
    }


    /**
     * Sets the adresa value for this Korisnik.
     * 
     * @param adresa
     */
    public void setAdresa(java.lang.String adresa) {
        this.adresa = adresa;
    }


    /**
     * Gets the aktiviran value for this Korisnik.
     * 
     * @return aktiviran
     */
    public boolean isAktiviran() {
        return aktiviran;
    }


    /**
     * Sets the aktiviran value for this Korisnik.
     * 
     * @param aktiviran
     */
    public void setAktiviran(boolean aktiviran) {
        this.aktiviran = aktiviran;
    }


    /**
     * Gets the authorities value for this Korisnik.
     * 
     * @return authorities
     */
    public Authority[] getAuthorities() {
        return authorities;
    }


    /**
     * Sets the authorities value for this Korisnik.
     * 
     * @param authorities
     */
    public void setAuthorities(Authority[] authorities) {
        this.authorities = authorities;
    }

    public Authority getAuthorities(int i) {
        return this.authorities[i];
    }

    public void setAuthorities(int i, Authority _value) {
        this.authorities[i] = _value;
    }


    /**
     * Gets the confirmationToken value for this Korisnik.
     * 
     * @return confirmationToken
     */
    public java.lang.String getConfirmationToken() {
        return confirmationToken;
    }


    /**
     * Sets the confirmationToken value for this Korisnik.
     * 
     * @param confirmationToken
     */
    public void setConfirmationToken(java.lang.String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }


    /**
     * Gets the email value for this Korisnik.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Korisnik.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the firstName value for this Korisnik.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this Korisnik.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the id value for this Korisnik.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this Korisnik.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the izdaje value for this Korisnik.
     * 
     * @return izdaje
     */
    public java.lang.Object[] getIzdaje() {
        return izdaje;
    }


    /**
     * Sets the izdaje value for this Korisnik.
     * 
     * @param izdaje
     */
    public void setIzdaje(java.lang.Object[] izdaje) {
        this.izdaje = izdaje;
    }

    public java.lang.Object getIzdaje(int i) {
        return this.izdaje[i];
    }

    public void setIzdaje(int i, java.lang.Object _value) {
        this.izdaje[i] = _value;
    }


    /**
     * Gets the lastName value for this Korisnik.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this Korisnik.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the lastPasswordResetDate value for this Korisnik.
     * 
     * @return lastPasswordResetDate
     */
    public java.util.Calendar getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }


    /**
     * Sets the lastPasswordResetDate value for this Korisnik.
     * 
     * @param lastPasswordResetDate
     */
    public void setLastPasswordResetDate(java.util.Calendar lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }


    /**
     * Gets the PIB value for this Korisnik.
     * 
     * @return PIB
     */
    public java.lang.String getPIB() {
        return PIB;
    }


    /**
     * Sets the PIB value for this Korisnik.
     * 
     * @param PIB
     */
    public void setPIB(java.lang.String PIB) {
        this.PIB = PIB;
    }


    /**
     * Gets the password value for this Korisnik.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this Korisnik.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the rezervacije value for this Korisnik.
     * 
     * @return rezervacije
     */
    public Rezervacija[] getRezervacije() {
        return rezervacije;
    }


    /**
     * Sets the rezervacije value for this Korisnik.
     * 
     * @param rezervacije
     */
    public void setRezervacije(Rezervacija[] rezervacije) {
        this.rezervacije = rezervacije;
    }

    public Rezervacija getRezervacije(int i) {
        return this.rezervacije[i];
    }

    public void setRezervacije(int i, Rezervacija _value) {
        this.rezervacije[i] = _value;
    }


    /**
     * Gets the role value for this Korisnik.
     * 
     * @return role
     */
    public Role getRole() {
        return role;
    }


    /**
     * Sets the role value for this Korisnik.
     * 
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }


    /**
     * Gets the statusNaloga value for this Korisnik.
     * 
     * @return statusNaloga
     */
    public StatusKorisnika getStatusNaloga() {
        return statusNaloga;
    }


    /**
     * Sets the statusNaloga value for this Korisnik.
     * 
     * @param statusNaloga
     */
    public void setStatusNaloga(StatusKorisnika statusNaloga) {
        this.statusNaloga = statusNaloga;
    }


    /**
     * Gets the username value for this Korisnik.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this Korisnik.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Korisnik)) return false;
        Korisnik other = (Korisnik) obj;
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
            this.aktiviran == other.isAktiviran() &&
            ((this.authorities==null && other.getAuthorities()==null) || 
             (this.authorities!=null &&
              java.util.Arrays.equals(this.authorities, other.getAuthorities()))) &&
            ((this.confirmationToken==null && other.getConfirmationToken()==null) || 
             (this.confirmationToken!=null &&
              this.confirmationToken.equals(other.getConfirmationToken()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.izdaje==null && other.getIzdaje()==null) || 
             (this.izdaje!=null &&
              java.util.Arrays.equals(this.izdaje, other.getIzdaje()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.lastPasswordResetDate==null && other.getLastPasswordResetDate()==null) || 
             (this.lastPasswordResetDate!=null &&
              this.lastPasswordResetDate.equals(other.getLastPasswordResetDate()))) &&
            ((this.PIB==null && other.getPIB()==null) || 
             (this.PIB!=null &&
              this.PIB.equals(other.getPIB()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.rezervacije==null && other.getRezervacije()==null) || 
             (this.rezervacije!=null &&
              java.util.Arrays.equals(this.rezervacije, other.getRezervacije()))) &&
            ((this.role==null && other.getRole()==null) || 
             (this.role!=null &&
              this.role.equals(other.getRole()))) &&
            ((this.statusNaloga==null && other.getStatusNaloga()==null) || 
             (this.statusNaloga!=null &&
              this.statusNaloga.equals(other.getStatusNaloga()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername())));
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
        _hashCode += (isAktiviran() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAuthorities() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthorities());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthorities(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getConfirmationToken() != null) {
            _hashCode += getConfirmationToken().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIzdaje() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIzdaje());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIzdaje(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getLastPasswordResetDate() != null) {
            _hashCode += getLastPasswordResetDate().hashCode();
        }
        if (getPIB() != null) {
            _hashCode += getPIB().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getRezervacije() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRezervacije());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRezervacije(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRole() != null) {
            _hashCode += getRole().hashCode();
        }
        if (getStatusNaloga() != null) {
            _hashCode += getStatusNaloga().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Korisnik.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "korisnik"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aktiviran");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aktiviran"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorities");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authorities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "authority"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirmationToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confirmationToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firstName"));
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
        elemField.setFieldName("izdaje");
        elemField.setXmlName(new javax.xml.namespace.QName("", "izdaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastPasswordResetDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastPasswordResetDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PIB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PIB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rezervacije");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rezervacije"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "rezervacija"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("role");
        elemField.setXmlName(new javax.xml.namespace.QName("", "role"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "role"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusNaloga");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusNaloga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "statusKorisnika"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
