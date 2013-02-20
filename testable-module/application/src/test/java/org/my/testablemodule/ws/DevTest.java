package org.my.testablemodule.ws;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.m1.Bean1;
import org.my.ws.EchoService;
import org.my.ws.EchoServiceImpl;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(Arquillian.class)
public class DevTest {

    @Deployment(name = "module", order = 1, testable = false)
    public static JavaArchive createModule() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Bean1.class)
                .addAsManifestResource("jboss-deployment-structure.xml");
    }

    @Deployment(name = "app", order = 2)
    public static JavaArchive createTestArchive() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "echo_service.jar")
                .addClasses(EchoService.class, EchoServiceImpl.class);

        archive.delete(ArchivePaths.create("META-INF/MANIFEST.MF"));
        String ManifestMF = "Manifest-Version: 1.0\n"
                + "Dependencies: deployment.org.my.m1\n";
        archive.setManifest(new StringAsset(ManifestMF));

        return archive;
    }

    @Test
    public void echoTest() throws Exception {
        EchoService echoClient = createEchoServiceClient();
        Assert.assertEquals("Hello Paul", echoClient.sayHello("Paul"));
    }

    private EchoService createEchoServiceClient() {
        try {

            URL wsdlLocation = new URL("http://localhost:8080/echo_service/EchoServiceService/EchoServiceImpl?wsdl");
            QName serviceName = new QName("http://org.jboss.weld/tests/interceptors/jaxws", "EchoServiceService");
            QName portName = new QName("http://org.jboss.weld/tests/interceptors/jaxws", "EchoServicePort");

            Service service = Service.create(wsdlLocation, serviceName);
            return service.getPort(portName, EchoService.class);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
