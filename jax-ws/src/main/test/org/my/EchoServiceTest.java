package org.my;

import org.junit.After;
import org.junit.Test;
import org.my.jaxws.EchoService;
import org.my.jaxws.EchoServiceService;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EchoServiceTest
{
    @Test
    public void echoTest() throws Exception
    {
        EchoServiceService echoService = new EchoServiceService(new URL("http://localhost:8080/echo_service/EchoService?wsdl"),
                new QName("http://my.org/simple", "EchoServiceService"));
        EchoService echo = echoService.getEchoServicePort();
        echo.sayHello("Paul");
    }

    @Test
    public void clientHandlerTest() throws Exception
    {
        URL wsdlLocation = new URL("http://localhost:8080/echo_service/EchoService?wsdl");
        QName serviceName = new QName("http://my.org/simple", "EchoServiceService");
        QName portName = new QName("http://my.org/simple", "EchoServicePort");

        Service service = Service.create(wsdlLocation, serviceName);
        EchoService echoService = service.getPort(portName, EchoService.class);

        // we could have used @HandlerChain but it's nice to show a bit of variety...
        BindingProvider bindingProvider = (BindingProvider) echoService;
        List<Handler> handlers = new ArrayList<Handler>(1);
        handlers.add(new MyClientHandler());
        bindingProvider.getBinding().setHandlerChain(handlers);

        echoService.sayHello("paul");
    }


    @Test
    public void clientHandlerTestAnotations() throws Exception
    {
        URL wsdlLocation = new URL("http://localhost:8080/echo_service/EchoService?wsdl");
        QName serviceName = new QName("http://my.org/simple", "EchoServiceService");
        QName portName = new QName("http://my.org/simple", "EchoServicePort");

        Service service = Service.create(wsdlLocation, serviceName);
        //EchoService echoService = service.getPort(portName, EchoService.class);
        EchoService echoService = service.getPort(EchoService.class);

        echoService.sayHello("paul");
    }

    @After
    public void after()
    {

    }
}
