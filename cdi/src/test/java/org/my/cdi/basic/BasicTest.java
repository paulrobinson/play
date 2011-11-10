package org.my.cdi.basic;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.cdi.singleton.MySingleton;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class BasicTest
{
    @Inject
    public Hello hello;

    @Deployment
    public static JavaArchive createTestArchive()
    {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(Hello.class)
                .addClasses(HelloImpl.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
    }


    @Test
    public void testSingleton()
    {
        Assert.assertEquals("Hello Paul", hello.sayHello("Paul"));
    }
}
