package org.my.simple.client;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;
import java.net.MalformedURLException;
import java.net.URL;
import org.my.simple.jaxws.*;

public class EchoClient implements IEchoService
{

    @WebServiceRef(name="EchoServiceService")
    //@ATClient
    public EchoServiceService echoService;

    public String sayHello(String msg)
    {
        try
        {
            EchoServiceService echoService = new EchoServiceService(new URL("http://localhost:8080/echo_service/EchoService?wsdl"),
                    new QName("http://my.org/simple", "EchoServiceService"));
            EchoService echo = echoService.getEchoServicePort();
            return echo.sayHello(msg);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String sayHello2(String msg)
    {
        return echoService.getEchoServicePort().sayHello(msg);
    }
}
