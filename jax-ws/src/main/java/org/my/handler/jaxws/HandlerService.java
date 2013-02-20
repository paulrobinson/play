package org.my.handler.jaxws;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 */
@WebService(name = "HandlerServiceImpl", targetNamespace = "http://my.org/simple")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@HandlerChain(file = "client-handlers.xml")
public interface HandlerService
{


    /**
     * @param arg0
     * @return returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String sayHello(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0);

}