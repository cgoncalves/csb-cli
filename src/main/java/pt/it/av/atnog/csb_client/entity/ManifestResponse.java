
package pt.it.av.atnog.csb_client.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaasProviders" type="{}PaasProviders" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "paasProviders"
})
@XmlRootElement(name = "ManifestResponse")
public class ManifestResponse {

    @XmlElement(name = "PaasProviders")
    protected PaasProviders paasProviders;

    /**
     * Gets the value of the paasProviders property.
     * 
     * @return
     *     possible object is
     *     {@link PaasProviders }
     *     
     */
    public PaasProviders getPaasProviders() {
        return paasProviders;
    }

    /**
     * Sets the value of the paasProviders property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaasProviders }
     *     
     */
    public void setPaasProviders(PaasProviders value) {
        this.paasProviders = value;
    }

}
