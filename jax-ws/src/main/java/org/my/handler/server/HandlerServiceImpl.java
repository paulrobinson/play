package org.my.handler.server;

import org.my.handler.client.IHandlerService;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(serviceName = "HandlerServiceService", portName = "HandlerServicePort",
        name = "HandlerServiceImpl", targetNamespace = "http://my.org/simple")
@HandlerChain(file = "/server-handlers.xml", name = "Context Handlers")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HandlerServiceImpl implements IHandlerService {

    @WebMethod
    public String sayHello(String msg) {

        try {
            Thread.sleep(10000);
        } catch (Exception e) {

        }

        String result = "Hello " + msg;
        System.out.println(result);
        return result;
    }

    public String getMessage()
    {
        return "hello";
    }

}
