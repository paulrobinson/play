package org.my.interceptor;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.interceptor.client.EchoClient;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class interceptorServiceTest
{
    @Inject
    public EchoClient echoClient;

    @Deployment
    public static WebArchive createTestArchive()
    {
        return ShrinkWrap.create(WebArchive.class, "echo_service.war")
                .addPackages(true, "org.my.interceptor")
                .addAsWebInfResource("web-interceptor.xml", "web.xml")
                .addAsWebInfResource(new ByteArrayAsset("<interceptors><class>org.my.interceptor.BenchmarkInterceptor</class></interceptors>".getBytes()), ArchivePaths.create("beans.xml"));
    }

    @Test
    public void echoTest() throws Exception
    {
        Assert.assertEquals("Hello Paul", echoClient.sayHello("Paul"));
        Assert.assertTrue(BenchmarkInterceptor.result >= 0);
    }

}
