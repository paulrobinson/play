package org.my.pojo.client;

import org.my.common.MyException;

public interface IEchoService {
    public String sayHello(String msg) throws MyException;

    public String sayHello2(String msg) throws MyException;
}
