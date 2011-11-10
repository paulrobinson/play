package org.my.cdi.singleton;

import javax.inject.Singleton;

@Singleton
public class MySingleton
{
    private int counter = 0;

    public int incr()
    {
        return counter++;
    }
}
