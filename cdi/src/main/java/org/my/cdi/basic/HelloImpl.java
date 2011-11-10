package org.my.cdi.basic;

public class HelloImpl implements Hello
{
    public String sayHello(String msg)
    {
        return "Hello " + msg;
    }
}
