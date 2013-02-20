package org.my;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.common.BenchmarkInterceptorImpl;
import org.my.ejb.client.EchoClient;

@RunWith(Arquillian.class)
public class EchoServiceEJBTest {

    @Deployment
    public static JavaArchive createTestArchive() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "echo_service.jar")
                .addPackages(true, "org.my")
                .addAsResource("server-handlers.xml")
                .addAsManifestResource(new ByteArrayAsset("<interceptors><class>org.my.ejb.server.MyCDIIntercepter</class></interceptors>".getBytes()), ArchivePaths.create("beans.xml"));

        /*
         * Remove the default MANIFEST.MF and replace with one that contains the required dependencies.
         */
        archive.delete(ArchivePaths.create("META-INF/MANIFEST.MF"));
        String ManifestMF = "Manifest-Version: 1.0\n"
                + "Dependencies: org.apache.cxf\n";
        archive.setManifest(new StringAsset(ManifestMF));

        return archive;
    }

    @Before
    public void setupTest() {
        BenchmarkInterceptorImpl.reset();
    }

    @Test
    public void echoTestIntercepted() throws Exception {

        EchoClient echoClient = new EchoClient();
        Assert.assertEquals("Hello Paul", echoClient.sayHello("Paul"));
        Assert.assertEquals(1, BenchmarkInterceptorImpl.setupCount);
        Assert.assertEquals(1, BenchmarkInterceptorImpl.proceedCount);
        Assert.assertEquals(0, BenchmarkInterceptorImpl.notifyFailureCount);
        Assert.assertEquals(1, BenchmarkInterceptorImpl.notifySuccessCount);
    }

    @Test
    public void echoTestNotIntercepted() throws Exception {

        EchoClient echoClient = new EchoClient();
        Assert.assertEquals("Hello Paul", echoClient.sayHello2("Paul"));
        Assert.assertEquals(0, BenchmarkInterceptorImpl.setupCount);
        Assert.assertEquals(0, BenchmarkInterceptorImpl.proceedCount);
        Assert.assertEquals(0, BenchmarkInterceptorImpl.notifyFailureCount);
        Assert.assertEquals(0, BenchmarkInterceptorImpl.notifySuccessCount);
    }

    @Test(expected = RuntimeException.class)
    public void echoTestFailure() throws Exception {
        try {
            EchoClient echoClient = new EchoClient();
            echoClient.sayHello("");
        } finally {
            Assert.assertEquals(1, BenchmarkInterceptorImpl.setupCount);
            Assert.assertEquals(1, BenchmarkInterceptorImpl.proceedCount);
            Assert.assertEquals(1, BenchmarkInterceptorImpl.notifyFailureCount);
            Assert.assertEquals(0, BenchmarkInterceptorImpl.notifySuccessCount);
        }
    }

}
