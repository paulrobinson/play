package org.my.testablemodule.application;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.arquillian.container.ManagementClient;
import org.jboss.as.controller.Extension;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.app.App;
import org.my.m1.Bean1;
import org.xnio.IoUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jboss.as.arquillian.api.ContainerResource;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.*;

/**
 * @author paul.robinson@redhat.com, 2012-03-07
 */

@RunWith(Arquillian.class)
public class CombinedTest {

    private static final String MODULE_NAME = "my-module";
    private static final String JAR_NAME = "my-module.jar";

    private static Archive moduleArchive;

    private static File testModuleRoot;


    @Deployment
    public static JavaArchive createTestArchive() {

        try {
            moduleArchive = createArchive();
            CombinedTest combinedTest = new CombinedTest();
            System.setProperty("jboss.home", "/home/paul/dev/jboss-as/build/target/jboss-as-7.1.1.Final-SNAPSHOT");
            testModuleRoot = new File(combinedTest.getModulePath(), MODULE_NAME);
            combinedTest.deleteRecursively(testModuleRoot);
            combinedTest.createTestModule(testModuleRoot);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to register module", e);
        }

        JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(App.class);

        archive.delete(ArchivePaths.create("META-INF/MANIFEST.MF"));
        String ManifestMF = "Manifest-Version: 1.0\n"
                + "Dependencies: my-module,org.jboss.as.controller-client,org.jboss.as.controller,org.jboss.shrinkwrap.core\n";
        archive.setManifest(new StringAsset(ManifestMF));

        return archive;
    }

    @AfterClass
    public static void removeModule()
    {
        CombinedTest combinedTest = new CombinedTest();
        combinedTest.deleteRecursively(testModuleRoot);
    }


    @Test
    public void test() {

        App app = new App();
        org.junit.Assert.assertEquals("M1,V6", app.getMessage());
    }

    private static Archive<?> createArchive() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, JAR_NAME);
        jar.addPackages(true, "org.my.m1");
        return jar;
    }

    private ModelNode executeOperation(ModelControllerClient client, String name, PathAddress address, boolean fail) throws IOException {
        ModelNode op = new ModelNode();
        op.get(OP).set(name);
        op.get(OP_ADDR).set(address.toModelNode());

        ModelNode result = client.execute(op);
        if (!fail) {
            Assert.assertFalse(result.get(FAILURE_DESCRIPTION).toString(), result.get(FAILURE_DESCRIPTION).isDefined());
        } else {
            Assert.assertTrue(result.get(FAILURE_DESCRIPTION).isDefined());
        }

        return result.get(RESULT);
    }

    private void deleteRecursively(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (String name : file.list()) {
                    deleteRecursively(new File(file, name));
                }
            }
            file.delete();
        }
    }

    private void copyFile(File target, InputStream src) throws IOException {
        final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target));
        try {
            int i = src.read();
            while (i != -1) {
                out.write(i);
                i = src.read();
            }
        } finally {
            IoUtils.safeClose(out);
        }
    }

    private void createTestModule(File testModuleRoot) throws IOException {
        if (testModuleRoot.exists()) {
            throw new IllegalArgumentException(testModuleRoot + " already exists");
        }
        File file = new File(testModuleRoot, "main");
        if (!file.mkdirs()) {
            throw new IllegalArgumentException("Could not create " + file);
        }
        final InputStream is = moduleArchive.as(ZipExporter.class).exportAsInputStream();
        try {
            copyFile(new File(file, JAR_NAME), is);
        } finally {
            IoUtils.safeClose(is);
        }

        URL url = this.getClass().getResource("/module.xml");
        if (url == null) {
            throw new IllegalStateException("Could not find module.xml");
        }
        copyFile(new File(file, "module.xml"), url.openStream());
    }

    private File getModulePath() {
        String modulePath = System.getProperty("module.path", null);
        if (modulePath == null) {
            String jbossHome = System.getProperty("jboss.home", null);
            if (jbossHome == null) {
                throw new IllegalStateException("Neither -Dmodule.path nor -Djboss.home were set");
            }
            modulePath = jbossHome + File.separatorChar + "modules";
        }
        File moduleDir = new File(modulePath);
        if (!moduleDir.exists()) {
            throw new IllegalStateException("Determined module path does not exist");
        }
        if (!moduleDir.isDirectory()) {
            throw new IllegalStateException("Determined module path is not a dir");
        }
        return moduleDir;
    }
}
