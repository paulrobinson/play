package org.my.rendezvous;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class WaitForRecoveryToCompleteTest
{

    @Deployment
    public static JavaArchive createTestArchive()
    {

        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(TemperatureConverter.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));

    }


    @Test
    public void testWaitForRecoveryToComplete()
    {
        System.out.println("Recovery Complete. Test completing...");
    }

}