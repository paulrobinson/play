package org.my.injection.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.RequestWrapper;

public interface EchoService
{
    @WebMethod
    public String sayHello(String arg0);

}
