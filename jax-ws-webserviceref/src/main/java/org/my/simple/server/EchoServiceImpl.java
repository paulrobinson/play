package org.my.simple.server;

import org.my.simple.client.IEchoService;
import org.my.simple.jaxws.EchoServiceService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceRef;

@WebService(serviceName = "EchoServiceService", portName = "EchoServicePort",
        name = "EchoServiceImpl", targetNamespace = "http://my.org/simple")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EchoServiceImpl implements IEchoService
{
    @WebMethod
    public String sayHello(String msg)
    {
        String result = "Hello " + msg;
        System.out.println("\n\nInvoked WS\n\n");
        return result;
    }

}
