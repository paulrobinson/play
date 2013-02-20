
package org.echo.jaxws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.echo.jaxws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendMessageResponse_QNAME = new QName("http://echo.org/echoservice8", "sendMessageResponse");
    private final static QName _SendMessage_QNAME = new QName("http://echo.org/echoservice7", "sendMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.echo.jaxws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendMessage.Shipto }
     * 
     */
    public SendMessage.Shipto createSendMessageShipto() {
        return new SendMessage.Shipto();
    }

    /**
     * Create an instance of {@link Shiporder.Shipto }
     * 
     */
    public Shiporder.Shipto createShiporderShipto() {
        return new Shiporder.Shipto();
    }

    /**
     * Create an instance of {@link SendMessage.Item }
     * 
     */
    public SendMessage.Item createSendMessageItem() {
        return new SendMessage.Item();
    }

    /**
     * Create an instance of {@link Shiporder.Item }
     * 
     */
    public Shiporder.Item createShiporderItem() {
        return new Shiporder.Item();
    }

    /**
     * Create an instance of {@link Shiporder }
     * 
     */
    public Shiporder createShiporder() {
        return new Shiporder();
    }

    /**
     * Create an instance of {@link SendMessage }
     * 
     */
    public SendMessage createSendMessage() {
        return new SendMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://echo.org/echoservice6", name = "sendMessageResponse")
    public JAXBElement<String> createSendMessageResponse(String value) {
        return new JAXBElement<String>(_SendMessageResponse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://echo.org/echoservice5", name = "sendMessage")
    public JAXBElement<SendMessage> createSendMessage(SendMessage value) {
        return new JAXBElement<SendMessage>(_SendMessage_QNAME, SendMessage.class, null, value);
    }

}
