package org.my.common;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

/**
 * @author paul.robinson@redhat.com, 2012-03-01
 */
public class BenchmarkInterceptorImpl implements GenericInterceptor {

    public static int setupCount = 0;
    public static int proceedCount = 0;
    public static int notifySuccessCount = 0;
    public static int notifyFailureCount = 0;

    public void setup(Object instance, Method method) {
        setupCount++;
        System.out.println("Setting up");

        if (instance instanceof org.my.pojo.server.EchoServiceImpl)
        {
            ((org.my.pojo.server.EchoServiceImpl) instance).value = "new";
        }

        if (instance instanceof org.my.ejb.server.EchoServiceImpl)
        {
            ((org.my.ejb.server.EchoServiceImpl) instance).value = "new";
        }
    }

    public Object proceed(InvocationContext ic) throws Exception {
        proceedCount++;
        return ic.proceed();
    }

    public void notifySuccess() {
        notifySuccessCount++;
        System.out.println("Success action");
    }

    public void notifyFailure(Exception e) {
        notifyFailureCount++;
        System.out.println("Failure action");
    }

    public static void reset()
    {
        setupCount = 0;
        proceedCount = 0;
        notifySuccessCount = 0;
        notifyFailureCount = 0;
    }
}
