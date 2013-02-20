package org.my.simple.client;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.my.simple.server.*;
import org.my.simple.jaxws.*;

public class EchoClient implements IEchoService
{
    public String sayHello(String msg) throws MyException
    {
        try
        {
            EchoServiceService echoService = new EchoServiceService(new URL("http://localhost:8080/echo_service-1.0/EchoServiceService?wsdl"),
                    new QName("http://my.org/simple", "EchoServiceService"));
            EchoService echo = echoService.getEchoServicePort(new MYFeature());

            BindingProvider bindingProvider = (BindingProvider) echo;
            List<Handler> handlers = new ArrayList<Handler>(1);
            handlers.add(new MyClientHandler());
            bindingProvider.getBinding().setHandlerChain(handlers);

            return echo.sayHello(msg);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception
    {
        EchoClient echoClient = new EchoClient();
        echoClient.sayHello("Hello");
    }
}
