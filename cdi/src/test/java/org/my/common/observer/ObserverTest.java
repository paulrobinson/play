package org.my.common.observer;

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
public class ObserverTest
{
    @Inject
    public EventProducer eventProducer;

    @Deployment
    public static JavaArchive createTestArchive()
    {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(EventObserverService1.class)
                .addClasses(EventObserverService2.class)
                .addClasses(EventProducer.class)
                .addClasses(PostEvent.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
    }


    @Test
    public void testObserver()
    {
        eventProducer.sendEvent("hello");
        Assert.assertEquals("hello", EventObserverService1.getLastMsg());
        Assert.assertEquals("hello", EventObserverService2.getLastMsg());
    }
}
