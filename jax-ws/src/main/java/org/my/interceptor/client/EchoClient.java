package org.my.interceptor.client;

import org.my.interceptor.jaxws.EchoService;
import org.my.interceptor.jaxws.EchoServiceService;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

public class EchoClient implements IEchoService {

    public String sayHello(String msg) {
        try {
            EchoServiceService echoService = new EchoServiceService(new URL("http://localhost:8080/echo_service/InterceptedEchoService?wsdl"),
                    new QName("http://my.org/simple", "InterceptedEchoServiceService"));
            EchoService echo = echoService.getEchoServicePort();
            return echo.sayHello(msg);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
