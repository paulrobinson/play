package org.my.simple.server;

import javax.ejb.ApplicationException;
import java.io.Serializable;

@ApplicationException
public class MyException extends Exception implements Serializable
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
