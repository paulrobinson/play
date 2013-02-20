package org.my.common.producer;

import javax.enterprise.inject.Produces;

public class MyProducer
{
    @Produces
    @FromMyProducer
    public Produced getInstance()
    {
        System.out.println("Producer invoked");
        return new Produced("produced");
    }
}
