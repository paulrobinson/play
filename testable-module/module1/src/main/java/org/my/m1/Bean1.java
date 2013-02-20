package org.my.m1;


//import org.my.m2.Bean2;

/**
 * @Author paul.robinson@redhat.com 08/02/2012
 */
public class Bean1 {

    public String getMessage() {
        String message = "M1,V6";
/*        Bean2 bean2 = new Bean2();
        message += " " + bean2.getMessage();*/

        System.out.println(message);
        return message;
    }
}
