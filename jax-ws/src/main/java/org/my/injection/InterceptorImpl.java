package org.my.injection;


import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.interceptor.Interceptor;

@Interceptor
@InterceptorAnnotation
public class InterceptorImpl
{
    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Throwable
    {
        return ic.proceed();
    }

}

