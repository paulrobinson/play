package org.my.common.alternatives;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import javax.inject.Inject;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AlternativeTest
{
    @Inject
    public MyLogger logger;

    @Deployment
    public static JavaArchive createTestArchive()
    {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(MyLogger.class)
                .addClasses(HelloLogger.class)
                .addClasses(GoodbyeLogger.class)
                .addAsManifestResource(
                        new ByteArrayAsset("<alternatives><class>org.my.common.alternatives.GoodbyeLogger</class></alternatives>".getBytes()),
                        ArchivePaths.create("beans.xml"));

    }

    @Test
    public void testHello()
    {
        String result = logger.log("Paul");
        Assert.assertEquals("Goodbye: Paul", result);
    }

}
