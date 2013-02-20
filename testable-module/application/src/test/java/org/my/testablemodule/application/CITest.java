package org.my.testablemodule.application;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.app.App;

@RunWith(Arquillian.class)
public class CITest {

    @Deployment(name = "app", order = 2)
    public static JavaArchive createTestArchive() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(App.class);

        archive.delete(ArchivePaths.create("META-INF/MANIFEST.MF"));
        String ManifestMF = "Manifest-Version: 1.0\n"
                + "Dependencies: org.my.m1\n";
        archive.setManifest(new StringAsset(ManifestMF));

        return archive;
    }


    @Test
    public void test() {
        App app = new App();
        Assert.assertEquals("M1,V1 M2,V1", app.getMessage());
    }
}