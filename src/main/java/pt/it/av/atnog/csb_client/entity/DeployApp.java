
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
 *         &lt;element name="paasProvider" type="{}paasProvider"/>
 *         &lt;element name="AppID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "paasProvider",
    "appID"
})
@XmlRootElement(name = "DeployApp")
public class DeployApp {

    @XmlElement(required = true)
    protected PaasProvider paasProvider;
    @XmlElement(name = "AppID", required = true)
    protected String appID;

    /**
     * Gets the value of the paasProvider property.
     * 
     * @return
     *     possible object is
     *     {@link PaasProvider }
     *     
     */
    public PaasProvider getPaasProvider() {
        return paasProvider;
    }

    /**
     * Sets the value of the paasProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaasProvider }
     *     
     */
    public void setPaasProvider(PaasProvider value) {
        this.paasProvider = value;
    }

    /**
     * Gets the value of the appID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppID() {
        return appID;
    }

    /**
     * Sets the value of the appID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppID(String value) {
        this.appID = value;
    }

}
