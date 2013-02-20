package org.my.simple.client;

import org.my.simple.jaxws.EchoServiceService;

import javax.enterprise.inject.Produces;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author paul.robinson@redhat.com, 2012-03-07
 */
public class ATProducer {

    @Produces
    @ATClient
    public Tmp getInstance() {
        System.out.println("\n\nInvoked Producer\n\n");
/*        try {
            return  new EchoServiceService(new URL("http://localhost:8080/echo_service/EchoService?wsdl"),
                        new QName("http://my.org/simple", "EchoServiceService"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error creating Client", e);
        }*/

        return new Tmp();
    }
}
