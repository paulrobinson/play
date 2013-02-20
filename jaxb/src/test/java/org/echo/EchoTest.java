package org.echo;

import org.echo.jaxws.EchoService;
import org.echo.jaxws.EchoServiceName;
import org.echo.jaxws.SendMessage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @Author paul.robinson@redhat.com 30/09/2012
 */
@RunWith(Arquillian.class)
public class EchoTest {

   @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "echo_service-1.0.war")
                .addPackages(true, "org.echo");
    }

    @Test
    public void test() throws Exception
    {
        EchoServiceName echoServiceName = new EchoServiceName();
        EchoService echo = echoServiceName.getEchoServicePort();

        SendMessage.Shipto shipTo = new SendMessage.Shipto();
        shipTo.setAddress("address");
        shipTo.setCity("city");
        shipTo.setCountry("country");
        shipTo.setName("name");

        SendMessage shipordr = new SendMessage();
        shipordr.setOrderid("orderId");
        shipordr.setOrderperson("person");
        shipordr.setShipto(shipTo);

        echo.sendMessage(shipordr);
    }
}
