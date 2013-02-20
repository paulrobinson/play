package org.my.ejb.client;

import org.my.common.MyException;
import org.my.ejb.jaxws.EchoService;
import org.my.ejb.jaxws.EchoServiceService;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

public class EchoClient implements IEchoService {
    public String sayHello(String msg) throws MyException {
        try {
            EchoServiceService echoService = new EchoServiceService(new URL("http://localhost:8080/echo_service/EchoServiceService/EchoServiceImpl?wsdl"),
                    new QName("http://my.org/simple", "EchoServiceService"));
            EchoService echo = echoService.getEchoServicePort();
            return echo.sayHello(msg);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String sayHello2(String msg) throws MyException {
        try {
            EchoServiceService echoService = new EchoServiceService(new URL("http://localhost:8080/echo_service/EchoServiceService/EchoServiceImpl?wsdl"),
                    new QName("http://my.org/simple", "EchoServiceService"));
            EchoService echo = echoService.getEchoServicePort();
            return echo.sayHello2(msg);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
