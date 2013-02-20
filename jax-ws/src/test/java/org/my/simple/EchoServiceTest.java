package org.my.simple;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.simple.client.EchoClient;
import org.my.simple.jaxws.EchoService;
import org.my.simple.jaxws.EchoServiceService;
import org.my.simple.server.MyException;
import javax.inject.Inject;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceRef;

@RunWith(Arquillian.class)
public class EchoServiceTest
{
    @Inject
    public EchoClient echoClient;

    @WebServiceRef(wsdlLocation="http://localhost:8081/echo_service/EchoService?wsdl")
    static EchoServiceService echoService;

    @Deployment
    public static WebArchive createTestArchive()
    {
        return ShrinkWrap.create(WebArchive.class, "echo_service.war")
                .addPackages(true, "org.my")
                .addAsResource("client-handlers.xml")
                .addAsResource("server-handlers.xml")
                .addAsWebInfResource("web.xml", "web.xml")
                .addAsWebInfResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
    }

    @Test
    public void echoTest() throws Exception
    {
        Assert.assertEquals("Hello Paul", echoClient.sayHello("Paul"));
    }

    @Test
    public void echoTestInjected() throws Exception
    {
        EchoService echoPort = echoService.getEchoServicePort();
        BindingProvider bp;
        Service s;
        Assert.assertEquals("Hello Paul", echoPort.sayHello("Paul"));
    }

    @Test(expected = MyException.class)
    public void echoTestException() throws Exception
    {
        try
        {
            echoClient.sayHello("");
        }
        catch (MyException e)
        {
            throw e;
        }
    }

/*    @Test
    public void echoTest() throws Exception
    {
        HandlerServiceService echoService = new HandlerServiceService(new URL("http://localhost:8080/echo_service/HandlerService?wsdl"),
                new QName("http://my.org/simple", "HandlerServiceService"));
        HandlerService echo = echoService.getHandlerServicePort();
        echo.sayHello("Paul");
    }*/

    /*   @Test
    public void clientHandlerTest() throws Exception
    {
        URL wsdlLocation = new URL("http://localhost:8080/echo_service/HandlerService?wsdl");
        QName serviceName = new QName("http://my.org/simple", "HandlerServiceService");
        QName portName = new QName("http://my.org/simple", "EchoServicePort");

        Service service = Service.create(wsdlLocation, serviceName);
        HandlerService echoService = service.getPort(portName, HandlerService.class);

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
        URL wsdlLocation = new URL("http://localhost:8080/echo_service/HandlerService?wsdl");
        QName serviceName = new QName("http://my.org/simple", "HandlerServiceService");
        QName portName = new QName("http://my.org/simple", "EchoServicePort");

        Service service = Service.create(wsdlLocation, serviceName);
        //HandlerService echoService = service.getPort(portName, HandlerService.class);
        HandlerService echoService = service.getPort(HandlerService.class);

        echoService.sayHello("paul");
    }

    }*/
}
