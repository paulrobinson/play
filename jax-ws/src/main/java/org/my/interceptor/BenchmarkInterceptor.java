package org.my.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Benchmarkable
@Interceptor
public class BenchmarkInterceptor
{
    public static long result = -1;

    @AroundInvoke
    public Object logPerformance(InvocationContext ic) throws Exception
    {
        long start = System.currentTimeMillis();

        Object value = ic.proceed();

        result = System.currentTimeMillis() - start;

        return value;
    }
}