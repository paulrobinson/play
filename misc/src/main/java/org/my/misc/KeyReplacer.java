package org.my.misc;

/**
 * @author paul.robinson@redhat.com, 2012-03-06
 */
public class KeyReplacer {

    public static String getKey(String msg) {
        int start = msg.indexOf("${");
        int end = msg.indexOf("}");

        if (start == -1 || end == -1)
        {
            return null;
        }

        return msg.substring(start+2, end);
    }

    public static String replaceKey(String msg, String value)
    {
        int start = msg.indexOf("${");
        int end = msg.indexOf("}");

        if (start == -1 || end == -1)
        {
            return msg;
        }

        String beginning = msg.substring(0, start);
        String ending = msg.substring(end+1, msg.length());

        return beginning + value + ending;
    }

}
