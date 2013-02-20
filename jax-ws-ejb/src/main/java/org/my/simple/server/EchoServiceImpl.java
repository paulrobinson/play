package org.my.simple.server;

import org.my.simple.client.IEchoService;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@Stateless
@WebService(serviceName = "EchoServiceService", portName = "EchoServicePort",
        name = "EchoServiceImpl", targetNamespace = "http://my.org/simple")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EchoServiceImpl implements IEchoService
{
    public String sayHello(String msg)
    {
        String result = "Hello " + msg;
        System.out.println(result);
        return result;
    }
}
