package org.my.interceptor.server;

import org.my.interceptor.Benchmarkable;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "InterceptedEchoServiceService", portName = "EchoServicePort",
        name = "EchoServiceImpl", targetNamespace = "http://my.org/simple")
public class InterceptedEchoServiceImpl
{
    @WebMethod
    @Benchmarkable
    public String sayHello(String msg)
    {
        return "Hello " + msg;
    }

}
