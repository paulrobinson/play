package org.my.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author paul.robinson@redhat.com 17/05/2012
 */
public class ExceptionTest {

    public void tm() {
        throw new AssertionError();
    }


    public static void main(String[] args) throws Exception{
        ExceptionTest t = new ExceptionTest();
        Method m = null;
        try {
            m = t.getClass().getMethod("tm");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            m.invoke(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.getTargetException().printStackTrace();
        }
    }
}
