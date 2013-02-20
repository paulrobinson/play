package org.my.simple.server;

import org.my.simple.client.IEchoService;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(serviceName = "EchoServiceService", portName = "EchoServicePort",
        name = "EchoServiceImpl", targetNamespace = "http://my.org/simple")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EchoServiceImpl implements IEchoService
{
    @WebMethod
    public String sayHello(String msg) throws MyException
    {
        try
        {
            //Thread.sleep(10000);
        }
        catch (Exception e)
        {

        }

        if (msg == null || msg.equals(""))
        {
            throw new MyException("Message was empty");
        }

        String result = "Hello " + msg;
        System.out.println(result);
        return result;
    }

    @WebMethod(exclude = true)
    public void privateMethod()
    {

    }

}
