package org.my.simple.server;

public class MyException extends Exception
{
    public MyException(String message)
    {
        super(message);
    }

    public MyException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
