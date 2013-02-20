package org.echo.service;


import org.echo.jaxws.Shiporder;

/**
 * @Author paul.robinson@redhat.com 30/09/2012
 */
public interface EchoService {

    public String sendMessage(Shiporder shiporder);

}
