package org.my.common.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.Serializable;

@Decorator
public abstract class ServiceDecorator implements Counter, Serializable
{
    @Inject
    @Delegate
    @Any
    private Counter counter;

    public int getValue()
    {
        counter.incriment();
        return counter.getValue();
    }

}
