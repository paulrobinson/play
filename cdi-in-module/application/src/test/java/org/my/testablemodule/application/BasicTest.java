package org.my.testablemodule.application;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.testablemodule.module.MyApp;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class BasicTest
{
    @Inject
    public MyApp hello;

    @Deployment
    public static JavaArchive createTestArchive()
    {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(MyApp.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));

                archive.delete(ArchivePaths.create("META-INF/MANIFEST.MF"));
        String ManifestMF = "Manifest-Version: 1.0\n"
                + "Dependencies: org.my\n";
        archive.setManifest(new StringAsset(ManifestMF));

        return archive;
    }


    @Test
    public void testSingleton()
    {
        Assert.assertEquals("Hello Paul", hello.sayHello("Paul"));
    }
}