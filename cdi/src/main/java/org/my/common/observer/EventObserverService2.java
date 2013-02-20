package org.my.common.observer;

import javax.enterprise.event.Observes;

public class EventObserverService2
{
    private static String lastMsg;

    public void afterPostEvent(@Observes PostEvent event)
    {
        System.out.println("Received event: " + event.getMsg());
        lastMsg = event.getMsg();
    }

    public static String getLastMsg()
    {
        return lastMsg;
    }
}