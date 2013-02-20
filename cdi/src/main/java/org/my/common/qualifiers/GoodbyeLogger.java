package org.my.common.qualifiers;

@Log(LogType.GOODBYE)
public class GoodbyeLogger implements MyLogger
{
    public String log(String msg)
    {
        String result = "Goodbye: " + msg;
        System.out.println(result);
        return result;
    }
}
