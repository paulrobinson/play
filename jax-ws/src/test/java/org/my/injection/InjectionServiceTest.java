package org.my.injection;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.injection.client.EchoClient;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class InjectionServiceTest
{
    @Inject
    public EchoClient echoClient;

    @Deployment
    public static WebArchive createTestArchive()
    {
        return ShrinkWrap.create(WebArchive.class, "echo_service.war")
                .addPackages(true, "org.my.injection")
                .addAsWebInfResource("web-injection.xml", "web.xml")
                .addAsWebInfResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
    }

    @Test
    public void echoTest() throws Exception
    {
        Assert.assertEquals("Hello Paul", echoClient.sayHello("Paul"));
    }

}
