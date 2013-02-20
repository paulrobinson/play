package org.echo.service;


import org.echo.jaxws.Shiporder;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.annotation.WebServlet;

/**
 * @Author paul.robinson@redhat.com 30/09/2012
 */
@WebServlet("/echoService")
@WebService(serviceName = "EchoServiceName", portName = "EchoServicePort",
        name = "EchoService", targetNamespace = "http://echo.org/echoservice")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class EchoServiceImpl implements EchoService {

    @WebMethod
    public String sendMessage(Shiporder shiporder) {
        return shiporder.getOrderid();
    }
}
