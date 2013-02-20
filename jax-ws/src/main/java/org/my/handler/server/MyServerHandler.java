package org.my.handler.server;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.xml.ws.handler.MessageContext;

import javax.management.ObjectName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.frontend.MethodDispatcher;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.service.Service;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.jboss.wsf.spi.SPIProviderResolver;
import org.jboss.wsf.spi.classloading.ClassLoaderProvider;
import org.jboss.wsf.spi.deployment.Endpoint;
import org.jboss.wsf.spi.deployment.Reference;
import org.jboss.wsf.spi.invocation.EndpointAssociation;
import org.jboss.wsf.spi.management.EndpointRegistry;
import org.jboss.wsf.spi.management.EndpointRegistryFactory;


public class MyServerHandler implements SOAPHandler<SOAPMessageContext> {
    public Set<QName> getHeaders() {
        System.out.println("MyServerHandler2.getHeaders()");
        return new HashSet<QName>();
    }

    public boolean handleMessage(SOAPMessageContext soapMessageContext) {

        Boolean outbound = (Boolean)soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        System.out.println("MyServerHandler.handleMessage(): outbound: " + outbound);

        HandlerServiceImpl handlerService = (HandlerServiceImpl) getEndpointInstanceCXF(soapMessageContext);
        System.out.println("Message from service impl: " + handlerService.getMessage());

        return true;
    }

    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        System.out.println("MyServerHandler.handleFault()");
        return true;
    }

    public void close(MessageContext messageContext) {
        System.out.println("MyServerHandler.close()");
    }

    public Object getEndpointInstanceCXF(MessageContext ctx) {

        Exchange exchange = ((WrappedMessageContext) ctx).getWrappedMessage().getExchange();
        Endpoint endpoint = exchange.get(Endpoint.class);
        Reference ref = endpoint.getInstanceProvider().getInstance(endpoint.getTargetBeanName());

        BindingOperationInfo bop = exchange.get(BindingOperationInfo.class);
        MethodDispatcher md = (MethodDispatcher)exchange.get(Service.class).get(MethodDispatcher.class.getName());
        Method m = md.getMethod(bop);

        return ref.getValue();
    }
}
