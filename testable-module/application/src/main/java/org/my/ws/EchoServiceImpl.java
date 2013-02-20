package org.my.ws;

import org.my.m1.Bean1;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless
@Remote(EchoService.class)
@WebService(serviceName = "EchoServiceService", portName = "EchoServicePort",
        name = "EchoServiceImpl", targetNamespace = "http://org.jboss.weld/tests/interceptors/jaxws")
public class EchoServiceImpl implements EchoService
{
    @WebMethod
    public String sayHello(String msg)
    {
        Bean1 bean1 = new Bean1();
        System.out.println(bean1.getMessage());
        return "Hello " + msg;
    }

}