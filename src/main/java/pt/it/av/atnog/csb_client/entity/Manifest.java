
package pt.it.av.atnog.csb_client.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="runtimes" type="{}runtimes"/>
 *         &lt;element name="frameworks" type="{}frameworks"/>
 *         &lt;element name="services" type="{}services"/>
 *       &lt;/sequence>
 *       &lt;attribute name="provider" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "runtimes",
    "frameworks",
    "services"
})
@XmlRootElement(name = "Manifest")
public class Manifest {

    @XmlElement(required = true)
    protected Runtimes runtimes;
    @XmlElement(required = true)
    protected Frameworks frameworks;
    @XmlElement(required = true)
    protected Services services;
    @XmlAttribute
    protected String provider;

    /**
     * Gets the value of the runtimes property.
     * 
     * @return
     *     possible object is
     *     {@link Runtimes }
     *     
     */
    public Runtimes getRuntimes() {
        return runtimes;
    }

    /**
     * Sets the value of the runtimes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Runtimes }
     *     
     */
    public void setRuntimes(Runtimes value) {
        this.runtimes = value;
    }

    /**
     * Gets the value of the frameworks property.
     * 
     * @return
     *     possible object is
     *     {@link Frameworks }
     *     
     */
    public Frameworks getFrameworks() {
        return frameworks;
    }

    /**
     * Sets the value of the frameworks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Frameworks }
     *     
     */
    public void setFrameworks(Frameworks value) {
        this.frameworks = value;
    }

    /**
     * Gets the value of the services property.
     * 
     * @return
     *     possible object is
     *     {@link Services }
     *     
     */
    public Services getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link Services }
     *     
     */
    public void setServices(Services value) {
        this.services = value;
    }

    /**
     * Gets the value of the provider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Sets the value of the provider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvider(String value) {
        this.provider = value;
    }

}
