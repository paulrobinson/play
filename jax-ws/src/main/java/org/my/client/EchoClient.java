package org.my.client;

import org.my.client.IEchoService;
import org.my.jaxws.EchoService;
import org.my.jaxws.EchoServiceService;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

public class EchoClient implements IEchoService
{
    @Override
    public String sayHello(String msg)
    {
        try
        {
            EchoServiceService echoService = new EchoServiceService(new URL("http://localhost:8080/echo_service/EchoService?wsdl"),
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
