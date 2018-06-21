/**
 * Authority.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xmlWeb_smestaj.smestajSoap;

public class Authority  implements java.io.Serializable {
    private java.lang.Long id;

    private xmlWeb_smestaj.smestajSoap.AuthorityName name;

    private xmlWeb_smestaj.smestajSoap.Permission[] permissions;

    private xmlWeb_smestaj.smestajSoap.Korisnik[] users;

    public Authority() {
    }

    public Authority(
           java.lang.Long id,
           xmlWeb_smestaj.smestajSoap.AuthorityName name,
           xmlWeb_smestaj.smestajSoap.Permission[] permissions,
           xmlWeb_smestaj.smestajSoap.Korisnik[] users) {
           this.id = id;
           this.name = name;
           this.permissions = permissions;
           this.users = users;
    }


    /**
     * Gets the id value for this Authority.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this Authority.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the name value for this Authority.
     * 
     * @return name
     */
    public xmlWeb_smestaj.smestajSoap.AuthorityName getName() {
        return name;
    }


    /**
     * Sets the name value for this Authority.
     * 
     * @param name
     */
    public void setName(xmlWeb_smestaj.smestajSoap.AuthorityName name) {
        this.name = name;
    }


    /**
     * Gets the permissions value for this Authority.
     * 
     * @return permissions
     */
    public xmlWeb_smestaj.smestajSoap.Permission[] getPermissions() {
        return permissions;
    }


    /**
     * Sets the permissions value for this Authority.
     * 
     * @param permissions
     */
    public void setPermissions(xmlWeb_smestaj.smestajSoap.Permission[] permissions) {
        this.permissions = permissions;
    }

    public xmlWeb_smestaj.smestajSoap.Permission getPermissions(int i) {
        return this.permissions[i];
    }

    public void setPermissions(int i, xmlWeb_smestaj.smestajSoap.Permission _value) {
        this.permissions[i] = _value;
    }


    /**
     * Gets the users value for this Authority.
     * 
     * @return users
     */
    public xmlWeb_smestaj.smestajSoap.Korisnik[] getUsers() {
        return users;
    }


    /**
     * Sets the users value for this Authority.
     * 
     * @param users
     */
    public void setUsers(xmlWeb_smestaj.smestajSoap.Korisnik[] users) {
        this.users = users;
    }

    public xmlWeb_smestaj.smestajSoap.Korisnik getUsers(int i) {
        return this.users[i];
    }

    public void setUsers(int i, xmlWeb_smestaj.smestajSoap.Korisnik _value) {
        this.users[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Authority)) return false;
        Authority other = (Authority) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.permissions==null && other.getPermissions()==null) || 
             (this.permissions!=null &&
              java.util.Arrays.equals(this.permissions, other.getPermissions()))) &&
            ((this.users==null && other.getUsers()==null) || 
             (this.users!=null &&
              java.util.Arrays.equals(this.users, other.getUsers())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPermissions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPermissions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPermissions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsers(), i);
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
        new org.apache.axis.description.TypeDesc(Authority.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "authority"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "authorityName"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permissions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "permission"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("users");
        elemField.setXmlName(new javax.xml.namespace.QName("", "users"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlWeb-smestaj/smestajSoap", "korisnik"));
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
