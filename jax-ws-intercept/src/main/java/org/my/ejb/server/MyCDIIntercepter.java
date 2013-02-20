package org.my.ejb.server;

import org.my.common.BenchmarkInterceptorImpl;
import org.my.common.Benchmarkable;
import org.my.common.GenericInterceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

@Benchmarkable
@Interceptor
public class MyCDIIntercepter {

    @AroundInvoke
    public Object logPerformance(InvocationContext ic) throws Exception {

        Method serviceMethod = ic.getMethod();
        Object serviceImpl = ic.getTarget();

        GenericInterceptor gi = new BenchmarkInterceptorImpl();
        gi.setup(serviceImpl, serviceMethod);
        try {
            Object result = gi.proceed(ic);
            gi.notifySuccess();
            return result;
        } catch (Exception e) {
            gi.notifyFailure(e);
            throw e;
        }
    }
}