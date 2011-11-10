package org.my.cdi.decorator;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;


@RunWith(Arquillian.class)
public class DecoratorTest
{
    @Inject
    public Counter counter;

    @Deployment
    public static JavaArchive createTestArchive()
    {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(Counter.class)
                .addClasses(CounterImpl.class)
                .addClasses(IncrimentDecorator.class)
                .addAsManifestResource(
                        new ByteArrayAsset("<decorators><class>org.my.cdi.decorator.IncrimentDecorator</class></decorators>".getBytes()),
                        ArchivePaths.create("beans.xml"));
    }


    @Test
    public void testDecorator()
    {
        Assert.assertEquals(1, counter.getValue());
    }
}
