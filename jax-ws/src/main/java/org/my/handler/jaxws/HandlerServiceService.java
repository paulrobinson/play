package org.my.handler.jaxws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 */
@WebServiceClient(name = "HandlerServiceService", targetNamespace = "http://my.org/simple", wsdlLocation = "http://localhost:8080/handler_service/HandlerServiceImpl?wsdl")
public class HandlerServiceService
        extends Service
{

    private final static URL HANDLERSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(HandlerService.class.getName());

    static
    {
        URL url = null;
        try
        {
            URL baseUrl;
            baseUrl = HandlerService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/handler_service/HandlerServiceImpl?wsdl");
        }
        catch (MalformedURLException e)
        {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/handler_service/HandlerServiceImpl?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        HANDLERSERVICESERVICE_WSDL_LOCATION = url;
    }

    public HandlerServiceService(URL wsdlLocation, QName serviceName)
    {
        super(wsdlLocation, serviceName);
    }

    public HandlerServiceService()
    {
        super(HANDLERSERVICESERVICE_WSDL_LOCATION, new QName("http://my.org/simple", "HandlerServiceService"));
    }

    /**
     * @return returns HandlerServiceImpl
     */
    @WebEndpoint(name = "HandlerServicePort")
    public HandlerService getHandlerServicePort()
    {
        return super.getPort(new QName("http://my.org/simple", "HandlerServicePort"), HandlerService.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns HandlerServiceImpl
     */
    @WebEndpoint(name = "HandlerServicePort")
    public HandlerService getHandlerServicePort(WebServiceFeature... features)
    {
        return super.getPort(new QName("http://my.org/simple", "HandlerServicePort"), HandlerService.class, features);
    }

}