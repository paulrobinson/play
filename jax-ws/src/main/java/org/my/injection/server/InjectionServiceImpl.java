package org.my.injection.server;

import org.my.injection.HelloImpl;
import org.my.injection.InterceptorAnnotation;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "InjectionServiceService", portName = "EchoServicePort",
        name = "EchoServiceImpl", targetNamespace = "http://my.org/simple")
public class InjectionServiceImpl
{
    @Inject
    public HelloImpl hello;

    @WebMethod
    @InterceptorAnnotation
    public String sayHello(String msg)
    {
        return hello.sayHello(msg);
    }

}
