package org.my.cdi.observer;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class EventProducer
{
    @Inject
    private Event<PostEvent> event;

    public void sendEvent(String msg)
    {
        event.fire(new PostEvent(msg));
    }

}
