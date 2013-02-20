package org.my.simple.client;

import org.my.simple.jaxws.EchoService;
import org.my.simple.jaxws.EchoServiceService;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

public class EchoClient implements IEchoService
{
    public String sayHello(String msg)
    {
        try
        {
            EchoServiceService echoService = new EchoServiceService(new URL("http://localhost:8080/echo_service/EchoServiceService/EchoServiceImpl?wsdl"),
                    new QName("http://my.org/simple", "EchoServiceService"));
            EchoService echo = echoService.getEchoServicePort();
            return echo.sayHello("Paul");
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
