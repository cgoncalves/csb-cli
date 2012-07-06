
package pt.it.av.atnog.csb_client.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pt.it.av.atnog.csb_client.entity package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class CsbEntityFactory {

    private final static QName _Service_QNAME = new QName("", "service");
    private final static QName _Runtime_QNAME = new QName("", "runtime");
    private final static QName _Framework_QNAME = new QName("", "framework");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pt.it.av.atnog.csb_client.entity
     * 
     */
    public CsbEntityFactory() {
    }

    /**
     * Create an instance of {@link ApplicationRestartResponse }
     * 
     */
    public ApplicationRestartResponse createApplicationRestartResponse() {
        return new ApplicationRestartResponse();
    }

    /**
     * Create an instance of {@link PaasProviders }
     * 
     */
    public PaasProviders createPaasProviders() {
        return new PaasProviders();
    }

    /**
     * Create an instance of {@link Manifest }
     * 
     */
    public Manifest createManifest() {
        return new Manifest();
    }

    /**
     * Create an instance of {@link PaasProvider }
     * 
     */
    public PaasProvider createPaasProvider() {
        return new PaasProvider();
    }

    /**
     * Create an instance of {@link ManifestResponse }
     * 
     */
    public ManifestResponse createManifestResponse() {
        return new ManifestResponse();
    }

    /**
     * Create an instance of {@link Runtimes }
     * 
     */
    public Runtimes createRuntimes() {
        return new Runtimes();
    }

    /**
     * Create an instance of {@link ApplicationDeleteResponse }
     * 
     */
    public ApplicationDeleteResponse createApplicationDeleteResponse() {
        return new ApplicationDeleteResponse();
    }

    /**
     * Create an instance of {@link DeployApp }
     * 
     */
    public DeployApp createDeployApp() {
        return new DeployApp();
    }

    /**
     * Create an instance of {@link Services }
     * 
     */
    public Services createServices() {
        return new Services();
    }

    /**
     * Create an instance of {@link ApplicationStartResponse }
     * 
     */
    public ApplicationStartResponse createApplicationStartResponse() {
        return new ApplicationStartResponse();
    }

    /**
     * Create an instance of {@link ApplicationStopResponse }
     * 
     */
    public ApplicationStopResponse createApplicationStopResponse() {
        return new ApplicationStopResponse();
    }

    /**
     * Create an instance of {@link ApplicationScaleResponse }
     * 
     */
    public ApplicationScaleResponse createApplicationScaleResponse() {
        return new ApplicationScaleResponse();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link ApplicationStatusResponse }
     * 
     */
    public ApplicationStatusResponse createApplicationStatusResponse() {
        return new ApplicationStatusResponse();
    }

    /**
     * Create an instance of {@link ACMLog }
     * 
     */
    public ACMLog createACMLog() {
        return new ACMLog();
    }

    /**
     * Create an instance of {@link ApplicationCreateResponse }
     * 
     */
    public ApplicationCreateResponse createApplicationCreateResponse() {
        return new ApplicationCreateResponse();
    }

    /**
     * Create an instance of {@link ACMCommit }
     * 
     */
    public ACMCommit createACMCommit() {
        return new ACMCommit();
    }

    /**
     * Create an instance of {@link Frameworks }
     * 
     */
    public Frameworks createFrameworks() {
        return new Frameworks();
    }

    /**
     * Create an instance of {@link ApplicationDeployResponse }
     * 
     */
    public ApplicationDeployResponse createApplicationDeployResponse() {
        return new ApplicationDeployResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "service")
    public JAXBElement<String> createService(String value) {
        return new JAXBElement<String>(_Service_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "runtime")
    public JAXBElement<String> createRuntime(String value) {
        return new JAXBElement<String>(_Runtime_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "framework")
    public JAXBElement<String> createFramework(String value) {
        return new JAXBElement<String>(_Framework_QNAME, String.class, null, value);
    }

}
