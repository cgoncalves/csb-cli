
package pt.it.av.atnog.csb_client.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paasProvider complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paasProvider">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="runtimes" type="{}runtimes" minOccurs="0"/>
 *         &lt;element name="frameworks" type="{}frameworks" minOccurs="0"/>
 *         &lt;element name="services" type="{}services" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="score" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paasProvider", propOrder = {
    "runtimes",
    "frameworks",
    "services"
})
@XmlRootElement(name = "paasProvider")
public class PaasProvider {

    protected Runtimes runtimes;
    protected Frameworks frameworks;
    protected Services services;
    @XmlAttribute(required = true)
    protected String name;
    @XmlAttribute
    protected Integer score;

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the score property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Sets the value of the score property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setScore(Integer value) {
        this.score = value;
    }

}
