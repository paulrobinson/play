
package org.echo.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "EchoService", targetNamespace = "http://echo.org/echoservice")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EchoService {


    /**
     * 
     * @param sendMessage
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "sendMessageResponse", targetNamespace = "http://echo.org/echoservice3", partName = "sendMessageResponse")
    public String sendMessage(
        @WebParam(name = "sendMessage", targetNamespace = "http://echo.org/echoservice2", partName = "sendMessage")
        SendMessage sendMessage);

}
