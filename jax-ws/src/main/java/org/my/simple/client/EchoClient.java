package org.my.simple.client;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import org.my.simple.server.*;
import org.my.simple.jaxws.*;

public class EchoClient implements IEchoService
{
    public String sayHello(String msg) throws MyException
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
}
