package org.my.simple.client;

import org.my.simple.server.MyException;
import javax.ejb.Remote;

@Remote
public interface IEchoService
{
    public String sayHello(String msg) throws MyException;
}
