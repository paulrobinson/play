package org.my.rendezvous;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class TemperatureConverterTest
{
    @Inject
    private TemperatureConverter converter;

    @Deployment
    public static JavaArchive createTestArchive()
    {

        return ShrinkWrap.create(JavaArchive.class, "test1.jar")
                .addClasses(TemperatureConverter.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));

    }

    @Test
    public void testConvertToCelsius()
    {
        Assert.assertEquals(converter.convertToCelsius(32d), 0d);
        Assert.assertEquals(converter.convertToCelsius(212d), 100d);
    }

    @Test
    public void testConvertToFarenheit()
    {
        Assert.assertEquals(converter.convertToFarenheit(0d), 32d);
        Assert.assertEquals(converter.convertToFarenheit(100d), 212d);
    }

}