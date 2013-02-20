package org.my.ejb.server;

import org.my.common.Benchmarkable;
import org.my.common.MyException;
import org.my.ejb.client.IEchoService;

import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@Stateless
@WebService(serviceName = "EchoServiceService", portName = "EchoServicePort",
        name = "EchoServiceImpl", targetNamespace = "http://my.org/simple")
@HandlerChain(file = "/server-handlers.xml", name = "Context Handlers")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EchoServiceImpl implements IEchoService
{
    public String value = "old";

    public EchoServiceImpl()
    {
        System.out.println("New instance");
    }

    @Benchmarkable
    public String sayHello(String msg) throws MyException
    {
        if (msg == null || msg.equals(""))
        {
            throw new MyException("message is empty");
        }

        String result = "Hello " + msg;
        System.out.println(result);
        return result;
    }

    public String sayHello2(String msg) throws MyException
    {
        return sayHello(msg);
    }
}
