package org.my.simple.client;

import javax.ejb.Remote;

@Remote
public interface IEchoService
{
    public String sayHello(String msg);
}
