package org.my.simple.server;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.HashSet;
import java.util.Set;

public class MyServerHandler implements SOAPHandler<SOAPMessageContext>
{
    private static int count = 0;

    public Set<QName> getHeaders()
    {
        count++;
        System.out.println("MyServerHandler.getHeaders()");
        return new HashSet<QName>();
    }

    public boolean handleMessage(SOAPMessageContext soapMessageContext)
    {
        count++;
        System.out.println("MyServerHandler.handleMessage()");
        return true;
    }

    public boolean handleFault(SOAPMessageContext soapMessageContext)
    {
        count++;
        System.out.println("MyServerHandler.handleFault()");
        return true;
    }

    public void close(MessageContext messageContext)
    {
        count++;
        System.out.println("MyServerHandler.close()");
    }

    public static int getCount()
    {
        return count;
    }
}
