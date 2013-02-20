package org.my.pojo.server;

import javax.ejb.Stateless;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.cxf.frontend.MethodDispatcher;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.service.Service;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.jboss.wsf.spi.deployment.Endpoint;
import org.jboss.wsf.spi.deployment.Reference;
import org.my.common.BenchmarkInterceptorImpl;
import org.my.common.Benchmarkable;
import org.my.common.GenericInterceptor;

public class MySoapHandler implements SOAPHandler<SOAPMessageContext> {

    private GenericInterceptor interceptor;

    private boolean shouldIntercept = false;

    public Set<QName> getHeaders() {

        System.out.println("MyServerHandler.getHeaders()");
        return new HashSet<QName>();
    }

    public boolean handleMessage(SOAPMessageContext soapMessageContext) {

        Boolean outbound = (Boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outbound) {
            return handleOutboundMessage(soapMessageContext);
        } else {
            return handleInboundMessage(soapMessageContext);
        }
    }

    private boolean handleInboundMessage(SOAPMessageContext soapMessageContext) {
        System.out.println("MyServerHandler.handleInboundMessage()");

        Object instance = getInstance(soapMessageContext);
        Method method = getMethod(soapMessageContext);

        shouldIntercept = !isEJB(instance) && isBenchmarkableMethod(method);

        if (shouldIntercept) {
            interceptor = new BenchmarkInterceptorImpl();
            interceptor.setup(instance, method);
        }

        return true;
    }

    private boolean isBenchmarkableMethod(Method method)
    {
        Benchmarkable benchmarkable = method.getAnnotation(Benchmarkable.class);
        return benchmarkable != null;
    }

    private boolean isEJB(Object instance)
    {
        Stateless stateless = instance.getClass().getAnnotation(Stateless.class);
        return stateless != null;
    }

    private boolean handleOutboundMessage(SOAPMessageContext soapMessageContext) {
        System.out.println("MyServerHandler.handleOutboundMessage()");

        if (shouldIntercept) {
            interceptor.notifySuccess();
        }

        return true;
    }

    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        System.out.println("MyServerHandler.handleFault()");

        if (shouldIntercept) {
            interceptor.notifyFailure(null);
        }
        return true;
    }

    public void close(MessageContext messageContext) {
        System.out.println("MyServerHandler.close()");
    }

    private Object getInstance(MessageContext ctx) {

        Endpoint endpoint = ((WrappedMessageContext) ctx).getWrappedMessage().getExchange().get(Endpoint.class);
        Reference ref = endpoint.getInstanceProvider().getInstance(endpoint.getTargetBeanName());
        return ref.getValue();
    }

    private Method getMethod(MessageContext ctx) {

        Exchange exchange = ((WrappedMessageContext) ctx).getWrappedMessage().getExchange();
        BindingOperationInfo bop = exchange.get(BindingOperationInfo.class);
        MethodDispatcher md = (MethodDispatcher) exchange.get(Service.class).get(MethodDispatcher.class.getName());
        return md.getMethod(bop);
    }
}
