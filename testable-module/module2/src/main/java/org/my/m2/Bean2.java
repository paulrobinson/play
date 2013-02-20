package org.my.m2;

import javax.transaction.UserTransaction;


/**
 * @Author paul.robinson@redhat.com 08/02/2012
 */
public class Bean2 {

    public String getMessage() {
        String message = "M2,V3";
        System.out.println(message);
        return message;
    }

    public String doSomething()
    {
        return "done something";
    }
}
