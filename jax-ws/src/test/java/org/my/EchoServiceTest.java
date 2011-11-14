package org.my;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.client.EchoClient;
import org.my.client.IEchoService;
import org.my.client.MyClientHandler;
import org.my.jaxws.EchoService;
import org.my.jaxws.EchoServiceService;
import org.my.server.EchoServiceImpl;
import org.my.server.MyServerHandler;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class EchoServiceTest
{
    @Inject
    public EchoClient echoClient;

    @Deployment
    public static WebArchive createTestArchive()
    {
        return ShrinkWrap.create(WebArchive.class, "echo_service.war")
                .addClasses(IEchoService.class)
                .addClasses(EchoService.class)
                .addClasses(EchoServiceService.class)
                .addClasses(EchoClient.class)
                .addClasses(EchoServiceImpl.class)
                .addClasses(MyClientHandler.class)
                .addClasses(MyServerHandler.class)
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

/*    @Test
    public void echoTest() throws Exception
    {
        EchoServiceService echoService = new EchoServiceService(new URL("http://localhost:8080/echo_service/EchoService?wsdl"),
                new QName("http://my.org/simple", "EchoServiceService"));
        EchoService echo = echoService.getEchoServicePort();
        echo.sayHello("Paul");
    }*/

 /*   @Test
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

    }*/
}
