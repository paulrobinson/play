package org.my;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.simple.client.EchoClient;
import org.my.simple.client.IEchoService;
import org.my.simple.jaxws.EchoService;
import org.my.simple.jaxws.EchoServiceService;
import org.my.simple.server.EchoServiceImpl;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class EchoServiceTest
{
    @Inject
    public EchoClient echoClient;

    @Deployment
    public static JavaArchive createTestArchive()
    {
        return ShrinkWrap.create(JavaArchive.class, "echo_service.jar")
                .addClasses(IEchoService.class)
                .addClasses(EchoService.class)
                .addClasses(EchoServiceService.class)
                .addClasses(EchoClient.class)
                .addClasses(EchoServiceImpl.class)
                .addAsManifestResource(
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

    }*/
}
