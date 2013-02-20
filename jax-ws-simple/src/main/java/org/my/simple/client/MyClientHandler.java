package org.my.simple.client;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;


public class MyClientHandler implements Handler
{
    @Override
    public boolean handleMessage(MessageContext messageContext) {

        System.out.println("MyClientHandler.handleMessage()");
        return false;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) {

        System.out.println("MyClientHandler.handleFault()");
        return false;
    }

    public void close(MessageContext messageContext)
    {
        System.out.println("MyClientHandler.close()");
    }
}
