package org.my.server;

import org.my.client.IEchoService;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(serviceName = "EchoServiceService", portName = "EchoServicePort",
        name = "EchoServiceImpl", targetNamespace = "http://my.org/simple")
@HandlerChain(file = "/server-handlers.xml", name = "Context Handlers")
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
