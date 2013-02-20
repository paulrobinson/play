package org.my.simple.ejb;

import org.my.simple.client.ATClient;
import org.my.simple.client.Tmp;
import org.my.simple.jaxws.EchoService;
import org.my.simple.jaxws.EchoServiceService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;

/**
 * @author paul.robinson@redhat.com, 2012-03-07
 */
@Stateless
@Remote
public class MyEJBImpl implements MyEJB {

    //@WebServiceRef(name="EchoServiceService")
    @ATClient
    public Tmp echoService;

    public String sayHello(String msg) {
        System.out.println("\n\nInvoked EJB\n\n");
        //EchoService client = echoService.getEchoServicePort();
        return echoService.sayHello(msg);
    }
}
