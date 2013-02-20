package org.my.handler.client;

import org.my.handler.jaxws.HandlerService;
import org.my.handler.jaxws.HandlerServiceService;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

public class HandlerClient implements IHandlerService
{
    public String sayHello(String msg)
    {
        try
        {
            HandlerServiceService handlerService = new HandlerServiceService(new URL("http://localhost:8080/echo_service/HandlerService?wsdl"),
                    new QName("http://my.org/simple", "HandlerServiceService"));
            HandlerService handler = handlerService.getHandlerServicePort();
            return handler.sayHello(msg);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
