package org.my.common.qualifiers;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.my.common.qualifiers.LogType.GOODBYE;
import static org.my.common.qualifiers.LogType.HELLO;

@RunWith(Arquillian.class)
public class QualifierTest
{
    @Inject
    @Log(HELLO)
    public MyLogger helloLogger;

    @Inject
    @Log(GOODBYE)
    public MyLogger goodbyeLogger;

    @Deployment
    public static JavaArchive createTestArchive()
    {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(MyLogger.class)
                .addClasses(HelloLogger.class)
                .addClasses(GoodbyeLogger.class)
                .addClasses(Log.class)
                .addClasses(LogType.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
    }

    @Test
    public void testHello()
    {
        String result = helloLogger.log("Paul");
        Assert.assertEquals("HelloImpl: Paul", result);
    }

    @Test
    public void testGoodbye()
    {
        String result = goodbyeLogger.log("Paul");
        Assert.assertEquals("Goodbye: Paul", result);
    }
}
