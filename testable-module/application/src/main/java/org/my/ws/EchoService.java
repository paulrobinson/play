package org.my.ws;


import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Remote
@WebService
public interface EchoService
{
    @WebMethod
    public String sayHello(String arg0);

}
