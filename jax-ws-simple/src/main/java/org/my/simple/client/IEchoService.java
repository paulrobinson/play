package org.my.simple.client;

import org.my.simple.server.MyException;

public interface IEchoService
{
    public String sayHello(String msg) throws MyException;
}
