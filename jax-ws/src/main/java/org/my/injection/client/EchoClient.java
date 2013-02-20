package org.my.injection.client;

import org.my.injection.jaxws.EchoService;
import org.my.injection.jaxws.InjectionServiceService;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

public class EchoClient implements EchoService {
    
    public String sayHello(String msg) {
        try {
            InjectionServiceService echoService = new InjectionServiceService(new URL("http://localhost:8081/echo_service/EchoService?wsdl"),
                    new QName("http://my.org/simple", "InjectionServiceService"));
            EchoService echo = echoService.getEchoServicePort();
            return echo.sayHello(msg);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
