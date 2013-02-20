package org.my.ejb;

import org.jboss.resteasy.core.ThreadLocalResteasyProviderFactory;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.RuntimeDelegate;

@MyInterceptor
@Interceptor
public class MyInterceptorImpl {
    public static long result = 0;

    @AroundInvoke
    public Object logPerformance(InvocationContext ic) throws Exception {

        long start = System.currentTimeMillis();

        System.out.println("\n\n\nBefore\n\n\n");

        Object value = ic.proceed();

        System.out.println("\n\n\nAfter\n\n\n");

        result = System.currentTimeMillis() - start;

        return value;
    }
}