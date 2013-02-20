package org.my.cdi;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Benchmarkable
@Interceptor
public class BenchmarkInterceptor
{
    private static int callCount = 0;

    @AroundInvoke
    public Object logPerformance(InvocationContext ic) throws Exception
    {
        callCount++;
        return ic.proceed();
    }

    public static int getCallCount()
    {
        return callCount;
    }
}