package org.my.handler;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.handler.client.HandlerClient;
import org.my.simple.server.MyException;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class HandlerServiceTest
{
    @Inject
    public HandlerClient HandlerClient;

    @Deployment
    public static WebArchive createTestArchive()
    {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "echo_service.war")
                .addPackages(true, "org.my")
                .addAsResource("client-handlers.xml")
                .addAsResource("server-handlers.xml")
                .addAsWebInfResource("web.xml", "web.xml")
                .addAsWebInfResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));

        archive.delete(ArchivePaths.create("META-INF/MANIFEST.MF"));

        String ManifestMF = "Manifest-Version: 1.0\n"
                + "Dependencies: org.apache.cxf\n";
        archive.setManifest(new StringAsset(ManifestMF));

        return archive;
    }

    @Test
    public void echoTest() throws Exception
    {
        Assert.assertEquals("Hello Paul", HandlerClient.sayHello("Paul"));
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
