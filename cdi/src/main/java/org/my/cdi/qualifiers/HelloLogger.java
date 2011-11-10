package org.my.cdi.qualifiers;

@Log(LogType.HELLO)
public class HelloLogger implements MyLogger
{
    public String log(String msg)
    {
        String result = "HelloImpl: " + msg;
        System.out.println(result);
        return result;
    }

}
