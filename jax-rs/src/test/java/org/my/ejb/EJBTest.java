package org.my.ejb;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @Author paul.robinson@redhat.com 05/04/2012
 */
@RunWith(Arquillian.class)
public class EJBTest {

    @Deployment
    public static WebArchive createTestArchive() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "jax-rs-1.0.war")
                .addClass(Hello.class)
                .addClass(HelloImpl.class)
                .addClass(MyInterceptor.class)
                .addClass(MyInterceptorImpl.class)
                .addClass(RestClientInterceptor.class)
                .addAsWebInfResource("web.xml")
                .addAsManifestResource(
                        new ByteArrayAsset("<interceptors><class>org.my.ejb.MyInterceptorImpl</class></interceptors>".getBytes()),
                        ArchivePaths.create("beans.xml"));
        archive.delete(ArchivePaths.create("META-INF/MANIFEST.MF"));

        String ManifestMF = "Manifest-Version: 1.0\n"
                + "Dependencies: org.jboss.resteasy.resteasy-jaxrs,javax.ws.rs.api,javax.ejb.api\n";
        archive.setManifest(new StringAsset(ManifestMF));

        return archive;

    }


    @Test
    public void simpleTest() throws Exception {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());

        Hello client = ProxyFactory.create(Hello.class, "http://localhost:8080/jax-rs-1.0/");
        System.out.println(client.sayHello("Paul - remote"));

        Assert.assertTrue(RestClientInterceptor.invoked);
    }
}
