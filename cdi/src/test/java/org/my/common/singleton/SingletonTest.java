package org.my.common.singleton;

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

@RunWith(Arquillian.class)
public class SingletonTest
{
    @Inject
    public MySingleton mySingleton1;

    @Inject
    public MySingleton mySingleton2;

    @Deployment
    public static JavaArchive createTestArchive()
    {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(MySingleton.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
    }


    @Test
    public void testSingleton()
    {
        int result = mySingleton1.incr();
        Assert.assertEquals(0, result);

        result = mySingleton2.incr();
        Assert.assertEquals(1, result);

        result = mySingleton1.incr();
        Assert.assertEquals(2, result);

        result = mySingleton2.incr();
        Assert.assertEquals(3, result);
    }
}
