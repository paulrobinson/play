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
import org.my.simple.ejb.MyEJB;
import org.my.simple.jaxws.EchoServiceService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;

@RunWith(Arquillian.class)
public class EchoServiceTest
{
    @Inject
    EchoClient client;

    @EJB
    MyEJB myEJB;

    @WebServiceRef(name="EchoServiceService")
    //@ATClient
    public EchoServiceService echoService;

    @Deployment
    public static WebArchive createTestArchive()
    {
        return ShrinkWrap.create(WebArchive.class, "echo_service.war")
                .addPackages(true, "org.my")
                .addAsManifestResource("jboss-client.xml")
                .addAsManifestResource("application-client.xml")
                .addAsManifestResource("echo.wsdl")
                .addAsWebInfResource("web.xml")
                .addAsWebInfResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
    }

    @Test
    public void echoTest() throws Exception
    {
        //EchoService echoPort = echoService.getEchoServicePort();
        //Assert.assertEquals("Hello Paul", echoPort.sayHello("Paul"));
        //EchoClient client = new EchoClient();
        //Assert.assertEquals("Hello Paul", client.sayHello("Paul"));
        //echoService.getEchoServicePort();
        //Assert.assertEquals("Hello Paul", client.sayHello2("Paul"));
        Assert.assertEquals("Hello Paul", myEJB.sayHello("Paul"));
    }
}
