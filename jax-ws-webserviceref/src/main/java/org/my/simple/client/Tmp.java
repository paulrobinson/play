package org.my.simple.client;

/**
 * @author paul.robinson@redhat.com, 2012-03-07
 */
public class Tmp {

    public String sayHello(String msg) {
        String result = "Hello " + msg;
        System.out.println("\n\nInvoked TMP\n\n");
        return result;
    }
}
