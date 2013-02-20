package org.my.simple.client;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.HashSet;
import java.util.Set;

public class MyClientHandler implements SOAPHandler<SOAPMessageContext>
{
    public Set<QName> getHeaders()
    {
        System.out.println("MyClientHandler.getHeaders()");
        return new HashSet<QName>();
    }

    public boolean handleMessage(SOAPMessageContext soapMessageContext)
    {
        System.out.println("MyClientHandler.handleMessage()");
        return true;
    }

    public boolean handleFault(SOAPMessageContext soapMessageContext)
    {
        System.out.println("MyClientHandler.handleFault()");
        return true;
    }

    public void close(MessageContext messageContext)
    {
        System.out.println("MyClientHandler.close()");
    }
}
