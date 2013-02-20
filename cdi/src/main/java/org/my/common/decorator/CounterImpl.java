package org.my.common.decorator;

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
