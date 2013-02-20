package org.my.common.alternatives;

import javax.enterprise.inject.Alternative;

@Alternative
public class HelloLogger implements MyLogger
{
    public String log(String msg)
    {
        String result = "HelloImpl: " + msg;
        System.out.println(result);
        return result;
    }

}
