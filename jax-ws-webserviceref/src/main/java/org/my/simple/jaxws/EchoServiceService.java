package org.my.simple.jaxws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 */
@WebServiceClient(name = "EchoServiceService", targetNamespace = "http://my.org/simple", wsdlLocation = "http://localhost:8080/echo_service/EchoService?wsdl")
public class EchoServiceService
        extends Service
{

    private final static URL ECHOSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(EchoServiceService.class.getName());

    static
    {
        URL url = null;
        try
        {
            URL baseUrl;
            baseUrl = EchoServiceService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/echo_service/EchoService?wsdl");
        }
        catch (MalformedURLException e)
        {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/echo_service/EchoService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        ECHOSERVICESERVICE_WSDL_LOCATION = url;
    }

    public EchoServiceService(URL wsdlLocation, QName serviceName)
    {
        super(wsdlLocation, serviceName);
    }

    public EchoServiceService()
    {
        super(ECHOSERVICESERVICE_WSDL_LOCATION, new QName("http://my.org/simple", "EchoServiceService"));
    }

    /**
     * @return returns EchoServiceImpl
     */
    @WebEndpoint(name = "EchoServicePort")
    public EchoService getEchoServicePort()
    {
        return super.getPort(new QName("http://my.org/simple", "EchoServicePort"), EchoService.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns EchoServiceImpl
     */
    @WebEndpoint(name = "EchoServicePort")
    public EchoService getEchoServicePort(WebServiceFeature... features)
    {
        return super.getPort(new QName("http://my.org/simple", "EchoServicePort"), EchoService.class, features);
    }

}
