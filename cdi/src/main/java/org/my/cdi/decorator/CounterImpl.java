package org.my.cdi.decorator;

import java.util.Date;

public class CounterImpl implements Counter
{
    private int value = 0;

    public int getValue()
    {
        return value;
    }

    public void incriment()
    {
        value++;
    }
}
