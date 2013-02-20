package org.my.common.observer;

public class PostEvent
{
    private String msg;

    public PostEvent(String msg)
    {
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }
}
