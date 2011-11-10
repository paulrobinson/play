package org.my.cdi.interceptor;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.cdi.producer.FromMyProducer;
import org.my.cdi.producer.MyProducer;
import org.my.cdi.producer.Produced;

import javax.inject.Inject;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


@RunWith(Arquillian.class)
public class InterceptorTest
{
    @Inject
    public Intercepted intercepted;

    @Deployment
    public static JavaArchive createTestArchive()
    {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(Benchmarkable.class)
                .addClasses(BenchmarkInterceptor.class)
                .addClasses(Intercepted.class)
                .addAsManifestResource(
                        new ByteArrayAsset("<interceptors><class>org.my.cdi.interceptor.BenchmarkInterceptor</class></interceptors>".getBytes()),
                        ArchivePaths.create("beans.xml"));
    }


    @Test
    public void testInterceptor()
    {
        intercepted.doSomething();
        long duration = BenchmarkInterceptor.result;
        Assert.assertTrue(duration >= 1000 && duration < 1100);
    }
}
