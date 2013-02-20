package org.my.common;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

/**
 * @author paul.robinson@redhat.com, 2012-03-01
 */
public interface GenericInterceptor {

    public void setup(Object instance, Method method);

    public Object proceed(InvocationContext ic) throws Exception;

    public void notifySuccess();

    public void notifyFailure(Exception e);
}
