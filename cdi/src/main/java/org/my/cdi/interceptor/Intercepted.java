package org.my.cdi.interceptor;

public class Intercepted
{
    @Benchmarkable
    public void doSomething()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
