package org.my.ejb.client;

import org.my.common.MyException;

import javax.ejb.Remote;

@Remote
public interface IEchoService {
    public String sayHello(String msg) throws MyException;

    public String sayHello2(String msg) throws MyException;
}
