package org.my.cdi.producer;

import javax.enterprise.inject.Produces;

public class MyProducer
{
    @Produces
    @FromMyProducer
    public Produced getInstance()
    {
        return new Produced("produced");
    }
}
