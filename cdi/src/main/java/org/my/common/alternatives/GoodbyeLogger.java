package org.my.common.alternatives;

import javax.enterprise.inject.Alternative;

@Alternative
public class GoodbyeLogger implements MyLogger
{
    public String log(String msg)
    {
        String result = "Goodbye: " + msg;
        System.out.println(result);
        return result;
    }
}
