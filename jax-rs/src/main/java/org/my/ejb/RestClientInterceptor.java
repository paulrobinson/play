package org.my.ejb;

import org.jboss.resteasy.annotations.interception.ClientInterceptor;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.interception.ClientExecutionContext;
import org.jboss.resteasy.spi.interception.ClientExecutionInterceptor;

import javax.ws.rs.ext.Provider;

/**
 * @Author paul.robinson@redhat.com 08/04/2012
 */
@ClientInterceptor
@Provider
public class RestClientInterceptor implements ClientExecutionInterceptor {

    public static boolean invoked = false;

    public ClientResponse execute(ClientExecutionContext clientExecutionContext) throws Exception {

        invoked = true;

        clientExecutionContext.getRequest().header("txctx", "123456");

        System.out.println("RestClientInterceptor.before");
        ClientResponse response = clientExecutionContext.proceed();
        System.out.println("RestClientInterceptor.after");
        return response;
    }
}
